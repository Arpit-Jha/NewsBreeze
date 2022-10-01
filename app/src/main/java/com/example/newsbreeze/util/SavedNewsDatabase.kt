package com.example.newsbreeze.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsbreeze.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class SavedNewsDatabase: RoomDatabase()
{
    abstract fun savedNewsDao(): SavedNewsDao

    companion object
    {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SavedNewsDatabase? = null

        fun getDatabase(context: Context): SavedNewsDatabase
        {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedNewsDatabase::class.java,
                    "word_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}