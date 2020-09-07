package com.mustafasuleymankinik.androidworkmanagerretrofitrxjava.model

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("country")
    var country_name:String,
    @SerializedName("province")
    var country_province:String,
    @SerializedName("population")
    var country_population:Int,
    @SerializedName("logo")
    var country_logo:String

)

