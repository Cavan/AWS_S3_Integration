package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageDownloadFileResult;

public class S3Download extends AppCompatActivity {

    //data memebers
    private EditText mFilename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_download);
        mFilename = findViewById(R.id.inputDownload);
    }


    private void downloadFile(){
        String getFile = mFilename.getText().toString();

        Amplify.Storage.downloadFile(
                getFile,
                getApplicationContext().getFilesDir() + "/DL_" + getFile,
                new ResultListener<StorageDownloadFileResult>() {
                    @Override
                    public void onResult(StorageDownloadFileResult result) {
                        Log.i("StorageQuickStart", "Successfully downloaded: " + result.getFile().getName());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("StorageQuickStart", error.getMessage());
                    }
                }
        );
    }


    public void onClickDownloadFile(View view) {
        downloadFile();
    }
}


