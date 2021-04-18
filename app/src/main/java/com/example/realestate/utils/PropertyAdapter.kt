package com.example.realestate.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.realestate.data.Property
import com.example.realestate.databinding.PropertyItemBinding
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import java.text.DecimalFormat

class PropertyAdapter(latitude: Double, longitude: Double): ListAdapter<Property, PropertyAdapter.PropertyViewHolder>(PropertyComparator()) {

    private val latLng = LatLng(latitude, longitude)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val binding =
            PropertyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PropertyViewHolder(binding, latLng)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PropertyViewHolder(private val binding: PropertyItemBinding, private val latlng: LatLng) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Property) {
            val propertyLocation = LatLng(property.latitude, property.longitude)
            val df = DecimalFormat(".0")
            binding.apply {
                Glide.with(itemView)
                    .load("https://intern.docker-dev.d-tt.nl" + property.image )
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(propertyImage)
                price.text = property.price.toString()
                zip.text = property.zip
                city.text = property.city
                bedrooms.text = property.bedrooms.toString()
                bathrooms.text = property.bathrooms.toString()
                size.text = property.size.toString()
                if(latlng.latitude != 0.0){
                    val d = df.format(SphericalUtil.computeDistanceBetween(propertyLocation, latlng)/1000)

                    location.text = d
                }
                else{
                    location.visibility = View.GONE
                    locationView.visibility = View.GONE
                }
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