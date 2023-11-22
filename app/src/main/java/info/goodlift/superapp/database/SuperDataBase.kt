package info.goodlift.superapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.goodlift.superapp.dao.MyDao
import info.goodlift.superapp.model.Author

@Database(
    entities = [
//        Book::class,
        Author::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SuperDataBase : RoomDatabase() {
    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: SuperDataBase? = null

        fun getDatabase(context: Context): SuperDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SuperDataBase::class.java,
                    "super_db.db3"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}