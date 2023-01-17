package com.example.newsapp.model.data

import com.example.newsapp.network.RetrofitInstance
import com.example.newsapp.presenter.news.NewsHome
import com.example.newsapp.presenter.search.SearchHome
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    @OptIn(DelicateCoroutinesApi::class)
    fun getBrakingNews(callback: NewsHome.Presenter){
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakinNews("br")

            if (response.isSuccessful){
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            }else{
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
            val response = RetrofitInstance.api.searchNews(term)
            if (response.isSuccessful){
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            }
            else{
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}