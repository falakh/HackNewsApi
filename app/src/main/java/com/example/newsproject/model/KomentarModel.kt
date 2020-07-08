package com.example.newsproject.model

class KomentarModel( val by : String,
                     val id : Int,
                     val kids : List<Int>?,
                     val parent : Int,
                     val time : Int,
                     val text : String,
                     val type : String
                     ) {
}