package io.github.bonarmada.gw_challenge.data.model


import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("contents") val contents: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("publication_date") val publicationDate: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("model_type") val modelType: String,
    @SerializedName("id") val id: Int,
    @SerializedName("locations") val locations: List<Location>,
    @SerializedName("categories") val categories: List<Category>,
    @SerializedName("levels") val levels: List<Level>,
    @SerializedName("tags") val tags: List<Any>,
    @SerializedName("refs") val refs: Refs,
    @SerializedName("company") val company: Company
) {
    data class Location(
        @SerializedName("name")
        val name: String
    )

    data class Category(
        @SerializedName("name")
        val name: String
    )

    data class Level(
        @SerializedName("name")
        val name: String,
        @SerializedName("short_name")
        val shortName: String
    )

    data class Refs(
        @SerializedName("landing_page")
        val landingPage: String
    )

    data class Company(
        @SerializedName("id")
        val id: Int,
        @SerializedName("short_name")
        val shortName: String,
        @SerializedName("name")
        val name: String
    )
}