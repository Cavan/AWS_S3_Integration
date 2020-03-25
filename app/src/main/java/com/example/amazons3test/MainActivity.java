package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
