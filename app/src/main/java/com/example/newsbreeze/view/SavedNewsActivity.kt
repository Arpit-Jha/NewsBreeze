package com.example.newsbreeze.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbreeze.R
import com.example.newsbreeze.adapter.SavedNewsAdapter
import com.example.newsbreeze.model.News
import com.example.newsbreeze.viewmodel.ViewModel

class SavedNewsActivity: AppCompatActivity()
{
    companion object
    {
        fun newIntent(context: Context): Intent {
            return Intent(context, SavedNewsActivity::class.java)
        }
    }

    private var savedNews = ArrayList<News>()
    private var savedNewsAdapter = SavedNewsAdapter(this, savedNews)

    private lateinit var imgBackButton: ImageView
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_news)

        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                loadSavedNews(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                loadSavedNews(newText)
                return true
            }
        })

        imgBackButton = findViewById(R.id.back_button)
        imgBackButton.setOnClickListener {
            onBackPressed()
        }

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = savedNewsAdapter

        viewModel = ViewModel(this)

        loadSavedNews(null)
    }

    private fun loadSavedNews(query: String?)
    {
        if (query == null)
        {
            viewModel.getAllSavedNews().observe(this) {
                if (it != null)
                {
                    savedNews.clear()
                    savedNews.addAll(it)
                    savedNewsAdapter.notifyDataSetChanged()
                }
            }
        }
        else
        {
            viewModel.searchNews(query).observe(this) {
                if (it != null)
                {
                    savedNews.clear()
                    savedNews.addAll(it)
                    savedNewsAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}