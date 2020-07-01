package org.nuc.labs.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.nuc.labs.db.dao.StudentDao
import org.nuc.labs.db.model.Student


/**
 * 设计的单例模式
 * */
@Database(
    entities = arrayOf(Student::class),
    version = 1,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null
        fun getInstance(context: Context): StudentDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "app.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("app.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
    }

    abstract fun getStudentDao(): StudentDao
}