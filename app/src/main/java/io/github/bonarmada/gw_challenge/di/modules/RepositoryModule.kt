package io.github.bonarmada.gw_challenge.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.bonarmada.gw_challenge.data.db.CompanyDao
import io.github.bonarmada.gw_challenge.data.db.JobDao
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import io.github.bonarmada.gw_challenge.data.repository.JobsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideJobsRepository(
        jobsRemoteDataSource: JobsRemoteDataSource,
        companyDao: CompanyDao,
        jobDao: JobDao
    ): JobsRepository =
        JobsRepository(jobsRemoteDataSource, companyDao, jobDao)
}