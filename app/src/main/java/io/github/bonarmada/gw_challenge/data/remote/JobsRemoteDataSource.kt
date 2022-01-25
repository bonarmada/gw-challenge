package io.github.bonarmada.gw_challenge.data.remote

import io.github.bonarmada.gw_challenge.data.api.GoodWorkApiService
import io.github.bonarmada.gw_challenge.data.model.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JobsRemoteDataSource @Inject constructor(
    private val apiService: GoodWorkApiService
) {
    fun getJobs(
        page: Int,
        vararg categories: String
    ): Single<Paging<Job>> {
        return apiService
            .getJobs(
                page = page,
                *categories
            )
            .map { response ->
                Paging(
                    response.results.map {
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

    fun getCompany(id: Int): Single<Company> {
        return apiService
            .getCompany(companyId = id)
            .map {
                it.asCompany()
            }
    }
}