package com.example.realestate.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realestate.api.PropertyApiService.Companion.BASE_URL
import com.example.realestate.data.Property
import com.example.realestate.databinding.PropertyItemBinding

class PropertyAdapter: ListAdapter<Property, PropertyAdapter.PropertyViewHolder>(PropertyComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding =
            PropertyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PropertyViewHolder(private val binding: PropertyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(property: Property) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://intern.docker-dev.d-tt.nl" + property.image )
                    .into(propertyImage)

                zip.text = property.zip
            }
        }

    }

    class PropertyComparator : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property) =
            oldItem.zip == newItem.zip

        override fun areContentsTheSame(oldItem: Property, newItem: Property) =
            oldItem == newItem
    }
}