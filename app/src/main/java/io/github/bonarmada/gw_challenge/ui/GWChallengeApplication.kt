package io.github.bonarmada.gw_challenge.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.bonarmada.gw_challenge.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class GWChallengeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}