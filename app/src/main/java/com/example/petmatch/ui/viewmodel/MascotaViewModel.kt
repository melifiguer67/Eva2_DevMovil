package com.example.petmatch.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.petmatch.data.MascotaRepository
import com.example.petmatch.model.Mascota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FormUiState(
    val nombre: String = "",
    val especie: String = "",
    val edad: String = "",
    val fotoUri: String? = null,
    val nombreError: String? = null,
    val edadError: String? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)

class MascotaViewModel(private val repository: MascotaRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    val listaMascotas = repository.todasLasMascotas

    fun onNombreChange(nombre: String) {
        _uiState.update { it.copy(nombre = nombre, nombreError = null) }
    }

    fun onEspecieChange(especie: String) {
        _uiState.update { it.copy(especie = especie) }
    }

    fun onEdadChange(edad: String) {
        _uiState.update { it.copy(edad = edad, edadError = null) }
    }

    fun onFotoChange(uri: String?) {
        _uiState.update { it.copy(fotoUri = uri) }
    }

    private fun validarFormulario(): Boolean {
        var isValid = true
        val currentState = _uiState.value

        if (currentState.nombre.isBlank()) {
            _uiState.update { it.copy(nombreError = "El nombre es obligatorio (╯°□°）╯︵ ┻━┻") }
            isValid = false
        }

        val edadInt = currentState.edad.toIntOrNull()
        if (edadInt == null || edadInt <= 0) {
            _uiState.update { it.copy(edadError = "Ingresa una edad válida (O_O)") }
            isValid = false
        }

        return isValid
    }

    fun guardarMascota() {
        if (!validarFormulario()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            val state = _uiState.value
            val nuevaMascota = Mascota(
                nombre = state.nombre,
                especie = state.especie,
                edad = state.edad,
                fotoUri = state.fotoUri
            )
            repository.insertarMascota(nuevaMascota)

            _uiState.update { it.copy(isSaving = false, saveSuccess = true) }
            limpiarFormulario()
        }
    }

    fun limpiarFormulario() {
        _uiState.value = FormUiState()
    }
}

class MascotaViewModelFactory(private val repository: MascotaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MascotaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MascotaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
