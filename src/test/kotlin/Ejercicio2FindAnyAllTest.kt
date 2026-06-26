import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

/**
 * Ejercicio 2: Find, Any y All
 * 
 * En este ejercicio trabajarás con funciones de búsqueda y predicados
 * que permiten verificar condiciones en colecciones.
 * 
 * Objetivo: Implementar un sistema de gestión de tareas que utilice
 * find para búsquedas, any para verificaciones de existencia y
 * all para validaciones completas.
 */
class Ejercicio2FindAnyAllTest {


    @Nested
    @DisplayName("Parte A: Operaciones con Find")
    inner class FindOperations {

        @Test
        @DisplayName("Debe encontrar la primera tarea de alta prioridad")
        fun encontrarPrimeraTareaAltaPrioridad() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Revisar emails", 1, false, listOf("comunicación"), 1),
                Tarea(2, "Bug crítico", 3, false, listOf("desarrollo", "urgente"), 4),
                Tarea(3, "Documentación", 2, true, listOf("docs"), 2),
                Tarea(4, "Deploy a producción", 3, false, listOf("devops", "urgente"), 3)
            )

            val tareaUrgente = gestor.encontrarPrimeraTareaUrgente(tareas)

            assertNotNull(tareaUrgente)
            assertEquals(2, tareaUrgente?.id)
            assertEquals(3, tareaUrgente?.prioridad)
        }

        @Test
        @DisplayName("Debe encontrar tarea por ID")
        fun encontrarTareaPorId() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Tarea A", 1, false, listOf("test"), 1),
                Tarea(5, "Tarea B", 2, true, listOf("test"), 2),
                Tarea(10, "Tarea C", 3, false, listOf("test"), 3)
            )

            val tarea = gestor.buscarPorId(tareas, 5)

            assertNotNull(tarea)
            assertEquals("Tarea B", tarea?.titulo)
        }

        @Test
        @DisplayName("Debe encontrar primera tarea sin completar con etiqueta específica")
        fun encontrarTareaPendienteConEtiqueta() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Escribir tests", 2, true, listOf("desarrollo", "testing"), 3),
                Tarea(2, "Revisar PR", 2, false, listOf("desarrollo", "review"), 2),
                Tarea(3, "Actualizar deps", 1, false, listOf("mantenimiento"), 1),
                Tarea(4, "Refactoring", 2, false, listOf("desarrollo", "mejora"), 4)
            )

            val tareaPendiente = gestor.encontrarTareaPendienteConEtiqueta(tareas, "desarrollo")

            assertNotNull(tareaPendiente)
            assertEquals("Revisar PR", tareaPendiente?.titulo)
            assertFalse(tareaPendiente?.completada ?: true)
        }

        @Test
        @DisplayName("Debe retornar null si no encuentra coincidencias")
        fun retornarNullSiNoEncuentra() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Tarea A", 1, true, listOf("test"), 1),
                Tarea(2, "Tarea B", 1, true, listOf("test"), 2)
            )

            val tareaInexistente = gestor.buscarPorId(tareas, 999)

            assertNull(tareaInexistente)
        }
    }

    @Nested
    @DisplayName("Parte B: Operaciones con Any")
    inner class AnyOperations {

        @Test
        @DisplayName("Debe verificar si hay tareas urgentes pendientes")
        fun verificarTareasUrgentesPendientes() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Tarea normal", 1, false, listOf("regular"), 1),
                Tarea(2, "Tarea urgente completada", 3, true, listOf("urgente"), 2),
                Tarea(3, "Tarea urgente pendiente", 3, false, listOf("urgente"), 3)
            )

            val hayUrgentesPendientes = gestor.hayTareasUrgentesPendientes(tareas)

            assertTrue(hayUrgentesPendientes)
        }

        @Test
        @DisplayName("Debe verificar si alguna tarea excede tiempo límite")
        fun verificarTareasExcedenTiempo() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Tarea corta", 2, false, listOf(), 2),
                Tarea(2, "Tarea media", 2, false, listOf(), 4),
                Tarea(3, "Tarea larga", 2, false, listOf(), 10)
            )

            val hayTareasLargas = gestor.hayTareasQueSuperanHoras(tareas, 8)

            assertTrue(hayTareasLargas)
        }

        @Test
        @DisplayName("Debe verificar si existe alguna tarea con etiqueta específica")
        fun verificarExistenciaEtiqueta() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Frontend", 2, false, listOf("react", "ui"), 3),
                Tarea(2, "Backend", 2, false, listOf("api", "database"), 4),
                Tarea(3, "Testing", 1, false, listOf("qa", "testing"), 2)
            )

            val hayTareasBackend = gestor.existeTareaConEtiqueta(tareas, "database")
            val hayTareasMovil = gestor.existeTareaConEtiqueta(tareas, "mobile")

            assertTrue(hayTareasBackend)
            assertFalse(hayTareasMovil)
        }
    }

    @Nested
    @DisplayName("Parte C: Operaciones con All")
    inner class AllOperations {

        @Test
        @DisplayName("Debe verificar si todas las tareas están completadas")
        fun verificarTodasCompletadas() {
            val gestor = GestorTareas()
            val tareasCompletadas = listOf(
                Tarea(1, "Tarea A", 1, true, listOf(), 1),
                Tarea(2, "Tarea B", 2, true, listOf(), 2),
                Tarea(3, "Tarea C", 3, true, listOf(), 3)
            )
            val tareasMixtas = listOf(
                Tarea(1, "Tarea A", 1, true, listOf(), 1),
                Tarea(2, "Tarea B", 2, false, listOf(), 2)
            )

            val todasCompletadas = gestor.todasCompletadas(tareasCompletadas)
            val mixtasCompletadas = gestor.todasCompletadas(tareasMixtas)

            assertTrue(todasCompletadas)
            assertFalse(mixtasCompletadas)
        }

        @Test
        @DisplayName("Debe verificar si todas las tareas tienen etiquetas")
        fun verificarTodasTienenEtiquetas() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Tarea A", 1, false, listOf("tag1"), 1),
                Tarea(2, "Tarea B", 2, false, listOf("tag2", "tag3"), 2),
                Tarea(3, "Tarea C", 3, false, listOf(), 3)
            )

            val todasEtiquetadas = gestor.todasTienenEtiquetas(tareas)

            assertFalse(todasEtiquetadas)
        }

        @Test
        @DisplayName("Debe verificar si todas las tareas cumplen tiempo estimado")
        fun verificarTiempoEstimado() {
            val gestor = GestorTareas()
            val tareasCortas = listOf(
                Tarea(1, "Tarea A", 1, false, listOf(), 2),
                Tarea(2, "Tarea B", 2, false, listOf(), 3),
                Tarea(3, "Tarea C", 3, false, listOf(), 4)
            )

            val todasDentroLimite = gestor.todasDentroDeHoras(tareasCortas, 5)
            val todasDentroLimiteEstricto = gestor.todasDentroDeHoras(tareasCortas, 3)

            assertTrue(todasDentroLimite)
            assertFalse(todasDentroLimiteEstricto)
        }
    }

    @Nested
    @DisplayName("Parte D: Combinación de Find, Any y All")
    inner class CombinedOperations {

        @Test
        @DisplayName("Debe validar proyecto listo para entrega")
        fun validarProyectoListoParaEntrega() {
            val gestor = GestorTareas()
            val tareasProyecto = listOf(
                Tarea(1, "Desarrollo feature A", 3, true, listOf("desarrollo"), 5),
                Tarea(2, "Testing feature A", 3, true, listOf("qa"), 3),
                Tarea(3, "Documentación", 2, true, listOf("docs"), 2),
                Tarea(4, "Deploy", 3, true, listOf("devops"), 1)
            )

            // Un proyecto está listo si:
            // - Todas las tareas de prioridad alta (3) están completadas
            // - No hay ninguna tarea pendiente con etiqueta "blocker"
            // - Existe al menos una tarea de documentación completada
            val proyectoListo = gestor.proyectoListoParaEntrega(tareasProyecto)

            assertTrue(proyectoListo)
        }

        @Test
        @DisplayName("Debe generar resumen de estado del proyecto")
        fun generarResumenEstado() {
            val gestor = GestorTareas()
            val tareas = listOf(
                Tarea(1, "Bug crítico", 3, false, listOf("bug", "urgente"), 4),
                Tarea(2, "Feature nueva", 2, true, listOf("desarrollo"), 6),
                Tarea(3, "Optimización", 1, false, listOf("mejora"), 3),
                Tarea(4, "Hotfix", 3, true, listOf("bug", "urgente"), 2)
            )

            val resumen = gestor.generarResumenEstado(tareas)

            // El resumen debe indicar:
            // - Si hay tareas críticas pendientes
            // - El total de horas pendientes
            // - Si todas las tareas con etiqueta "bug" están resueltas
            assertEquals(
                EstadoProyecto(
                    hayTareasCriticasPendientes = true,
                    totalHorasPendientes = 7,
                    todosLosBugsResueltos = false
                ),
                resumen
            )
        }
    }

}
