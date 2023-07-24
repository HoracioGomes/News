package com.example.news.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.news.R
import com.example.news.databinding.FragmentInfoBinding
import com.example.news.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    lateinit var infoBinding: FragmentInfoBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoBinding = FragmentInfoBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        infoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if (article.url != "" && article.url != null) {
                loadUrl(article.url)
            }
        }

        infoBinding.fabSave.setOnClickListener {
            if (article != null) {
                viewModel.saveArticle(article)
            }
            Snackbar.make(view, "Saved!", Snackbar.LENGTH_SHORT).show()
        }
    }

}