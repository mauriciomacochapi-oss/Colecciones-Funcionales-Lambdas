/**
 * Ejercicio 4: Funciones como Argumentos
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio4FuncionesComoArgumentosTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 */

enum class TipoTransaccion { INGRESO, EGRESO }

enum class EstadoTransaccion { PENDIENTE, PROCESADA, RECHAZADA }

data class Transaccion(
    val id: String,
    val monto: Double,
    val tipo: TipoTransaccion,
    val categoria: String,
    val fecha: String, // Formato: "YYYY-MM-DD"
    val estado: EstadoTransaccion,
)

data class ConfiguracionProcesamiento(
    val filtro: (Transaccion) -> Boolean,
    val transformacion: (Transaccion) -> Double,
    val formateo: (Double) -> String,
)

class ProcesadorTransacciones {
    // Parte A: Funciones de Transformación como Parámetros

    fun transformarMontos(
        transacciones: List<Transaccion>,
        transformacion: (Double) -> Double,
    ): List<Double> {
        return transacciones.map { transformacion(it.monto) }
    }

    fun <T> procesarCon(
        transacciones: List<Transaccion>,
        procesador: (Transaccion) -> T,
    ): List<T> {
        return transacciones.map(procesador)
    }

    // Parte B: Funciones de Filtrado como Parámetros

    fun filtrarTransacciones(
        transacciones: List<Transaccion>,
        predicado: (Transaccion) -> Boolean,
    ): List<Transaccion> {
        return transacciones.filter(predicado)
    }

    fun filtrarConMultiplesCriterios(
        transacciones: List<Transaccion>,
        criterios: List<(Transaccion) -> Boolean>,
    ): List<Transaccion> {
        return transacciones.filter { transaccion ->
            criterios.all { criterio -> criterio(transaccion) }
        }
    }

    // Parte C: Funciones de Agregación como Parámetros

    fun <T> agregar(
        transacciones: List<Transaccion>,
        valorInicial: T,
        agregador: (T, Transaccion) -> T,
    ): T {
        return transacciones.fold(valorInicial, agregador)
    }

    // Parte D: Composición de Funciones

    fun ejecutarPipeline(
        transacciones: List<Transaccion>,
        filtro1: (Transaccion) -> Boolean,
        filtro2: (Transaccion) -> Boolean,
        transformacion: (Transaccion) -> Double,
        agregacion: (Double, Double) -> Double,
    ): Double {
        return transacciones
            .filter(filtro1)
            .filter(filtro2)
            .map(transformacion)
            .fold(0.0, agregacion)

    }

    fun procesarConConfiguracion(
        transacciones: List<Transaccion>,
        config: ConfiguracionProcesamiento,
    ): List<String> {
        return transacciones
            .filter(config.filtro)
            .map(config.transformacion)
            .map(config.formateo)
    }

    fun procesarConEventos(
        transacciones: List<Transaccion>,
        onTransaccionProcesada: (Transaccion) -> Unit,
        onTransaccionRechazada: (Transaccion) -> Unit,
    ) {
        transacciones.forEach { transaccion ->
            when (transaccion.estado) {
                EstadoTransaccion.PROCESADA -> onTransaccionProcesada(transaccion)
                EstadoTransaccion.RECHAZADA -> onTransaccionRechazada(transaccion)
                else -> { }
            }
        }
    }
}



