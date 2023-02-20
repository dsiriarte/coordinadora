package com.davidsantiagoiriarte.data.localstorage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.davidsantiagoiriarte.data.localstorage.entities.DBUnidad

@Dao
interface UnidadesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(unidades: List<DBUnidad>)
}
