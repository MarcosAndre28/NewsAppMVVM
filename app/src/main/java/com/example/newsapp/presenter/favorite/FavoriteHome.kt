package com.example.newsapp.presenter.favorite

import com.example.newsapp.model.Article

interface FavoriteHome {
    fun showArticles(articles: List<Article>)
}