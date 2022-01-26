package io.github.bonarmada.gw_challenge.data.remote.models

import com.google.gson.annotations.SerializedName
import io.github.bonarmada.gw_challenge.domain.models.Job

data class JobDTO(
    @SerializedName("contents") val contents: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("publication_date") val publicationDate: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("model_type") val modelType: String,
    @SerializedName("id") val id: Int,
    @SerializedName("locations") val locationsDTO: List<LocationDTO>?,
    @SerializedName("categories") val categoriesDTO: List<CategoryDTO>?,
    @SerializedName("levels") val levelsDTO: List<LevelDTO>?,
    @SerializedName("refs") val refsDTO: RefsDTO?,
    @SerializedName("company") val company: CompanyDTO?
) {

    data class LocationDTO(
        @SerializedName("name")
        val name: String
    )

    data class CategoryDTO(
        @SerializedName("name")
        val name: String
    )

    data class LevelDTO(
        @SerializedName("name")
        val name: String,
        @SerializedName("short_name")
        val shortName: String
    )

    data class RefsDTO(
        @SerializedName("landing_page")
        val landingPage: String
    )

    data class CompanyDTO(
        @SerializedName("id")
        val id: Int,
        @SerializedName("short_name")
        val shortName: String,
        @SerializedName("name")
        val name: String
    )
}

fun JobDTO.asJob(): Job {
    return Job(
        contents = contents,
        name = name,
        type = type,
        publicationDate = publicationDate,
        shortName = shortName,
        modelType = modelType,
        id = id,
        locations = locationsDTO?.map { it.asLocation() } ?: listOf(),
        categories = categoriesDTO?.map { it.asCategory() } ?: listOf(),
        levels = levelsDTO?.map { it.asLevel() } ?: listOf(),
        refs = refsDTO?.asRefs(),
        company = company?.asCompany()
    )
}

fun JobDTO.LocationDTO.asLocation(): Job.Location {
    return Job.Location(
        name = name
    )
}

fun JobDTO.CategoryDTO.asCategory(): Job.Category {
    return Job.Category(
        name = name
    )
}

fun JobDTO.LevelDTO.asLevel(): Job.Level {
    return Job.Level(
        name = name,
        shortName = shortName
    )
}


fun JobDTO.RefsDTO.asRefs(): Job.Refs {
    return Job.Refs(
        landingPage = landingPage
    )
}


fun JobDTO.CompanyDTO.asCompany(): Job.Company {
    return Job.Company(
        id = id,
        shortName = shortName,
        name = name
    )
}