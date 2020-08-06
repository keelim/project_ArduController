package com.keelim.testing.model

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.ArrayList
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
class Module {

    @Provides
    @Named("permission")
    fun providePermission(): PermissionListener {
        return object : PermissionListener {
            override fun onPermissionGranted() {
                Log.e("Permission", "Permission: Good")
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
                Log.e("Permission", "permission: Error")
            }
        }
    }
}