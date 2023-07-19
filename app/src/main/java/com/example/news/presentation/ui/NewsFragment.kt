package com.example.news.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.data.model.APIResponse
import com.example.news.data.repository.Resource
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.presentation.ui.adapter.NewsAdapter
import com.example.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private var page: Int = 1
    private val country: String = "us"
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentNewsBinding
    lateinit var newsAdapter: NewsAdapter

    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).adapter
        binding = FragmentNewsBinding.bind(view)
        initRecyclerView()
        setupList()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, args = bundle)
        }
        setupSearch()
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun setupList() {
        viewModel.getNews(country = country, page = page)
        viewModel.liveNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(activity, "A error occurred: $it", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setupSearch() {
        setSearchView()
        setCloseSearch()
        viewModel.searchedNews.observe(
            viewLifecycleOwner
        ) { resource ->
            when (resource) {

                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }

                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }


    private fun setSearchView() {
        binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchNews(
                        country = country,
                        page = page,
                        searchQuery = it
                    )
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    query?.let {
                        viewModel.searchNews(
                            country = country,
                            page = page,
                            searchQuery = it
                        )
                    }
                }
                return false
            }

        })
    }

    private fun setCloseSearch() {
        binding.svNews.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                initRecyclerView()
                setupList()
                return false
            }

        })
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val listSize = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = firstVisibleItemPosition + visibleItems >= listSize
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getNews(country, page)
                isScrolling = false
            }
        }
    }
}