package com.example.newsproject.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.example.newsproject.model.NewsDetailPojo
import com.example.newsproject.model.NewsModel

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var  newsIdlIst =  MutableLiveData<ArrayList<Int>>()
    private var pickTittle = MutableLiveData<NewsDetailPojo>()

    fun getNewsLis(): MutableLiveData<ArrayList<Int>> {
        return newsIdlIst
    }

    fun getPickTittle(): MutableLiveData<NewsDetailPojo> {
        return pickTittle
    }

    fun starANews(data: NewsDetailPojo) {
        pickTittle.value = data
    }

    fun getData(context : Context){
        var newsIdListResponse = ArrayList<Int>()
        NewsModel(getApplication()).getALlNewsId(
            succesListener = Response.Listener {
                for ( i in 0..it.length()-1){
                    newsIdListResponse.add(it[i] as Int)
                    Log.d("ID",it[i].toString())
                }
                newsIdlIst.value = newsIdListResponse
            },erorListener = Response.ErrorListener {
                Log.d("Eror", it.toString())
            }

        )
    }

}