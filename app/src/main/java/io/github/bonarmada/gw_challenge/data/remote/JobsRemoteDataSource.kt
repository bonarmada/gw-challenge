package io.github.bonarmada.gw_challenge.data.remote

import io.github.bonarmada.gw_challenge.data.api.GoodWorkApiService
import io.github.bonarmada.gw_challenge.data.model.Job
import io.github.bonarmada.gw_challenge.data.model.Paging
import io.github.bonarmada.gw_challenge.data.model.asJob
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JobsRemoteDataSource @Inject constructor(
    private val apiService: GoodWorkApiService
) {
    fun getJobs(page: Int, perPage: Int): Single<Paging<Job>> {
        return apiService
            .getJobs(
                page = page,
                perPage = perPage
            )
            .map { response ->
                Paging(
                    response.data.map {
                        it.asJob()
                    },
                    if (response.page >= response.pageCount) {
                        null
                    } else {
                        response.page + 1
                    }
                )
            }
    }
}