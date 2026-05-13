package com.example.list.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.list.R
import com.example.list.databinding.FragmentHomeBinding
import com.example.list.ui.adapter.ComicAdapter
import com.example.list.ui.adapter.HighlightAdapter
import com.example.list.ui.viewmodel.ComicViewModel
import com.example.list.ui.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ComicViewModel by viewModels {
        ComicViewModelFactory(getString(R.string.app_name))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupToolbar()
        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.toolbar.title = viewModel.getAppName()
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_language) {
                findNavController().navigate(R.id.action_home_to_language)
                true
            } else false
        }
    }

    private fun setupRecyclerViews() {
        val comicAdapter = ComicAdapter(
            onDetailClick = { comic ->
                Timber.i("Detail button clicked for: ${comic.title}")
                viewModel.onComicClick(comic.id)
            },
            onBrowserClick = { comic ->
                Timber.i("Explicit Intent button clicked for: ${comic.title}")
                viewModel.onBrowserClick(comic.browserUrl)
            }
        )
        binding.rvComics.adapter = comicAdapter

        val highlightAdapter = HighlightAdapter(
            onItemClick = { comic ->
                Timber.i("Highlight item clicked: ${comic.title}")
                viewModel.onComicClick(comic.id)
            }
        )
        binding.rvHighlight.adapter = highlightAdapter
        if (binding.rvHighlight.onFlingListener == null) {
            androidx.recyclerview.widget.PagerSnapHelper().attachToRecyclerView(binding.rvHighlight)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.comics.collect { comics ->
                        if (comics.isNotEmpty()) {
                            Timber.d("Received ${comics.size} comics from ViewModel")
                        }
                        (binding.rvComics.adapter as? ComicAdapter)?.submitList(comics)
                        (binding.rvHighlight.adapter as? HighlightAdapter)?.submitList(comics)
                    }
                }

                launch {
                    viewModel.selectedComicId.collect { id ->
                        id?.let {
                            Timber.d("Navigating to detail with ID: ${it}")
                            val bundle = bundleOf("comicId" to it)
                            findNavController().navigate(R.id.action_home_to_detail, bundle)
                            viewModel.onNavigated()
                        }
                    }
                }

                launch {
                    viewModel.selectedBrowserUrl.collect { url ->
                        url?.let {
                            Timber.d("Opening browser for URL: ${it}")
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            startActivity(intent)
                            viewModel.onNavigated()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}