package org.nuc.labs.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.nuc.labs.db.dao.QuestionDao
import org.nuc.labs.db.model.Question

@Database(
    entities = arrayOf(Question::class),
    version = 1,
    exportSchema = false
)
abstract class QuestionDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: QuestionDatabase? = null
        fun getInstance(context: Context): QuestionDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDatabase::class.java,
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

    abstract fun getQuestionDao(): QuestionDao
}