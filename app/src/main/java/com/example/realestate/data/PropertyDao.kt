import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realestate.data.Property

@Dao
interface PropertyDao{

    @Insert
    suspend fun insertProperty(property: Property)

    @Update
    fun updateProperty(property: Property)

    @Query("SELECT * FROM property_table")
    fun getAllProperties(): LiveData<List<Property>>

    @Query("DELETE FROM property_table")
    fun deleteAll(property: Property)
}