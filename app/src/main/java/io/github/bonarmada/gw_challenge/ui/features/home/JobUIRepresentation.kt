package io.github.bonarmada.gw_challenge.ui.features.home

import android.os.Parcelable
import io.github.bonarmada.gw_challenge.domain.models.Job
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobUIRepresentation(
    val id: Int,
    val jobName: String,
    val contents: String,
    val jobLandingPageUrl: String,
    val location: String,
    val category: String,

    val companyName: String,
    val companyId: Int
) : Parcelable {
    companion object {
        fun fromJob(job: Job): JobUIRepresentation {
            return with(job) {
                JobUIRepresentation(
                    id = id,
                    jobName = name,
                    contents = contents,
                    jobLandingPageUrl = refs?.landingPage.orEmpty(),
                    location = locations.getOrNull(0)?.name.orEmpty(),
                    category = categories.joinToString(separator = " | ") { it.name },
                    companyName = company?.name.orEmpty(),
                    companyId = company?.id ?: 0
                )
            }
        }
    }
}