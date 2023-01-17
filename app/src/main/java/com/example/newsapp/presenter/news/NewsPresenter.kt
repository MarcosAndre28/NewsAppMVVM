package com.example.newsapp.presenter.news

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome

class NewsPresenter(val view: ViewHome.View, private val dataSource: NewsDataSource): NewsHome.Presenter {

    override fun requestAll() {
       this.view.showProgressBar()
        this.dataSource.getBrakingNews(this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.shoArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
       this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hidePrpgressBar()
    }

}