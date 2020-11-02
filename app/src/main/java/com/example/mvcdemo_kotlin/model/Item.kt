package com.example.mvcdemo_kotlin.model


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("answer_count")
    var answerCount: Int,
    @SerializedName("content_license")
    var contentLicense: String,
    @SerializedName("creation_date")
    var creationDate: Int,
    @SerializedName("is_answered")
    var isAnswered: Boolean,
    @SerializedName("last_activity_date")
    var lastActivityDate: Int,
    @SerializedName("link")
    var link: String,
    @SerializedName("owner")
    var owner: Owner,
    @SerializedName("question_id")
    var questionId: Int,
    @SerializedName("score")
    var score: Int,
    @SerializedName("tags")
    var tags: List<String>,
    @SerializedName("title")
    var title: String,
    @SerializedName("view_count")
    var viewCount: Int
)