package io.github.bonarmada.gw_challenge.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.bonarmada.gw_challenge.data.api.GoodWorkApiService
import io.github.bonarmada.gw_challenge.data.remote.JobsRemoteDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {

    @Provides
    @Singleton
    internal fun provideJobsRemoteDataSource(apiService: GoodWorkApiService): JobsRemoteDataSource =
        JobsRemoteDataSource(apiService)
}