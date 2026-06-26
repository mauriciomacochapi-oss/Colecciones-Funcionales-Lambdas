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
        TODO("Implementar: Debe aplicar la función de transformación a cada monto")
    }

    fun <T> procesarCon(
        transacciones: List<Transaccion>,
        procesador: (Transaccion) -> T,
    ): List<T> {
        TODO("Implementar: Debe procesar cada transacción con la función dada")
    }

    // Parte B: Funciones de Filtrado como Parámetros

    fun filtrarTransacciones(
        transacciones: List<Transaccion>,
        predicado: (Transaccion) -> Boolean,
    ): List<Transaccion> {
        TODO("Implementar: Debe filtrar transacciones usando el predicado")
    }

    fun filtrarConMultiplesCriterios(
        transacciones: List<Transaccion>,
        criterios: List<(Transaccion) -> Boolean>,
    ): List<Transaccion> {
        TODO("Implementar: Debe filtrar transacciones que cumplan TODOS los criterios")
    }

    // Parte C: Funciones de Agregación como Parámetros

    fun <T> agregar(
        transacciones: List<Transaccion>,
        valorInicial: T,
        agregador: (T, Transaccion) -> T,
    ): T {
        TODO("Implementar: Debe agregar valores usando la función agregadora")
    }

    // Parte D: Composición de Funciones

    fun ejecutarPipeline(
        transacciones: List<Transaccion>,
        filtro1: (Transaccion) -> Boolean,
        filtro2: (Transaccion) -> Boolean,
        transformacion: (Transaccion) -> Double,
        agregacion: (Double, Double) -> Double,
    ): Double {
        TODO(
            """
            Implementar pipeline:
            1) Aplicar filtro1
            2) Aplicar filtro2
            3) Transformar cada transacción a Double
            4) Agregar todos los valores con la función de agregación (inicial: 0.0)
        """,
        )
    }

    fun procesarConConfiguracion(
        transacciones: List<Transaccion>,
        config: ConfiguracionProcesamiento,
    ): List<String> {
        TODO(
            """
            Implementar:
            1) Filtrar usando config.filtro
            2) Transformar usando config.transformacion
            3) Formatear usando config.formateo
        """,
        )
    }

    fun procesarConEventos(
        transacciones: List<Transaccion>,
        onTransaccionProcesada: (Transaccion) -> Unit,
        onTransaccionRechazada: (Transaccion) -> Unit,
    ) {
        TODO(
            """
            Implementar:
            - Para transacciones PROCESADAS: ejecutar onTransaccionProcesada
            - Para transacciones RECHAZADAS: ejecutar onTransaccionRechazada
        """,
        )
    }
}

