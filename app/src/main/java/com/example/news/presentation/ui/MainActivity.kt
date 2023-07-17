package com.example.news.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.presentation.ui.adapter.NewsAdapter
import com.example.news.presentation.viewmodel.NewsViewModel
import com.example.news.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory).get(NewsViewModel::class.java)

        val navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            ?.findNavController()
        setupNavController(navController)
    }

    private fun setupNavController(navController: NavController?) {
        navController?.let {
            binding.bnvNews.setupWithNavController(
                it
            )
        }
    }
}