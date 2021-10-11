package ru.kostry.notesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import ru.kostry.notesapp.data.NoteItem
import ru.kostry.notesapp.data.NoteItemDao

class NoteViewModel(private val noteItemDao: NoteItemDao): ViewModel() {

    val allNoteItems: LiveData<List<NoteItem>> = noteItemDao.getAllItems().asLiveData()

}

class NoteViewModelFactory(private val noteItemDao: NoteItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModelFactory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModelFactory(noteItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}