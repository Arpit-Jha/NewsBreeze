package com.example.newsbreeze.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbreeze.R
import com.example.newsbreeze.adapter.HeadlineAdapter
import com.example.newsbreeze.model.Headline
import com.example.newsbreeze.viewmodel.ViewModel

class HeadlinesActivity : AppCompatActivity()
{
    private val TAG = "HeadlinesActivity"

    private var headlines = ArrayList<Headline>()
    private var headlinesAdapter = HeadlineAdapter(this, headlines)

    private lateinit var viewModel: ViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar

    private lateinit var imgSavedNews: ImageView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines)

        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                progressBar.visibility = View.VISIBLE

                val locale = resources.configuration.locale.country
                loadData(query, locale)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                progressBar.visibility = View.VISIBLE

                val locale = resources.configuration.locale.country
                loadData(newText, locale)

                return true
            }
        })

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = headlinesAdapter

        progressBar = findViewById(R.id.progress_bar)

        imgSavedNews = findViewById(R.id.saved_news)
        imgSavedNews.setOnClickListener{
            intent = SavedNewsActivity.newIntent(this)
            startActivity(intent)
        }

        viewModel = ViewModel(this)

        progressBar.visibility = View.VISIBLE
        val locale = resources.configuration.locale.country
        loadData(null, locale)
    }

    private fun loadData(keyword: String?, countryCode: String)
    {
        viewModel.getTopHeadlines(keyword, countryCode).observe(this) {
            progressBar.visibility = View.GONE

            if (it?.headlines != null)
            {
                headlines.clear()
                headlines.addAll(it.headlines)
                headlinesAdapter.notifyDataSetChanged()
            }
        }
    }
}