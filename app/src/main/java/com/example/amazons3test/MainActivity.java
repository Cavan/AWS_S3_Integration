package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Amplify initialization
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails userStateDetails) {
                try {
                    Amplify.addPlugin(new AWSS3StoragePlugin());
                    Amplify.configure(getApplicationContext());
                    Log.i("StorageQuickstart", "All set and ready to go!");
                } catch (Exception e) {
                    Log.e("StorageQuickstart", e.getMessage());
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("StorageQuickstart", "Initialization error.", e);
            }
        });
    }

    public void S3UploadFile(View view) {
        Intent intent = new Intent(this, S3Upload.class);
        startActivity(intent);
    }

    public void S3ListFiles(View view) {
        Intent intent = new Intent(this, S3ListFiles.class);
        startActivity(intent);
    }

    public void S3DeleteFile(View view) {
        Intent intent = new Intent(this, S3DeleteFile.class);
        startActivity(intent);
    }

    public void S3DownloadFile(View view) {
        Intent intent = new Intent(this, S3Download.class);
        startActivity(intent);
    }
}
