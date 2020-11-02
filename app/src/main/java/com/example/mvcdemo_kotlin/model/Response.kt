package com.example.mvcdemo_kotlin.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("has_more")
    var hasMore: Boolean,
    @SerializedName("items")
    var items: List<Item>,
    @SerializedName("quota_max")
    var quotaMax: Int,
    @SerializedName("quota_remaining")
    var quotaRemaining: Int
)