package com.example.amazons3test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.ResultListener;
import com.amplifyframework.storage.result.StorageListResult;

public class S3ListFiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_list_files);
    }

    private void listFiles(){
        Amplify.Storage.list(
                "",
                new ResultListener<StorageListResult>() {
                    @Override
                    public void onResult(StorageListResult storageListResult) {
                        for (StorageListResult.Item item : storageListResult.getItems()) {
                            Log.i("StorageQuickStart", "Item: " + item.getKey());
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("StorageQuickStart", error.getMessage());
                    }
                }
        );
    }

    public void getCloudFiles(View view) {
        listFiles();
    }
}
