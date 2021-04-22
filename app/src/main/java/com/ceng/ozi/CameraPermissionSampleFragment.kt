package com.ceng.ozi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CameraPermissionSampleFragment : Fragment(), PermissionListener {

    @Inject
    lateinit var cameraPermissionObserver: CameraPermissionObserver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraPermissionObserver.permissionListener = this

        button_first.setOnClickListener {
            requestPermission()
        }
    }

    override fun onDestroyView() {
        cameraPermissionObserver.permissionListener = null
        super.onDestroyView()
    }

    override fun permissionGranted() {
        Toast.makeText(requireActivity(), "Permission Granted", Toast.LENGTH_SHORT).show()
    }

    override fun permissionDenied() {
        Toast.makeText(requireActivity(), "Permission Denied", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermission() {
        if (cameraPermissionObserver.checkPermission()) {
            // Go
        } else {
            cameraPermissionObserver.launch()
        }
    }
}