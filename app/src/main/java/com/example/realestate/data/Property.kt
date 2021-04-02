package com.example.realestate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "property_table")
data class Property (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val zip: String) 