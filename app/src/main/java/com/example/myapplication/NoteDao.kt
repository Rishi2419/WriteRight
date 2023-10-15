package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(note: Notes)

    @Delete
   suspend fun delete(note: Notes)

    @Query("Select * from notes_table order by id ASC")
    fun getallnotes():LiveData<List<Notes>>


}