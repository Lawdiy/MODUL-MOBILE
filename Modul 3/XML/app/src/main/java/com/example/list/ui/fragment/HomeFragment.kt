package com.example.list.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.list.R
import com.example.list.data.source.MovieDataSource
import com.example.list.databinding.FragmentHomeBinding
import com.example.list.ui.adapter.HighlightAdapter
import com.example.list.ui.adapter.MovieAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // Toolbar Menu for Language
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_language) {
                findNavController().navigate(R.id.action_home_to_language)
                true
            } else false
        }

        val movies = MovieDataSource.dummyMovies

        // Main List
        val movieAdapter = MovieAdapter(
            onDetailClick = { movie ->
                val bundle = bundleOf("movieId" to movie.id)
                findNavController().navigate(R.id.action_home_to_detail, bundle)
            },
            onBrowserClick = { movie ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.browserUrl))
                startActivity(intent)
            }
        )
        binding.rvMovies.adapter = movieAdapter
        movieAdapter.submitList(movies)

        // Highlight Carousel
        val highlightAdapter = HighlightAdapter(
            onItemClick = { movie ->
                val bundle = bundleOf("movieId" to movie.id)
                findNavController().navigate(R.id.action_home_to_detail, bundle)
            }
        )
        binding.rvHighlight.adapter = highlightAdapter
        androidx.recyclerview.widget.PagerSnapHelper().attachToRecyclerView(binding.rvHighlight)
        highlightAdapter.submitList(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}