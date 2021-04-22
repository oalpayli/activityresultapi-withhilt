package com.ceng.ozi

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CameraPermissionObserver @Inject constructor(
    @ActivityContext private val appCompatContext: Context
) : AbstractPermissionObserver() {

    private var requestPermission: ActivityResultLauncher<Array<String>>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (appCompatContext is AppCompatActivity) {
            requestPermission =
                appCompatContext.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                    permissions.entries.forEach {
                        granted = it.value
                        if (!it.value) {
                            return@forEach
                        }
                    }
                    if (granted) {
                        permissionListener?.permissionGranted()
                    } else {
                        permissionListener?.permissionDenied()
                    }
                }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        requestPermission = null
    }

    override fun launch() {
        requestPermission?.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }

    override fun checkPermission(): Boolean {
        val cameraPermission =
            ContextCompat.checkSelfPermission(appCompatContext, Manifest.permission.CAMERA)
        val writePermission = ContextCompat.checkSelfPermission(
            appCompatContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission = ContextCompat.checkSelfPermission(
            appCompatContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return cameraPermission == PackageManager.PERMISSION_GRANTED &&
                writePermission == PackageManager.PERMISSION_GRANTED &&
                readPermission == PackageManager.PERMISSION_GRANTED
    }
}