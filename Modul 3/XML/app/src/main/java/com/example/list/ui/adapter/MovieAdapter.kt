package com.example.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.list.R
import com.example.list.data.model.Movie
import com.example.list.databinding.ItemMovieBinding
import java.util.Locale

class MovieAdapter(
    private val onDetailClick: (Movie) -> Unit,
    private val onBrowserClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        val context = holder.itemView.context
        val isId = Locale.getDefault().language == "in" || Locale.getDefault().language == "id"

        with(holder.binding) {
            tvTitle.text = movie.title
            tvYear.text = movie.year
            tvPlot.text =
                "${context.getString(R.string.plot_label)} ${if (isId) movie.plotId else movie.plotEn}"

            Glide.with(ivMovie).load(movie.imageResId).into(ivMovie)

            btnDetail.setOnClickListener { onDetailClick(movie) }
            btnBrowser.setOnClickListener { onBrowserClick(movie) }
        }
    }

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(old: Movie, new: Movie) = old.id == new.id
        override fun areContentsTheSame(old: Movie, new: Movie) = old == new
    }
}