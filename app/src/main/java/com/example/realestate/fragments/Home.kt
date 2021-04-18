@file:Suppress("UNREACHABLE_CODE")

package com.example.realestate.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realestate.data.PropertyViewModel
import com.example.realestate.utils.PropertyAdapter
import com.example.realestate.databinding.FragmentHomeBinding
import com.example.realestate.location.LocationViewModel


class Home  : Fragment() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    private val viewModel: PropertyViewModel by activityViewModels()
    private val locationViewModel: LocationViewModel by activityViewModels()

    private var latitude :Double = 0.0
    private var longitude :Double = 0.0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepRequestLocationUpdates()
    }

    private fun observeData(){
        val propertyAdapter = PropertyAdapter(latitude, longitude)

        binding.apply {
            propertyRecyclerView.apply {
                adapter = propertyAdapter
                layoutManager = LinearLayoutManager(context)
            }
            progressBar.visibility = View.GONE
        }
        viewModel.properties.observe(viewLifecycleOwner, Observer { properties ->
            propertyAdapter.submitList(properties.data)
        })
    }

    private fun requestLocationUpdates() {
        locationViewModel.getLocationLiveData().observe(viewLifecycleOwner, Observer { locationDetails ->
            latitude = locationDetails.latitude
            longitude = locationDetails.longitude
            observeData()
        })
    }

    private fun prepRequestLocationUpdates() {

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            requestLocationUpdates()
        } else {
            val permissionRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissionRequest, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    prepRequestLocationUpdates()
                } else {
                    Log.d("message","location not granted")
                    observeData()
                }
            }

        }
    }


}