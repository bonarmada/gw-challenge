package io.github.bonarmada.gw_challenge.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.bonarmada.gw_challenge.data.db.AppDatabase
import io.github.bonarmada.gw_challenge.data.db.CompanyDao
import io.github.bonarmada.gw_challenge.data.db.JobDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Gw-challenge.db"
        ).build()
    }

    @Provides
    fun provideCompanyDao(appDatabase: AppDatabase): CompanyDao {
        return appDatabase.companyDao()
    }

    @Provides
    fun provideJobDao(appDatabase: AppDatabase): JobDao {
        return appDatabase.jobDao()
    }
}