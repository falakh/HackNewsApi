package com.example.newsproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsDetailPojo(    val by : String,
                         val descendants : Int,
                         val id : Int,
                         val kids : List<Int>?,
                         val score : Int,
                         val time : Int,
                         val title : String,
                         val type : String,
                         val url : String) : Parcelable {
}
