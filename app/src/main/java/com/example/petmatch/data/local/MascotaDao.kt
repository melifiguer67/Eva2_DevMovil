package com.example.petmatch.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.petmatch.model.Mascota
import kotlinx.coroutines.flow.Flow

@Dao
interface MascotaDao {
    @Insert
    suspend fun insertar(mascota: Mascota)

    @Query("SELECT * FROM mascotas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<Mascota>>
}
