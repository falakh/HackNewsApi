package com.example.newsproject.repositori

import androidx.lifecycle.MutableLiveData
import com.example.newsproject.model.NewsDetailPojo


object DataRepository {
    internal val mCurrentNews: MutableLiveData<NewsDetailPojo> = MutableLiveData()

    fun starCurrentNews(news: NewsDetailPojo) {
        mCurrentNews.value = news
    }


}