package com.example.newsbreeze.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbreeze.R
import com.example.newsbreeze.model.Headline
import com.example.newsbreeze.model.News
import com.example.newsbreeze.util.DateFormatter
import com.example.newsbreeze.view.ArticleActivity
import com.example.newsbreeze.viewmodel.ViewModel
import com.squareup.picasso.Picasso

class HeadlineAdapter(private val context: Context, private val headlines: List<Headline>): RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder>()
{
    inner class HeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private lateinit var headline: Headline
        private lateinit var news: News
        private val imageView: ImageView

        private val tvTitle: TextView
        private val tvDescription: TextView
        private val tvTime: TextView

        private val btnRead: Button
        private val btnSave: Button

        private val newsViewModel = ViewModel(context)

        init {
            imageView = itemView.findViewById(R.id.image)

            tvTitle = itemView.findViewById(R.id.title)
            tvDescription = itemView.findViewById(R.id.desc)
            tvTime = itemView.findViewById(R.id.time)

            btnRead = itemView.findViewById(R.id.read)
            btnRead.setOnClickListener {
                val intent = ArticleActivity.newIntent(context, headline.urlToImage,
                    headline.publishedAt, headline.title, headline.description, headline.content,
                    headline.author)

                context.startActivity(intent)
            }

            btnSave = itemView.findViewById(R.id.save)
            btnSave.setOnClickListener {
                news = News(
                    headline.author, headline.title, headline.description, headline.content,
                    headline.url, headline.urlToImage, headline.publishedAt
                )

                newsViewModel.upsert(news)
                Toast.makeText(context, "News saved", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(headline: Headline)
        {
            this.headline = headline

            Picasso.get().load(headline.urlToImage).into(imageView)

            tvTitle.text = headline.title
            tvDescription.text = headline.description
            tvTime.text = DateFormatter(context).format(headline.publishedAt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int)
    {
        holder.bind(headlines[position])
    }

    override fun getItemCount(): Int
    {
        return headlines.size
    }
}