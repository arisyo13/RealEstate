package com.example.realestate.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Property::class], version = 1)
abstract class PropertyDatabase: RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
}