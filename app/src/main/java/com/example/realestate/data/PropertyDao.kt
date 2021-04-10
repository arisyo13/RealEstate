package com.example.realestate.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Query("SELECT * FROM property_table ORDER BY price ASC")
    fun getAllProperties(): Flow<List<Property>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(properties: List<Property>)

    @Query("DELETE FROM property_table")
    suspend fun deleteAllProperties()
}