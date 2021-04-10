package com.example.realestate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "property_table")
data class Property (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val price: Int,
    val bedrooms: Int,
    val bathrooms: Int,
    val size: Int,
    val description: String,
    val zip: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val createdDate: String
)