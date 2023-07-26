package com.example.news.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.data.model.Article
import com.example.news.databinding.FragmentSavedNewsBinding
import com.example.news.presentation.ui.Params.Companion.ARG_PARAM1
import com.example.news.presentation.ui.Params.Companion.ARG_PARAM2
import com.example.news.presentation.ui.adapter.NewsAdapter
import com.example.news.presentation.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment() {
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentSavedNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = (activity as MainActivity).adapter
        viewModel = (activity as MainActivity).viewModel
        binding = FragmentSavedNewsBinding.bind(view)
        configAdapter()
        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.getSavedNews().observe(
            viewLifecycleOwner
        ) {
            newsAdapter.differ.submitList(it)
        }
    }

    private fun configAdapter() {
        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }

            findNavController().navigate(
                R.id.action_savedNewsFragment_to_infoFragment,
                args = bundle
            )
        }
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}