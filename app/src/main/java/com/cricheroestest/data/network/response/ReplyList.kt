package com.cricheroestest.data.network.response


import com.google.gson.annotations.SerializedName

data class ReplyList(
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("comment_id")
    val commentId: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("is_liked")
    val isLiked: Int?,
    @SerializedName("is_loved")
    val isLoved: Int?,
    @SerializedName("is_player_pro")
    val isPlayerPro: Int?,
    @SerializedName("is_respected")
    val isRespected: Int?,
    @SerializedName("is_sad")
    val isSad: Int?,
    @SerializedName("is_wellplayed")
    val isWellplayed: Int?,
    @SerializedName("is_wow")
    val isWow: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_photo")
    val profilePhoto: String?,
    @SerializedName("tag_user")
    val tagUser: List<TagUser>?,
    @SerializedName("time_ago")
    val timeAgo: String?,
    @SerializedName("total_like")
    val totalLike: Int?,
    @SerializedName("total_love")
    val totalLove: Int?,
    @SerializedName("total_reaction")
    val totalReaction: Int?,
    @SerializedName("total_respect")
    val totalRespect: Int?,
    @SerializedName("total_sad")
    val totalSad: Int?,
    @SerializedName("total_wellplay")
    val totalWellplay: Int?,
    @SerializedName("total_wow")
    val totalWow: Int?,
    @SerializedName("user_id")
    val userId: Int?
)