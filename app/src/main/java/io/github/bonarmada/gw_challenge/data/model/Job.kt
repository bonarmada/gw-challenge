package io.github.bonarmada.gw_challenge.data.model

data class Job(
    val contents: String,
    val name: String,
    val type: String,
    val publicationDate: String,
    val shortName: String,
    val modelType: String,
    val id: Int,
    val locations: List<Location>,
    val categories: List<Category>,
    val levels: List<Level>,
    val refs: Refs?,
    val company: Company?
) {

    data class Location(
        val name: String
    )

    data class Category(
        val name: String
    )

    data class Level(
        val name: String,
        val shortName: String
    )

    data class Refs(
        val landingPage: String
    )

    data class Company(
        val id: Int,
        val shortName: String,
        val name: String
    )
}