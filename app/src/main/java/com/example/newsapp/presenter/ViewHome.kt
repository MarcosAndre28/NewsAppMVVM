package com.example.newsapp.presenter

import com.example.newsapp.model.Article

interface ViewHome {
    interface View{
        fun showProgressBar()
        fun showFailure(message: String)
        fun hidePrpgressBar()
        fun shoArticles(article: List<Article>)
    }
}