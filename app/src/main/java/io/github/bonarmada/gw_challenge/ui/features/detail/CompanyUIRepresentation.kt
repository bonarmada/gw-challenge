package io.github.bonarmada.gw_challenge.ui.features.detail

import android.os.Parcelable
import io.github.bonarmada.gw_challenge.domain.models.Company
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyUIRepresentation(
    val id: Int,
    val name: String,
    val location: String,
    val logoUrl: String
) : Parcelable {
    companion object {
        fun fromCompany(company: Company): CompanyUIRepresentation {
            return with(company) {
                CompanyUIRepresentation(
                    id = id,
                    name = name,
                    location = locations.getOrNull(0)?.name.orEmpty(),
                    logoUrl = refs?.logoImage.orEmpty()
                )
            }
        }
    }
}