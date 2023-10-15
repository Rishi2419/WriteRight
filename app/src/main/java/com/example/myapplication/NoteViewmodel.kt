package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewmodel(application: Application) : AndroidViewModel(application) {

    private val repository:NotesRepository
    val allnotes : LiveData<List<Notes>>


    init {
        val dao=NoteDatabase.getDatabase(application).getnotedao()
        repository= NotesRepository(dao)
        allnotes=repository.allNotes
    }

    fun deleteNode(note:Notes)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun insertNote(note: Notes)=viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

}