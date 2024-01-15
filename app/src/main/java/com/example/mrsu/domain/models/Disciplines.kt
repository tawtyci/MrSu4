package com.example.mrsu.domain.models

import com.google.gson.annotations.SerializedName


data class Disciplines (

  @SerializedName("Id"             ) var Id             : Int?        = null,
  @SerializedName("Title"          ) var Title          : String?     = null,
  @SerializedName("Language"       ) var Language       : String?     = null,
  @SerializedName("LessonType"     ) var LessonType     : Int?        = null,
  @SerializedName("Remote"         ) var Remote         : Boolean?    = null,
  @SerializedName("Group"          ) var Group          : String?     = null,
  @SerializedName("SubgroupNumber" ) var SubgroupNumber : Byte?        = null,
  @SerializedName("Teacher"        ) var Teacher        : Teacher?    = Teacher(),
  @SerializedName("Auditorium"     ) var Auditorium     : Auditorium? = Auditorium()

)