package com.example.newsproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.example.newsproject.NewsDetail
import com.example.newsproject.R
import com.example.newsproject.model.NewsDetailPojo
import com.example.newsproject.model.NewsModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.newsinfo.*
import kotlinx.android.synthetic.main.newsinfo.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class NewsLIstAdapter(val detailNewsList : ArrayList<Int>) : RecyclerView.Adapter<NewsLIstAdapter.NewsListViewHolder>()  {
    var temp = HashMap<Int,NewsDetailPojo>()
    lateinit var context : Context;
    class  NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(x: NewsDetailPojo) {
            with(itemView){
                tvNewsTittle.text = x.title
                tvLikeCount.text = x.score.toString()
                tvViewCount.text = x.kids?.size.toString() ?: 0.toString()
                pbNewsInfoLoading.visibility = View.GONE
                tvNewsTittle.visibility = View.VISIBLE
                tvLikeCount.visibility = View.VISIBLE
                tvViewCount.visibility = View.VISIBLE
                setOnClickListener {
                    val intent = Intent(context,NewsDetail::class.java)
                    intent.putExtra(NewsDetail.key,x)
                    context.startActivity(intent)
                }
            }
        }
        fun showLoading(){
            with(itemView){
                tvNewsTittle.visibility = View.INVISIBLE
                tvLikeCount.visibility = View.INVISIBLE
                tvViewCount.visibility = View.INVISIBLE
                pbNewsInfoLoading.visibility = View.VISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        context = parent.context
        var view =   LayoutInflater.from(parent.context).inflate(R.layout.newsinfo,parent,false)
        return  NewsListViewHolder(view);
    }

    override fun getItemCount(): Int {
        return detailNewsList.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.showLoading()
        var key =  detailNewsList[position];
        if(temp.containsKey(key)){
            temp[key]?.let { holder.setItem(it) }
        }else{
            NewsModel(context).getNewsDetail(succesListener = Response.Listener {
                var gson = Gson().fromJson(it.toString(),NewsDetailPojo::class.java)
                temp[ detailNewsList[position]] = gson
                holder.setItem(gson)
            },erorListener = Response.ErrorListener {
                Log.d("EROR",it.toString())
            },newsId = detailNewsList[position]
            )
        }
    }
}