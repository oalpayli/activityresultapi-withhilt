package com.ceng.ozi

import androidx.lifecycle.LifecycleObserver

abstract class AbstractPermissionObserver : LifecycleObserver {

    var granted: Boolean = false
    var permissionListener: PermissionListener? = null

    abstract fun launch()

    abstract fun checkPermission(): Boolean
}

interface PermissionListener {
    fun permissionGranted()
    fun permissionDenied()
}
