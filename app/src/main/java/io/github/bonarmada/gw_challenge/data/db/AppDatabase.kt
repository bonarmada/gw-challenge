package io.github.bonarmada.gw_challenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.bonarmada.gw_challenge.data.db.models.CompanyDB
import io.github.bonarmada.gw_challenge.data.db.models.JobDB

@Database(entities = [CompanyDB::class, JobDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
    abstract fun jobDao(): JobDao
}