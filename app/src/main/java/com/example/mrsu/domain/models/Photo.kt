package com.example.mrsu.domain.models

import com.google.gson.annotations.SerializedName


data class Photo (

  @SerializedName("UrlSmall"  ) var UrlSmall  : String? = null,
  @SerializedName("UrlMedium" ) var UrlMedium : String? = null,
  @SerializedName("UrlSource" ) var UrlSource : String? = null

)