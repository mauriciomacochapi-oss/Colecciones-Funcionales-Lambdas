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
        return tareas.find { it.prioridad == 3 }
    }

    fun buscarPorId(
        tareas: List<Tarea>,
        id: Int,
    ): Tarea? {
        return tareas.find { it.id == id }
    }

    fun encontrarTareaPendienteConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Tarea? {
        return tareas.find { !it.completada && it.etiquetas.contains(etiqueta) }
    }

    // Parte B: Operaciones con Any

    fun hayTareasUrgentesPendientes(tareas: List<Tarea>): Boolean {
        return tareas.any { it.prioridad == 3 && !it.completada }
    }

    fun hayTareasQueSuperanHoras(
        tareas: List<Tarea>,
        horasLimite: Int,
    ): Boolean {
        return tareas.any { it.tiempoEstimadoHoras > horasLimite }
    }

    fun existeTareaConEtiqueta(
        tareas: List<Tarea>,
        etiqueta: String,
    ): Boolean {
        return tareas.any { it.etiquetas.contains(etiqueta) }
    }

    // Parte C: Operaciones con All

    fun todasCompletadas(tareas: List<Tarea>): Boolean {
        return tareas.all { it.completada }
    }

    fun todasTienenEtiquetas(tareas: List<Tarea>): Boolean {
        return tareas.all { it.etiquetas.isNotEmpty() }
    }

    fun todasDentroDeHoras(
        tareas: List<Tarea>,
        horasMaximo: Int,
    ): Boolean {
        return tareas.all { it.tiempoEstimadoHoras <= horasMaximo }
    }

    // Parte D: Combinación de Find, Any y All

    fun proyectoListoParaEntrega(tareas: List<Tarea>): Boolean {
        val altasCompletadas = tareas.filter { it.prioridad == 3 }.all { it.completada }
        val sinBlockersPendientes = !tareas.any { !it.completada && it.etiquetas.contains("blocker") }
        val tieneDocsCompletada = tareas.any { it.completada && it.etiquetas.contains("docs") }
        return altasCompletadas && sinBlockersPendientes && tieneDocsCompletada
    }

    fun generarResumenEstado(tareas: List<Tarea>): EstadoProyecto {
        val criticasPendientes = tareas.any { it.prioridad == 3 && !it.completada }
        val horasPendientes = tareas.filter { !it.completada }.sumOf { it.tiempoEstimadoHoras }
        val bugs = tareas.filter { it.etiquetas.contains("bug") }
        val bugsResueltos = bugs.isEmpty() || bugs.all { it.completada }

        return EstadoProyecto(
            hayTareasCriticasPendientes = criticasPendientes,
            totalHorasPendientes = horasPendientes,
            todosLosBugsResueltos = bugsResueltos
        )

    }
}

