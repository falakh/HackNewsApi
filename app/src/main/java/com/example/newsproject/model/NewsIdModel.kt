package com.example.newsproject.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONObject


class NewsModel(val context: Context) {

    private val getAllNewsIdUrl = "https://hacker-news.firebaseio.com/v0/topstories.json"
    fun getALlNewsId(
        succesListener: Response.Listener<JSONArray>,
        erorListener: Response.ErrorListener
    ) {
        val url = getAllNewsIdUrl
        val newsIdWebRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            succesListener,
            erorListener
        )
        Volley.newRequestQueue(context).add(newsIdWebRequest)
    }

    fun getNewsDetail(newsId : Int,succesListener: Response.Listener<JSONObject>,
                      erorListener: Response.ErrorListener){
        val url ="https://hacker-news.firebaseio.com/v0/item/${newsId}.json?print=pretty"
        val newsDetailWebRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            succesListener,
            erorListener
        )
        Volley.newRequestQueue(context).add(newsDetailWebRequest)
    }
}