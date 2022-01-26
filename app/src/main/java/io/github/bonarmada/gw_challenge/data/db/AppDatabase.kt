package io.github.bonarmada.gw_challenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.bonarmada.gw_challenge.data.model.CompanyDB

@Database(entities = [CompanyDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
}