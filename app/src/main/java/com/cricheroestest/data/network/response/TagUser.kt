package com.cricheroestest.data.network.response


import com.google.gson.annotations.SerializedName

data class TagUser(
    @SerializedName("name")
    val name: String?,
    @SerializedName("player_id")
    val playerId: String?
)