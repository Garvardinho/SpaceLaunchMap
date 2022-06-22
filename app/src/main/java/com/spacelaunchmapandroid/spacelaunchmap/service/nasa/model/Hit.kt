package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("_index")
    var index: String,

    @SerializedName("_id")
    var id: String,

    @SerializedName("_source")
    var source: Source
)
