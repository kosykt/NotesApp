package ru.kostry.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kostry.notesapp.data.NoteItem
import ru.kostry.notesapp.databinding.NoteItemBinding

class NoteAdapter(private val onItemClicked: (NoteItem) -> Unit) :
    ListAdapter<NoteItem, NoteAdapter.NoteViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class NoteViewHolder(private var binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteItem: NoteItem) {
            binding.noteNameTextView.text = noteItem.noteTitle
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NoteItem>() {
            override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
                return oldItem.noteTitle == newItem.noteTitle
            }
        }
    }
}