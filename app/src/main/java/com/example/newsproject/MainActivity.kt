package com.example.newsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.newsproject.adapter.NewsLIstAdapter
import com.example.newsproject.repositori.DataRepository
import com.example.newsproject.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getNewsLis().observe(this, Observer {
            rvNewsList.adapter = (NewsLIstAdapter(it))
            if (it != null) {
                pbLinear.visibility = View.GONE
            } else {
                pbLinear.visibility = View.VISIBLE
            }
        })
        DataRepository.mCurrentNews.observe(this, Observer {
            if (it != null) {
                tvStarId.text = it.title
            } else {
                tvStarId.visibility = View.GONE
            }
        })
        mainActivityViewModel.getData(context = baseContext)

    }

}