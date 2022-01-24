package io.github.bonarmada.gw_challenge.data.api

import io.github.bonarmada.gw_challenge.base.BaseResponse
import io.github.bonarmada.gw_challenge.data.model.JobDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodWorkApiService {

    @GET("jobs")
    fun getJobs(
        @Query("page") page: Int
    ): Single<BaseResponse<JobDTO>>
}