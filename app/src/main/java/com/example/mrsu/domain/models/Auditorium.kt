package com.example.mrsu.domain.models

import com.google.gson.annotations.SerializedName


data class Auditorium (

  @SerializedName("Id"          ) var Id          : Int?    = null,
  @SerializedName("Number"      ) var Number      : String? = null,
  @SerializedName("Title"       ) var Title       : String? = null,
  @SerializedName("CampusId"    ) var CampusId    : Int?    = null,
  @SerializedName("CampusTitle" ) var CampusTitle : String? = null

)