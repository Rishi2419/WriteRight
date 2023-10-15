package com.example.myapplication

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Notes>> = noteDao.getallnotes()

    suspend fun insert(note: Notes){
        noteDao.insert(note)
    }
    suspend fun delete(note: Notes){
        noteDao.delete(note)
    }



}