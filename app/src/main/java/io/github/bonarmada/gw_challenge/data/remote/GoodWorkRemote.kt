package io.github.bonarmada.gw_challenge.data.remote

import io.github.bonarmada.gw_challenge.base.BaseResponse
import io.github.bonarmada.gw_challenge.data.model.Job
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodWorkRemote {

    @GET("jobs")
    fun getJobs(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<BaseResponse<Job>>

}