package com.zy.serviceimpl;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        PermissionActivityPermissionsDispatcher.getContractWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    public void getContract() {
        Toast.makeText(this, "获得了权限", Toast.LENGTH_SHORT).show();
    }
}
