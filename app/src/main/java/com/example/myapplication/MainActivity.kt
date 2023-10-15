package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRvAdaptor {

    lateinit var viewmodel: NoteViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adaptor = NotesRVAdaptor(this, this)
        recyclerView.adapter = adaptor

        viewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewmodel::class.java)

        viewmodel.allnotes.observe(this, Observer { list ->
            list?.let {
                adaptor.updateList(it)
            }

        })

    }

    override fun onItemClicked(note: Notes) {
        viewmodel.deleteNode(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitdata(view: View) {
        val input = findViewById<EditText>(R.id.input)
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()){
            viewmodel.insertNote(Notes(noteText))
            Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_LONG).show()

        }
    }
}