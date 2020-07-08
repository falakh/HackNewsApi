package com.example.newsproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.newsproject.adapter.KomenLIstAdapter
import com.example.newsproject.model.NewsDetailPojo
import com.example.newsproject.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_news_detail.*
import java.text.SimpleDateFormat
import java.util.*

class NewsDetail : AppCompatActivity() {
    lateinit var newsDetail: NewsDetailPojo

    companion object {
        val key = "NEWS_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        this.newsDetail = intent.getParcelableExtra(key)
        initView()
    }

    fun initView() {
        with(newsDetail) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time.toLong()
            var formatter = SimpleDateFormat("DD/MM/YYYY")
            tvTime.text = formatter.format(calendar.time)
            tvAuthor.text = this.by
            tvDeskripsi.text = this.url
            textViewJudul.text = title
            rvKomenList.adapter = KomenLIstAdapter(kids ?: (listOf()))
            btnStar.setOnClickListener {
                ViewModelProviders.of(this@NewsDetail).get(MainActivityViewModel::class.java).starANews(newsDetail)
                Toast.makeText(this@NewsDetail,"Judl berhasil ditambahkan",Toast.LENGTH_LONG).show()
            }
        }
    }
}