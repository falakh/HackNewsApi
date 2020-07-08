package com.example.newsproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.example.newsproject.R
import com.example.newsproject.model.KomentarModel
import com.example.newsproject.model.NewsModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_news_detail.view.*
import kotlinx.android.synthetic.main.komen_kolom.view.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KomenLIstAdapter(val detailNewsList : List<Int>) : RecyclerView.Adapter<KomenLIstAdapter.NewsListViewHolder>()  {
    var temp = HashMap<Int,KomentarModel>()
    lateinit var context : Context;
    class  NewsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(x: KomentarModel) {
            with(itemView){
                pbKomentar.visibility = View.GONE
                tvKomenValue.visibility = View.VISIBLE
                tvKomenValue.text = x.text
            }
        }
        fun showLoading(){
            with(itemView){
                pbKomentar.visibility = View.VISIBLE
                tvKomenValue.visibility = View.INVISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        context = parent.context
        var view =   LayoutInflater.from(parent.context).inflate(R.layout.komen_kolom,parent,false)
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
                var komenValue = Gson().fromJson(it.toString(),KomentarModel::class.java)
                temp[ detailNewsList[position]] = komenValue
                holder.setItem(komenValue)
            },erorListener = Response.ErrorListener {
                Log.d("EROR",it.toString())
            },newsId = detailNewsList[position]
            )
        }
    }
}