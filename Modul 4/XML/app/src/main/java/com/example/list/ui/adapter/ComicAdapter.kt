package com.example.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.list.R
import com.example.list.data.model.Comic
import com.example.list.databinding.ItemComicBinding
import java.util.Locale

class ComicAdapter(
    private val onDetailClick: (Comic) -> Unit,
    private val onBrowserClick: (Comic) -> Unit
) : ListAdapter<Comic, ComicAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = getItem(position)
        val context = holder.itemView.context
        val isId = Locale.getDefault().language == "in" || Locale.getDefault().language == "id"

        with(holder.binding) {
            tvTitle.text = comic.title
            tvYear.text = comic.year
            tvPlot.text =
                "${context.getString(R.string.plot_label)} ${if (isId) comic.plotId else comic.plotEn}"

            Glide.with(ivComic).load(comic.imageResId).into(ivComic)

            btnDetail.setOnClickListener { onDetailClick(comic) }
            btnBrowser.setOnClickListener { onBrowserClick(comic) }
        }
    }

    class ViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(old: Comic, new: Comic) = old.id == new.id
        override fun areContentsTheSame(old: Comic, new: Comic) = old == new
    }
}