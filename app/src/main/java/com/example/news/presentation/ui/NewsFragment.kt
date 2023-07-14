package com.example.news.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.data.repository.Resource
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.presentation.ui.adapter.NewsAdapter
import com.example.news.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private val page: Int = 1
    private val country: String = "us"
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentNewsBinding
    lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentNewsBinding.bind(view)
        initRecyclerView()
        setupList()
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
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

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}