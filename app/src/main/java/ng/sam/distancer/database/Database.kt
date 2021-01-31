package ng.sam.distancer.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ng.sam.distancer.dao.HumanDistanceDao
import ng.sam.distancer.models.HumanLocation

@androidx.room.Database(
    entities = [HumanLocation::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "human_location"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun humanDistanceDao(): HumanDistanceDao


}