package io.github.bonarmada.gw_challenge.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.bonarmada.gw_challenge.data.api.GoodWorkApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {

    @Provides
    @Singleton
    internal fun provideGoodWorkApiService(retrofit: Retrofit): GoodWorkApiService = retrofit.create(
        GoodWorkApiService::class.java)
}