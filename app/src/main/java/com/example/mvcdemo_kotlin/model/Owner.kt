package com.example.mvcdemo_kotlin.model


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("accept_rate")
    var acceptRate: Int,
    @SerializedName("display_name")
    var displayName: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("profile_image")
    var profileImage: String,
    @SerializedName("reputation")
    var reputation: Int,
    @SerializedName("user_id")
    var userId: Int,
    @SerializedName("user_type")
    var userType: String
)