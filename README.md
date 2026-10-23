# Eva2_DevMovil

# PetMatch - Desarrollo de Aplicaciones Móviles (DSY1105)

## Descripción
PetMatch es una aplicación móvil desarrollada en Android Studio con Kotlin y Jetpack Compose que permite registrar y visualizar mascotas, demostrando la aplicación de principios de usabilidad, arquitectura MVVM, persistencia local (Room) y acceso a recursos nativos (Galería).

## Funcionalidades Implementadas
- **Interfaz Visual:** Diseño estructurado con Material Design 3, jerarquía clara y adaptabilidad.
- **Formularios Validados:** Validación centralizada en el ViewModel, con mensajes de error visuales e íconos.
- **Gestión de Estado:** Uso de `StateFlow` y `State` para reflejar cambios en la UI en tiempo real.
- **Animaciones:** Uso de `AnimatedVisibility` para la retroalimentación visual de errores.
- **Persistencia Local:** Base de datos SQLite mediante Room (Insertar y recuperar datos).
- **Recursos Nativos:** Integración de la Galería (ActivityResultContracts) para seleccionar la foto de la mascota.
- **Navegación:** Flujo coherente entre el listado y el formulario.

## Arquitectura
El proyecto sigue el patrón **MVVM** (Model-View-ViewModel) para garantizar la mantenibilidad y desacoplar la lógica de la interfaz.

## Pasos para Ejecutar
1. Clonar el repositorio.
2. Abrir en Android Studio (versión Hedgehog o superior).
3. Sincronizar Gradle.
4. Ejecutar en un emulador o dispositivo físico (API 24+).

## Estudiantes
