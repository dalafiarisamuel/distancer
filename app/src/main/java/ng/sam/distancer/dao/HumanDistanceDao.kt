package ng.sam.distancer.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ng.sam.distancer.models.HumanLocation

@Dao
interface HumanDistanceDao {

    @Query("SELECT * FROM `HumanLocation` ORDER BY `_id` DESC")
    fun getHumanLocationLive(): LiveData<MutableList<HumanLocation>>

    @Query("SELECT * FROM `HumanLocation` WHERE 'is_location_resolved' = 0 ORDER BY `_id` DESC")
    suspend fun getUnresolvedHumanLocation(): MutableList<HumanLocation>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHumanLocation(location: HumanLocation): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHumanLocation(location: HumanLocation)

    @Query("DELETE FROM `HumanLocation`")
    suspend fun cleanTable()

    @Query("SELECT COUNT(*) FROM `HumanLocation`")
    suspend fun getCount(): Long
}