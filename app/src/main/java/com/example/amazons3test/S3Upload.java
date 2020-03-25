package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageUploadFileResult;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class S3Upload extends AppCompatActivity {

    private EditText fileName;
    private EditText fileContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_upload);

    }

    //Upload file
    private void uploadFile(){

        //set filename and file content
        fileName = findViewById(R.id.fileName);
        fileContents = findViewById(R.id.fileContents);


        File sampleFile = new File(getApplicationContext().getFilesDir(), fileName.getText().toString());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(sampleFile));
            writer.append(fileContents.getText().toString());
            writer.close();
        }
        catch(Exception e){
            Log.e("StorageQuickstart", e.getMessage());
        }
        Amplify.Storage.uploadFile(
                  "uploaded_" + fileName.getText().toString() + ".txt",
                sampleFile.getAbsolutePath(),
                new ResultListener<StorageUploadFileResult>() {
                    @Override
                    public void onResult(StorageUploadFileResult result) {
                        Log.i("StorageQuickStart", "Successfully uploaded: " + result.getKey());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("StorageQuickstart", "Upload error.", error);
                    }
                }
        );
    }

    public void onClickUploadFile(View view) {
        uploadFile();
        //Clear the EditText fields
        fileName.setText("");
        fileContents.setText("");
    }
}
