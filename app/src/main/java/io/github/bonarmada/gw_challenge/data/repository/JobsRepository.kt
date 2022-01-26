package io.github.bonarmada.gw_challenge.data.repository

import io.github.bonarmada.gw_challenge.data.db.CompanyDao
import io.github.bonarmada.gw_challenge.data.model.*
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import timber.log.Timber
import javax.inject.Inject

class JobsRepository @Inject constructor(
    private val remoteDataSource: JobsRemoteDataSource,
    private val companyDao: CompanyDao
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


    fun getCompany(
        id: Int
    ): Flowable<Company> {

        return companyDao.getCompany(id).map { CompanyDB.asCompany(it) }
    }

    fun getCompanyFromRemote(id: Int): Completable {
        return remoteDataSource.getCompany(id = id).flatMapCompletable {
            companyDao.insert(CompanyDB.fromCompany(it))
        }
    }
}