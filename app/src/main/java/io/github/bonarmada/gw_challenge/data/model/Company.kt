package io.github.bonarmada.gw_challenge.data.model


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
    val displayLocation = locations.getOrNull(0)?.name.orEmpty()

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
        val landingPage: String?,
        val jobsPage: String?,
        val miniF1Image: String?,
        val f2Image: String?,
        val logoImage: String?,
        val f1Image: String?,
        val f3Image: String?
    )
}