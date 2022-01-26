package io.github.bonarmada.gw_challenge.data.db.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.bonarmada.gw_challenge.domain.models.Job

@Entity(tableName = "favorite_jobs")
data class JobDB(
    @PrimaryKey val id: Int,
    val jobName: String,
    val contents: String,
    val jobLandingPageUrl: String,
    val location: String,
    val category: String,

    val companyName: String,
    val companyId: Int,
) {
    companion object {
        fun fromJob(job: Job): JobDB {
            return with(job) {
                JobDB(
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

        fun toJob(job: JobDB): Job {
            return with(job) {
                Job.empty().copy(
                    id = id,
                    name = jobName,
                    contents = contents,
                    refs = Job.Refs(jobLandingPageUrl),
                    locations = listOf(Job.Location(location)),
                    categories = category.split("|").map { Job.Category(it) },
                    company = Job.Company(
                        id = companyId,
                        name = companyName,
                        shortName = companyName
                    )
                )
            }
        }
    }
}