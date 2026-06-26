/**
 * Ejercicio 2: Find, Any y All
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio2FindAnyAllTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 */

data class Tarea(
    val id: Int,
    val titulo: String,
    val prioridad: Int, // 1 = baja, 2 = media, 3 = alta
    val completada: Boolean,
    val etiquetas: List<String>,
    val tiempoEstimadoHoras: Int,
)

data class EstadoProyecto(
    val hayTareasCriticasPendientes: Boolean,
    val totalHorasPendientes: Int,
    val todosLosBugsResueltos: Boolean
)

class GestorTareas {
    // Parte A: Operaciones con Find

    fun encontrarPrimeraTareaUrgente(tareas: List<Tarea>): Tarea? {
        TODO("Implementar: Debe encontrar la primera tarea con prioridad 3")
    }

    fun buscarPorId(
        tareas: List<Tarea>,
        id: Int,
    ): Tarea? {
        TODO("Implementar: Debe encontrar la tarea con el ID especificado")
    }

    fun encontrarTareaPendienteConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Tarea? {
        TODO("Implementar: Debe encontrar la primera tarea no completada con la etiqueta especificada")
    }

    // Parte B: Operaciones con Any

    fun hayTareasUrgentesPendientes(tareas: List<Tarea>): Boolean {
        TODO("Implementar: Debe verificar si hay alguna tarea urgente (prioridad 3) sin completar")
    }

    fun hayTareasQueSuperanHoras(
        tareas: List<Tarea>,
        horasLimite: Int,
    ): Boolean {
        TODO("Implementar: Debe verificar si alguna tarea supera el límite de horas especificado")
    }

    fun existeTareaConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Boolean {
        TODO("Implementar: Debe verificar si existe alguna tarea con la etiqueta especificada")
    }

    // Parte C: Operaciones con All

    fun todasCompletadas(tareas: List<Tarea>): Boolean {
        TODO("Implementar: Debe verificar si todas las tareas están completadas")
    }

    fun todasTienenEtiquetas(tareas: List<Tarea>): Boolean {
        TODO("Implementar: Debe verificar si todas las tareas tienen al menos una etiqueta")
    }

    fun todasDentroDeHoras(
        tareas: List<Tarea>,
        horasMaximo: Int,
    ): Boolean {
        TODO("Implementar: Debe verificar si todas las tareas están dentro del límite de horas")
    }

    // Parte D: Combinación de Find, Any y All

    fun proyectoListoParaEntrega(tareas: List<Tarea>): Boolean {
        TODO(
            """
            Implementar: Un proyecto está listo si:
            - Todas las tareas de prioridad alta (3) están completadas
            - No hay ninguna tarea pendiente con etiqueta "blocker"
            - Existe al menos una tarea de documentación completada
        """,
        )
    }

    fun generarResumenEstado(tareas: List<Tarea>): EstadoProyecto {
        TODO(
            """
            Implementar: Debe generar un resumen con:
            - hayTareasCriticasPendientes: si hay tareas de prioridad 3 sin completar
            - totalHorasPendientes: suma de horas de tareas no completadas
            - todosLosBugsResueltos: si todas las tareas con etiqueta "bug" están completadas
        """,
        )
    }
}

