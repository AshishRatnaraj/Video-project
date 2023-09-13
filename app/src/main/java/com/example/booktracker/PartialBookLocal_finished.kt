package com.example.booktracker

import androidx.room.ColumnInfo

class PartialBookLocal_finished (
    @ColumnInfo(name ="r_id")
  val id: Int,
    @ColumnInfo(name="r_finished")
    val finished:Boolean = false
)