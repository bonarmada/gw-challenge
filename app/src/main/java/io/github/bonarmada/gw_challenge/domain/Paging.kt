package io.github.bonarmada.gw_challenge.domain

data class Paging<T>(
    val list: List<T>,
    val nextPage: Int? = null
)
