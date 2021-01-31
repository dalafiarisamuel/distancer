package ng.sam.distancer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HumanLocation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int? = null

    @ColumnInfo(name = "start_longitude")
    var startLongitude: String? = null

    @ColumnInfo(name = "start_latitude")
    var startLatitude: String? = null

    @ColumnInfo(name = "end_longitude")
    var endLongitude: String? = null

    @ColumnInfo(name = "end_latitude")
    var endLatitude: String? = null

    @ColumnInfo(name = "create_date")
    var createdDate: String? = null

    @ColumnInfo(name = "resolved_start_location")
    var resolvedStartLocation: String? = null

    @ColumnInfo(name = "resolved_end_location")
    var resolvedEndLocation: String? = null

    @ColumnInfo(name = "is_location_resolved")
    var isLocationResolve: Boolean = false
}