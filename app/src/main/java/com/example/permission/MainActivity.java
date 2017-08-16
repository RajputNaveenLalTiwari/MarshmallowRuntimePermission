package com.example.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RUNTIME_PERMISSION_REQUEST_CODE = 5;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            /*if (checkSinglePermissions())
            {
                init();
            }
            else
            {
                //Toast.makeText(context,"Need Permission To Work With This Application",Toast.LENGTH_LONG).show();
            }*/

            if(checkMultiplePermissions())
            {
                init();
            }
            else
            {
                Toast.makeText(context,"Need Permission To Work With This Application",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void init()
    {
        Toast.makeText(context,"Actual Logic",Toast.LENGTH_LONG).show();
    }

    private boolean checkSinglePermissions()
    {
        int readPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> permissionList = new ArrayList<>();
        if (readPermission != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty())
        {
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]), RUNTIME_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }

    private boolean checkMultiplePermissions()
    {
        int readPermission = ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA);
        int wirtePermission = ContextCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> permissionList = new ArrayList<>();
        if (readPermission != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (wirtePermission != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty())
        {
            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]), RUNTIME_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case RUNTIME_PERMISSION_REQUEST_CODE:
                if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED )
                {
                    Toast.makeText(context,"Now You Got Permission To Work With This Application",Toast.LENGTH_LONG).show();
                    init();
                }
                else
                {
                    Toast.makeText(context,"Need Permission To Work With This Application",Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }


    }
}
