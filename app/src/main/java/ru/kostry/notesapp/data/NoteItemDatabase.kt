package ru.kostry.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteItem::class], version = 1, exportSchema = false)
abstract class NoteItemDatabase: RoomDatabase() {

    abstract fun noteItemDao(): NoteItemDao

    companion object {

        @Volatile
        private var INSTANCE: NoteItemDatabase? = null

        fun getDatabase(context: Context): NoteItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteItemDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}