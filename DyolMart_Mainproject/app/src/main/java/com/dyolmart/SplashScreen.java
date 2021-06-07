package com.dyolmart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.dyolmart.MainActivity;

public class SplashScreen extends AppCompatActivity {

    private boolean InternetCheck = true;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        StartAnimations();
        postDelayedMethod();
        //postLocationMethod();
    }

    private void postLocationMethod () {
        if (ActivityCompat.checkSelfPermission(com.dyolmart.SplashScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(com.dyolmart.SplashScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(com.dyolmart.SplashScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            // Write you code here if permission already given.
        }
    }

    private void postDelayedMethod() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                boolean InternetResult = checkConnection();
                Intent i = new Intent(getApplicationContext(), com.dyolmart.MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }


    private void DialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(com.dyolmart.SplashScreen.this);

        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connectivity");

        builder.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                });
        builder.setPositiveButton("Retry",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postDelayedMethod();

            }


        });
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private boolean checkConnection() {

        if (isOnline()){
            return InternetCheck;
        }
        else {
            return false;
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        @SuppressLint("MissingPermission") NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(),"Please Check Your Internet Connectivity and Try again...", Toast.LENGTH_LONG).show();
            return false;
        }    }



    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.in_le);


        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView ) findViewById(R.id.loooo);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }
}


