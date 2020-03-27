package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageDownloadFileResult;

import java.io.File;
import java.io.FileOutputStream;

public class S3Download extends AppCompatActivity {

    //data memebers
    private EditText mFilename;
    private Context context;
    private String getFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_download);
        mFilename = findViewById(R.id.inputDownload);
    }


    private void downloadFile(){

         getFile = mFilename.getText().toString();

        Amplify.Storage.downloadFile(
                getFile,
                getApplicationContext().getFilesDir() + getFile,
                new ResultListener<StorageDownloadFileResult>() {
                    @Override
                    public void onResult(StorageDownloadFileResult result) {
                       //writeFile(result);

                        Log.i("StorageQuickStart", "Successfully downloaded: " + result.getFile().getName());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("StorageQuickStart", error.getMessage());
                    }
                }
        );
    }



    public void writeFile(StorageDownloadFileResult fileContents){



        try {
            File textFile = new File(getExternalFilesDir(null), getFile);
            FileOutputStream fileOutputStream = new FileOutputStream(textFile);
            fileOutputStream.write(fileContents.toString().getBytes());
            fileOutputStream.close();
            Log.d("FileHandler", "File written to: " + getExternalFilesDir(null));
        } catch (java.io.IOException e){
            e.printStackTrace();
            Log.e("FileHandler", "Error writing file");

        }

    }


    private void saveImage(Context context, Bitmap b, String imageName){
        FileOutputStream foStream;
        try{
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e){
            Log.d("saveImage", "Exception, There was a problem saving the file");
            e.printStackTrace();
        }
    }


    //onClick event handler
    public void onClickDownloadFile(View view) {
        downloadFile();
    }



}


