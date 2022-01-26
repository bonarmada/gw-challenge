package io.github.bonarmada.gw_challenge.domain.models


data class Company(
    val description: String,
    val locations: List<Location>,
    val industries: List<Industry>,
    val tags: List<Tag>,
    val shortName: String,
    val name: String,
    val publicationDate: String,
    val modelType: String,
    val id: Int,
    val refs: Refs?
) {
    companion object {
        fun empty() = Company(
            "",
            listOf(),
            listOf(),
            listOf(),
            "",
            "",
            "",
            "",
            0,
            null
        )
    }

    data class Location(
        val name: String
    )

    data class Industry(
        val name: String
    )

    data class Tag(
        val name: String,
        val shortName: String
    )

    data class Size(
        val name: String,
        val shortName: String
    )

    data class Refs(
        val landingPage: String? = null,
        val jobsPage: String? = null,
        val miniF1Image: String? = null,
        val f2Image: String? = null,
        val logoImage: String? = null,
        val f1Image: String? = null,
        val f3Image: String? = null
    )
}