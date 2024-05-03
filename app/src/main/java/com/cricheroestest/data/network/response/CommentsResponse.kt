package com.cricheroestest.data.network.response


import com.google.gson.annotations.SerializedName

data class CommentsResponse(
    @SerializedName("data")
    val `data`: List<CommentsList>?,
    @SerializedName("page")
    val page: PageX?,
    @SerializedName("status")
    val status: Boolean?
)