package com.example.amazons3test;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;




import com.amplifyframework.storage.result.StorageDownloadFileResult;

import java.io.File;
import java.io.FileOutputStream;

public class FileHandler {

    private final String FILENAME;
    private final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    Context mContext;
    FileHandler(String newFilename, Context context){

        this.FILENAME = newFilename;
        this.mContext = context;

    }

    public void saveFileEXT(){

    }

    public void loadFileEXT(){

    }

    public void saveImageFile(Bitmap imageToSave,String filename){
        File direct = new File(Environment.getExternalStorageDirectory() + "/AutoXCaptures");
    }


    public boolean isExternalStorageWritable(){
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
            return true;
        }
        return false;
    }

//    public void checkStoragePermissions(){
//        int permission = ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(, PERMISSIONS_STORAGE, 101);
//        }
//    }



    public void writeFile(StorageDownloadFileResult fileContents){



        try {
            File textFile = new File(mContext.getExternalFilesDir(null), FILENAME);
            FileOutputStream fileOutputStream = new FileOutputStream(textFile);
            fileOutputStream.write(fileContents.toString().getBytes());
            fileOutputStream.close();
            Log.d("FileHandler", "File written to: " + mContext.getExternalFilesDir(null));
        } catch (java.io.IOException e){
            e.printStackTrace();
            Log.e("FileHandler", "Error writing file");

        }

    }

}
