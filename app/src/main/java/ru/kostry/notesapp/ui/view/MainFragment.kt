package ru.kostry.notesapp.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.kostry.notesapp.databinding.FragmentMainBinding
import ru.kostry.notesapp.ui.NoteApplication
import ru.kostry.notesapp.ui.viewmodel.NoteViewModel
import ru.kostry.notesapp.ui.viewmodel.NoteViewModelFactory

class MainFragment : Fragment() {

    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory(
            (activity?.application as NoteApplication).database.noteItemDao()
        )
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

}