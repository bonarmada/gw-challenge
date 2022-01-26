package io.github.bonarmada.gw_challenge.data.model

import androidx.room.Entity

@Entity(tableName = "companies")
data class CompanyDB(
    val id: Int,
    val name: String,
    val location: String,
    val logoUrl: String
)

fun CompanyDB.asCompany(): Company {
    return Company.empty().copy(
        id = this.id,
        name = name,
        locations = listOf(Company.Location(location)),
        refs = Company.Refs(logoImage = logoUrl)
    )
}