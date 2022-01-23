package io.github.bonarmada.gw_challenge.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.bonarmada.gw_challenge.BASE_URL
import io.github.bonarmada.gw_challenge.data.remote.GoodWorkRemote
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Provides
    @Singleton
    internal fun provideGoodWorkRemote(retrofit: Retrofit): GoodWorkRemote = retrofit.create(
        GoodWorkRemote::class.java)
}