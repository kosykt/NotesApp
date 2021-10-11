package ru.kostry.notesapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteItem: NoteItem)

    @Update
    suspend fun update(noteItem: NoteItem)

    @Delete
    suspend fun delete(noteItem: NoteItem)

    @Query("SELECT * from item ORDER BY title ASC")
    fun getAllItems(): Flow<List<NoteItem>>

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<NoteItem>

}