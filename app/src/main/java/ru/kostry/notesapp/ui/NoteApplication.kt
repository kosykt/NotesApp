package ru.kostry.notesapp.ui

import android.app.Application
import ru.kostry.notesapp.data.NoteItemDatabase

class NoteApplication : Application(){
    val database: NoteItemDatabase by lazy { NoteItemDatabase.getDatabase(this) }
}