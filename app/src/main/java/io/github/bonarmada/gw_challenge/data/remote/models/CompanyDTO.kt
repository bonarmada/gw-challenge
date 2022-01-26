package io.github.bonarmada.gw_challenge.data.remote.models


import com.google.gson.annotations.SerializedName
import io.github.bonarmada.gw_challenge.domain.models.Company

data class CompanyDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("locations")
    val locationsDTO: List<LocationDTO>?,
    @SerializedName("industries")
    val industriesDTO: List<IndustryDTO>?,
    @SerializedName("tags")
    val tagsDTO: List<TagDTO>?,
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("publication_date")
    val publicationDate: String,
    @SerializedName("model_type")
    val modelType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("refs")
    val refsDTO: RefsDTO?
) {
    data class LocationDTO(
        @SerializedName("name")
        val name: String
    )

    data class IndustryDTO(
        @SerializedName("name")
        val name: String
    )

    data class TagDTO(
        @SerializedName("name")
        val name: String,
        @SerializedName("short_name")
        val shortName: String
    )

    data class RefsDTO(
        @SerializedName("landing_page") val landingPage: String?,
        @SerializedName("jobs_page") val jobsPage: String?,
        @SerializedName("mini_f1_image") val miniF1Image: String?,
        @SerializedName("f2_image") val f2Image: String?,
        @SerializedName("logo_image") val logoImage: String?,
        @SerializedName("f1_image") val f1Image: String?,
        @SerializedName("f3_image") val f3Image: String?
    )
}

fun CompanyDTO.asCompany(): Company {
    return Company(
        description = description,
        locations = locationsDTO?.map { it.asLocation() } ?: listOf(),
        industries = industriesDTO?.map { it.asIndustry() } ?: listOf(),
        tags = tagsDTO?.map { it.asTag() } ?: listOf(),
        shortName = shortName,
        name = name,
        publicationDate = publicationDate,
        modelType = modelType,
        id = id,
        refs = refsDTO?.asRefs()
    )
}

fun CompanyDTO.LocationDTO.asLocation(): Company.Location {
    return Company.Location(
        name = name
    )
}


fun CompanyDTO.IndustryDTO.asIndustry(): Company.Industry {
    return Company.Industry(
        name = name
    )
}


fun CompanyDTO.TagDTO.asTag(): Company.Tag {
    return Company.Tag(
        name = name,
        shortName = shortName
    )
}


fun CompanyDTO.RefsDTO.asRefs(): Company.Refs {
    return Company.Refs(
        landingPage = landingPage,
        jobsPage = jobsPage,
        miniF1Image = miniF1Image,
        f2Image = f2Image,
        logoImage = logoImage,
        f1Image = f1Image,
        f3Image = f3Image
    )
}
