package io.github.bonarmada.gw_challenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.bonarmada.gw_challenge.data.db.models.CompanyDB
import io.github.bonarmada.gw_challenge.data.db.models.JobDB
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(job: JobDB): Completable

    @Query("DELETE FROM favorite_jobs WHERE id = :id")
    fun deleteFromFavorites(id: Int): Completable

    @Query("SELECT * FROM favorite_jobs WHERE id IN (:id) LIMIT 1")
    fun getJob(id: Int): Flowable<JobDB>

    @Query("SELECT * FROM favorite_jobs")
    fun getFavoriteJobs(): Flowable<List<JobDB>>
} 