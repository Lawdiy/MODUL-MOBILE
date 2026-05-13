package com.example.list.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.list.R
import com.example.list.data.source.ComicDataSource
import com.example.list.databinding.FragmentDetailBinding
import java.util.Locale
import timber.log.Timber

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val comicId = arguments?.getInt("comicId") ?: -1
        val comic = ComicDataSource.dummyComics.find { it.id == comicId }

        comic?.let {
            Timber.d("Displaying detail for comic: ${it.title}")
            binding.toolbar.title = it.title
            binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

            Glide.with(this).load(it.imageResId).into(binding.ivDetail)
            binding.tvTitle.text = it.title
            binding.tvYear.text = it.year

            val isId = Locale.getDefault().language == "in" || Locale.getDefault().language == "id"
            binding.tvPlot.text = if (isId) it.plotId else it.plotEn
        } ?: run {
            Timber.e("Comic with ID ${comicId} not found!")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}