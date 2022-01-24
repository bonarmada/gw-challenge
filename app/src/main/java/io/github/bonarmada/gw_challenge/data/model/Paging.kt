package io.github.bonarmada.gw_challenge.data.model

data class Paging<T>(
    val list: List<T>,
    val nextPage: Int? = null
)
