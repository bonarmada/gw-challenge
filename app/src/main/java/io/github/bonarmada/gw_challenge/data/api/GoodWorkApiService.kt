package io.github.bonarmada.gw_challenge.data.api

import io.github.bonarmada.gw_challenge.base.BaseResponse
import io.github.bonarmada.gw_challenge.data.model.CompanyDTO
import io.github.bonarmada.gw_challenge.data.model.JobDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoodWorkApiService {

    @GET("jobs")
    fun getJobs(
        @Query("page") page: Int,
        @Query("category") vararg category: String,
    ): Single<BaseResponse<JobDTO>>

    @GET("companies/{id}")
    fun getCompany(
        @Path("id") companyId: Int
    ): Single<CompanyDTO>
}