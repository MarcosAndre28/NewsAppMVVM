package com.example.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.MainAdapter
import com.example.newsapp.model.Article
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome
import com.example.newsapp.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this,dataSource)
        presenter.requestAll()
        configRecycle()
    }

    private fun configRecycle(){
        val rvNews = findViewById<RecyclerView>(R.id.rvNews)
        with(rvNews){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    override fun showProgressBar() {
        val rvProgressBar = findViewById<ProgressBar>(R.id.rvProgressBar)
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
       Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun hidePrpgressBar() {
        val rvProgressBar = findViewById<ProgressBar>(R.id.rvProgressBar)
        rvProgressBar.visibility = View.GONE
    }

    override fun shoArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.search_menu -> {
                startActivity(Intent(this, SearchActivity::class.java))
                finish()
            }
            R.id.favorite_menu -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}