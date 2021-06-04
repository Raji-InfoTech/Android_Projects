package com.dyolmart;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class GrantPermission extends AppCompatActivity {
    static  Activity activity;
    static Context context;
    String[] permissions = new String[]{
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS
    }; // Here i used multiple permission check
    int MULTIPLE_PERMISSIONS=199;
    List<String> listPermissionsNeeded = new ArrayList<>();
    Boolean Granted=true,NeverAskChecked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Boolean b=checkPermissions();
    }
        boolean checkPermissions() {
        int result;
        for (String p : permissions) {
            //check whether permissions already given or not
            result = ContextCompat.checkSelfPermission(com.dyolmart.GrantPermission.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(com.dyolmart.GrantPermission.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 199: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(),com.dyolmart.Customer_Login.class);
                    startActivity(intent);

                }
                // permissions list of don't granted permission
                String perStr = "";

                for (int i = 0; i < grantResults.length; i++)
                    if (grantResults[i] != 0) {
                        perStr += permissions[i] + "\n";
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(com.dyolmart.GrantPermission.this, permissions[i])) {
                            Granted = false;
                            if (i != 4)
                                NeverAskChecked = true;
                           // Toast.makeText(context,String.valueOf(i)+NeverAskChecked.toString()+permissions[i],Toast.LENGTH_LONG).show();
                        } else {
                            Granted = false;
                            //Toast.makeText(context,String.valueOf(i),Toast.LENGTH_LONG).show();
                        }
                    }
                //Toast.makeText(MainActivity.this,"outside " +NeverAskChecked.toString()+","+Granted.toString(),Toast.LENGTH_LONG).show();

                if (NeverAskChecked.equals(true))
                {
                    Intent intent = new Intent(com.dyolmart.GrantPermission.this, RequestPermissionWarning.class);
                    intent.putExtra("NeverAsk", "true");
                   // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                }
                else
                if (Granted.equals(false)) {
                    Intent intent = new Intent(com.dyolmart.GrantPermission.this, RequestPermissionWarning.class);
                    intent.putExtra("Permissions", perStr);
                    intent.putExtra("NeverAsk", "false");
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                // Toast.makeText(MainActivity.this,permissions[i]+String.valueOf(grantResults[i]),Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}
