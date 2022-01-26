package io.github.bonarmada.gw_challenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.bonarmada.gw_challenge.data.model.CompanyDB
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(company: CompanyDB): Completable

    @Query("SELECT * FROM companies WHERE id IN (:id) LIMIT 1")
    fun getCompany(id: Int): Single<CompanyDB>
} 