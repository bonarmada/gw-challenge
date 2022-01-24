package io.github.bonarmada.gw_challenge.data.repository

import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.data.model.Paging
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val remoteDataSource: JobsRemoteDataSource
) {
    fun getJobs(
        page: Int,
        perPage: Int
    ): Single<Paging<Job>> {
        return remoteDataSource.getJobs(
            page = page,
            perPage = perPage
        )
    }
}