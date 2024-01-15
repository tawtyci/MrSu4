package com.example.mrsu.domain.models

import com.example.mrsu.domain.models.Lessons
import com.google.gson.annotations.SerializedName


data class TimeTable (

  @SerializedName("Date"    ) var Date    : String?            = null,
  @SerializedName("Lessons" ) var Lessons : ArrayList<Lessons> = arrayListOf()

)