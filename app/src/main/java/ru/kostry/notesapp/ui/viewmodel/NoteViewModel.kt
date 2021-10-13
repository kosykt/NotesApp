package ru.kostry.notesapp.ui.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.kostry.notesapp.data.NoteItem
import ru.kostry.notesapp.data.NoteItemDao

class NoteViewModel(private val noteItemDao: NoteItemDao): ViewModel() {

    val allNoteItems: LiveData<List<NoteItem>> = noteItemDao.getAllItems().asLiveData()

    private fun insertItem(noteItem: NoteItem) {
        viewModelScope.launch {
            noteItemDao.insert(noteItem)
        }
    }

    private fun getNewItemEntry(noteTitle: String, noteText: String): NoteItem {
        return NoteItem(
            noteTitle = noteTitle,
            noteText = noteText
        )
    }

    fun addNewItem(noteTitle: String, noteText: String) {
        val newItem = getNewItemEntry(noteTitle, noteText)
        insertItem(newItem)
    }

    fun isEntryValid(noteTitle: String, noteText: String): Boolean {
        if (noteTitle.isBlank() || noteText.isBlank()) {
            return false
        }
        return true
    }

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