package io.github.bonarmada.gw_challenge.data.repository

import io.github.bonarmada.gw_challenge.data.model.Company
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.data.model.Paging
import io.github.bonarmada.gw_challenge.data.model.asCompany
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val remoteDataSource: JobsRemoteDataSource
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

    fun getCompany(id: Int): Single<Company> {
        return remoteDataSource.getCompany(
            id = id
        )
    }
}