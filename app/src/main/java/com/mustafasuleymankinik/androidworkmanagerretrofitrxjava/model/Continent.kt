package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model

import com.google.gson.annotations.SerializedName

data class Continent(
    @SerializedName("continent")
    var continent_name:String,
    @SerializedName("province")
    var continent_province:String,
    @SerializedName("population")
    var continent_population:Int,
    @SerializedName("logo")
    var continent_logo:String

)