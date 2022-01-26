package io.github.bonarmada.gw_challenge.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bonarmada.gw_challenge.domain.models.Company

@Entity(tableName = "companies")
data class CompanyDB(
    @PrimaryKey val id: Int,
    val name: String,
    val location: String,
    val logoUrl: String
) {
    companion object {
        fun fromCompany(company: Company): CompanyDB {
            return with(company) {
                CompanyDB(
                    id = id,
                    name = name,
                    location = locations.getOrNull(0)?.name.orEmpty(),
                    logoUrl = refs?.logoImage.orEmpty()
                )
            }
        }

        fun asCompany(company: CompanyDB): Company {
            return with(company) {
                Company.empty().copy(
                    id = id,
                    name = name,
                    locations = listOf(Company.Location(location)),
                    refs = Company.Refs(logoImage = logoUrl)
                )
            }
        }

    }
}
