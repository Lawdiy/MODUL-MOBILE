package com.example.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.list.data.model.Movie
import com.example.list.databinding.ItemHighlightBinding

class HighlightAdapter(
    private val onItemClick: (Movie) -> Unit
) : ListAdapter<Movie, HighlightAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        with(holder.binding) {
            root.setOnClickListener { onItemClick(movie) }
            Glide.with(ivHighlight.context)
                .load(movie.imageResId)
                .into(ivHighlight)
        }
    }

    class ViewHolder(val binding: ItemHighlightBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(old: Movie, new: Movie) = old.id == new.id
        override fun areContentsTheSame(old: Movie, new: Movie) = old == new
    }
}