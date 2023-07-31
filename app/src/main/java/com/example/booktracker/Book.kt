package com.example.booktracker

import com.google.gson.annotations.SerializedName

data class Book(
 @SerializedName("r_id")
 val id: Int,
 @SerializedName("r_title")
 val title: String,
 @SerializedName("r_author")
 val author: String,
 @SerializedName("series")
 val series: String,
 @SerializedName("r_finished")
 val finished: Boolean = false
)




