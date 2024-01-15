package com.example.mrsu.domain.models

import com.example.mrsu.domain.models.Disciplines
import com.google.gson.annotations.SerializedName


data class Lessons (

  @SerializedName("Number"        ) var Number        : Byte?                   = null,
  @SerializedName("SubgroupCount" ) var SubgroupCount : Byte?                   = null,
  @SerializedName("Disciplines"   ) var Disciplines   : ArrayList<Disciplines> = arrayListOf()

)