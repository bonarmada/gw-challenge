package io.github.bonarmada.gw_challenge.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val results: List<T>,
    val page: Int,
    @SerializedName("page_count") val pageCount: Int
)