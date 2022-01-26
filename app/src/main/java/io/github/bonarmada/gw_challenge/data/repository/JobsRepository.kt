package io.github.bonarmada.gw_challenge.data.repository

import io.github.bonarmada.gw_challenge.data.db.CompanyDao
import io.github.bonarmada.gw_challenge.data.db.JobDao
import io.github.bonarmada.gw_challenge.data.db.models.CompanyDB
import io.github.bonarmada.gw_challenge.data.db.models.JobDB
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import io.github.bonarmada.gw_challenge.domain.Paging
import io.github.bonarmada.gw_challenge.domain.models.Company
import io.github.bonarmada.gw_challenge.domain.models.Job
import io.github.bonarmada.gw_challenge.ui.features.home.JobUIRepresentation
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val remoteDataSource: JobsRemoteDataSource,
    private val companyDao: CompanyDao,
    private val jobDao: JobDao
) {
    fun getJobs(
        page: Int,
        vararg categories: String
    ): Single<Paging<Job>> {
        return remoteDataSource.getJobs(
            page = page,
            categories = categories
        )
    }

    fun addJobToFavorites(
        job: Job
    ): Completable {
        return jobDao.addToFavorites(JobDB.fromJob(job))
    }

    fun removeJobFromFavourites(
        job: Job
    ): Completable {
        return jobDao.deleteFromFavorites(job.id)
    }

    fun getFavoriteJob(id: Int): Flowable<Job> {
        return jobDao.getJob(id).map { JobDB.toJob(it) }
    }

    fun getFavoriteJobs(): Flowable<List<Job>> {
        return jobDao.getFavoriteJobs().map { list ->
            list.map {
                JobDB.toJob(it)
            }
        }
    }

    fun getCompany(
        id: Int
    ): Flowable<Company> {
        return companyDao.getCompany(id).map { CompanyDB.toCompany(it) }
    }

    fun getCompanyFromRemote(id: Int): Completable {
        return remoteDataSource.getCompany(id = id).flatMapCompletable {
            companyDao.insert(CompanyDB.fromCompany(it))
        }
    }
}