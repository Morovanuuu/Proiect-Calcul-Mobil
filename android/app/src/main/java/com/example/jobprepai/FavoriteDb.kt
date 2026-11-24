package com.example.jobprepai

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    // Flow: lista se actualizeaza singura in UI cand se adauga/sterge un favorit
    @Query("SELECT * FROM favorite ORDER BY titlu")
    fun toate(): Flow<List<Job>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salveaza(job: Job)

    @Delete
    suspend fun sterge(job: Job)
}

@Database(entities = [Job::class], version = 1)
abstract class FavoriteDb : RoomDatabase() {
    abstract fun dao(): FavoriteDao

    companion object {
        @Volatile private var instanta: FavoriteDb? = null

        fun get(context: Context): FavoriteDb = instanta ?: synchronized(this) {
            instanta ?: Room.databaseBuilder(context.applicationContext, FavoriteDb::class.java, "jobprep.db")
                .build().also { instanta = it }
        }
    }
}
