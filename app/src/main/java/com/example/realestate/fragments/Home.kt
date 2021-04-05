@file:Suppress("UNREACHABLE_CODE")

package com.example.realestate.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realestate.data.PropertyViewModel
import com.example.realestate.utils.PropertyAdapter
import com.example.realestate.databinding.FragmentHomeBinding


class Home  : Fragment() {

    private val viewModel: PropertyViewModel by activityViewModels()

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
        val propertyAdapter = PropertyAdapter()

        binding.apply {
            propertyRecyclerView.apply {
                adapter = propertyAdapter
                layoutManager = LinearLayoutManager(context)
            }

            viewModel.properties.observe(viewLifecycleOwner, Observer { properties ->

                propertyAdapter.submitList(properties.data)
            })
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}