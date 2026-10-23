package com.example.petmatch.data

import com.example.petmatch.data.local.MascotaDao
import com.example.petmatch.model.Mascota
import kotlinx.coroutines.flow.Flow

class MascotaRepository(private val dao: MascotaDao) {
    val todasLasMascotas: Flow<List<Mascota>> = dao.obtenerTodas()

    suspend fun insertarMascota(mascota: Mascota) {
        dao.insertar(mascota)
    }
}
