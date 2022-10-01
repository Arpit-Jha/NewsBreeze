package com.example.newsbreeze.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsbreeze.R
import com.example.newsbreeze.util.DateFormatter
import com.squareup.picasso.Picasso

class ArticleActivity: AppCompatActivity()
{
    companion object
    {
        private const val EXTRA_URL_TO_IMAGE = "com.example.newsbreeze.view.url_to_image"
        private const val EXTRA_DATE = "com.example.newsbreeze.view.date"
        private const val EXTRA_TITLE = "com.example.newsbreeze.view.title"
        private const val EXTRA_DESCRIPTION = "com.example.newsbreeze.view.description"
        private const val EXTRA_CONTENT = "com.example.newsbreeze.view.content"
        private const val EXTRA_AUTHOR = "com.example.newsbreeze.view.author"


        fun newIntent(context: Context, urlToImage: String?, date: String?, title: String?, description: String?,
                      content: String?, author: String?): Intent
        {
            val intent = Intent(context, ArticleActivity::class.java)

            intent.putExtra(EXTRA_URL_TO_IMAGE, urlToImage)
            intent.putExtra(EXTRA_DATE, date)
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_DESCRIPTION, description)
            intent.putExtra(EXTRA_CONTENT, content)
            intent.putExtra(EXTRA_AUTHOR, author)

            return intent
        }
    }
    private lateinit var imageView: ImageView
    private lateinit var imgBackButton: ImageView

    private lateinit var txtDate: TextView
    private lateinit var txtTitle: TextView
    private lateinit var txtDescription: TextView
    private lateinit var txtContent: TextView
    private lateinit var txtAuthor: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        imageView = findViewById(R.id.image)
        imgBackButton = findViewById(R.id.back_button)
        imgBackButton.setOnClickListener {
            onBackPressed()
        }

        txtDate = findViewById(R.id.date)
        txtTitle = findViewById(R.id.title)
        txtDescription = findViewById(R.id.desc)
        txtContent = findViewById(R.id.content)
        txtAuthor = findViewById(R.id.author)

        val intent = intent
        
        if (intent != null)
        {
            val urlToImage = intent.getStringExtra(EXTRA_URL_TO_IMAGE)
            val date = intent.getStringExtra(EXTRA_DATE)
            val title = intent.getStringExtra(EXTRA_TITLE)
            val description = intent.getStringExtra(EXTRA_DESCRIPTION)
            val content = intent.getStringExtra(EXTRA_CONTENT)
            val author = intent.getStringExtra(EXTRA_AUTHOR)

            Picasso.get().load(urlToImage).into(imageView)
            txtDate.text = DateFormatter(this).format(date)
            txtTitle.text = title
            txtDescription.text = description
            txtContent.text = content
            txtAuthor.text = author
        }
    }
}