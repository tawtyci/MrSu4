package com.example.mrsu.domain.models


import com.google.gson.annotations.SerializedName


data class TimeTableToDate (

  @SerializedName("Group"           ) var Group           : String?    = null,
  @SerializedName("PlanNumber"      ) var PlanNumber      : String?    = null,
  @SerializedName("FacultyName"     ) var FacultyName     : String?    = null,
  @SerializedName("TimeTableBlockd" ) var TimeTableBlockd : Int?       = null,
  @SerializedName("TimeTable"       ) var TimeTable       : TimeTable? = TimeTable()

)