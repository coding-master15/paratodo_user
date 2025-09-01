package com.paratodo.user;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;

import com.general.files.SinchService;
import com.utils.Logger;

public abstract class BaseActivity extends AppCompatActivity implements ServiceConnection {

    private SinchService.SinchServiceInterface mSinchServiceInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext().bindService(new Intent(this, SinchService.class), this,
                BIND_AUTO_CREATE);

        // Disable default fitting
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        }

        // Apply to root view once it's available
        getWindow().getDecorView().setOnApplyWindowInsetsListener((v, insets) -> {
            int left = 0, top = 0, right = 0, bottom = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                //left = insets.getInsets(WindowInsets.Type.systemBars()).left;
                top = insets.getInsets(WindowInsets.Type.systemBars()).top;
                //right = insets.getInsets(WindowInsets.Type.systemBars()).right;
                bottom = insets.getInsets(WindowInsets.Type.systemBars()).bottom;
            }

            // Apply padding to the content view
            View content = findViewById(android.R.id.content);
            if (content != null) {
                content.setPadding(left, top, right, bottom);
            }

            return insets;
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Logger.e("called", "true");
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = (SinchService.SinchServiceInterface) iBinder;
            onServiceConnected();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = null;
            onServiceDisconnected();
        }
    }

    protected void onServiceConnected() {
        // for subclasses
    }

    protected void onServiceDisconnected() {
        // for subclasses
    }

    public SinchService.SinchServiceInterface getSinchServiceInterface() {
        return mSinchServiceInterface;
    }


}
