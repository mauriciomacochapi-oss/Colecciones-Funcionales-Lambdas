import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

/**
 * Ejercicio 4: Funciones como Argumentos
 * 
 * Este ejercicio explora el concepto de funciones de orden superior,
 * donde las funciones pueden recibir otras funciones como parámetros.
 * 
 * Objetivo: Implementar un procesador de datos flexible que utilice
 * funciones como argumentos para permitir diferentes estrategias de procesamiento.
 */
class Ejercicio4FuncionesComoArgumentosTest {
    
    @Nested
    @DisplayName("Parte A: Funciones de Transformación como Parámetros")
    inner class TransformationFunctions {
        
        @Test
        @DisplayName("Debe aplicar función de transformación a montos")
        fun aplicarTransformacionMontos() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.PROCESADA)
            )
            
            // Función que aplica 10% de impuesto
            val aplicarImpuesto: (Double) -> Double = { monto -> monto * 0.9 }
            
            val montosConImpuesto = procesador.transformarMontos(transacciones, aplicarImpuesto)
            
            assertEquals(listOf(90.0, 45.0, 180.0), montosConImpuesto)
        }
        
        @Test
        @DisplayName("Debe procesar transacciones con función personalizada")
        fun procesarConFuncionPersonalizada() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PENDIENTE)
            )
            
            // Función que formatea transacciones
            val formatear: (Transaccion) -> String = { t ->
                "${t.id}: ${if (t.tipo == TipoTransaccion.INGRESO) "+" else "-"}$${t.monto}"
            }
            
            val transaccionesFormateadas = procesador.procesarCon(transacciones, formatear)
            
            assertEquals(listOf("T001: +$100.0", "T002: -$50.0"), transaccionesFormateadas)
        }
        
        @Test
        @DisplayName("Debe permitir diferentes estrategias de cálculo")
        fun diferentesEstrategiasCalculo() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 150.0, TipoTransaccion.INGRESO, "Servicios", "2024-01-03", EstadoTransaccion.PROCESADA)
            )
            
            val calcularComision: (Double) -> Double = { monto -> monto * 0.05 }
            val calcularBonificacion: (Double) -> Double = { monto -> if (monto > 100) 10.0 else 0.0 }
            
            val comisiones = procesador.transformarMontos(transacciones, calcularComision)
            val bonificaciones = procesador.transformarMontos(transacciones, calcularBonificacion)
            
            assertEquals(listOf(5.0, 10.0, 7.5), comisiones)
            assertEquals(listOf(0.0, 10.0, 10.0), bonificaciones)
        }
    }
    
    @Nested
    @DisplayName("Parte B: Funciones de Filtrado como Parámetros")
    inner class FilterFunctions {
        
        @Test
        @DisplayName("Debe filtrar usando predicado como parámetro")
        fun filtrarConPredicado() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PENDIENTE),
                Transaccion("T003", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.RECHAZADA)
            )
            
            val soloGrandesMontos: (Transaccion) -> Boolean = { it.monto > 75 }
            val soloProcesadas: (Transaccion) -> Boolean = { it.estado == EstadoTransaccion.PROCESADA }
            
            val transaccionesGrandes = procesador.filtrarTransacciones(transacciones, soloGrandesMontos)
            val transaccionesProcesadas = procesador.filtrarTransacciones(transacciones, soloProcesadas)
            
            assertEquals(2, transaccionesGrandes.size)
            assertEquals(1, transaccionesProcesadas.size)
        }
        
        @Test
        @DisplayName("Debe combinar múltiples filtros")
        fun combinarFiltros() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 200.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-03", EstadoTransaccion.PROCESADA),
                Transaccion("T004", 150.0, TipoTransaccion.INGRESO, "Servicios", "2024-01-04", EstadoTransaccion.PENDIENTE)
            )
            
            val esIngreso: (Transaccion) -> Boolean = { it.tipo == TipoTransaccion.INGRESO }
            val esProcesada: (Transaccion) -> Boolean = { it.estado == EstadoTransaccion.PROCESADA }
            
            // Combinar filtros: ingresos Y procesadas
            val ingresosProcesados = procesador.filtrarConMultiplesCriterios(
                transacciones,
                listOf(esIngreso, esProcesada)
            )
            
            assertEquals(2, ingresosProcesados.size)
            assertTrue(ingresosProcesados.all { it.tipo == TipoTransaccion.INGRESO && it.estado == EstadoTransaccion.PROCESADA })
        }
    }
    
    @Nested
    @DisplayName("Parte C: Funciones de Agregación como Parámetros")
    inner class AggregationFunctions {
        
        @Test
        @DisplayName("Debe agregar valores usando función personalizada")
        fun agregarConFuncionPersonalizada() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.PROCESADA)
            )
            
            val sumarMontos: (Double, Transaccion) -> Double = { acc, t -> acc + t.monto }
            val contarProcesadas: (Int, Transaccion) -> Int = { acc, t -> 
                if (t.estado == EstadoTransaccion.PROCESADA) acc + 1 else acc
            }
            
            val totalMontos = procesador.agregar(transacciones, 0.0, sumarMontos)
            val cantidadProcesadas = procesador.agregar(transacciones, 0, contarProcesadas)
            
            assertEquals(350.0, totalMontos)
            assertEquals(3, cantidadProcesadas)
        }
        
        @Test
        @DisplayName("Debe calcular balance con función de agregación")
        fun calcularBalance() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 1000.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 300.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 500.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.PROCESADA),
                Transaccion("T004", 200.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-04", EstadoTransaccion.PROCESADA)
            )
            
            val calcularBalance: (Double, Transaccion) -> Double = { acc, t ->
                when(t.tipo) {
                    TipoTransaccion.INGRESO -> acc + t.monto
                    TipoTransaccion.EGRESO -> acc - t.monto
                }
            }
            
            val balanceFinal = procesador.agregar(transacciones, 0.0, calcularBalance)
            
            assertEquals(1000.0, balanceFinal) // 1000 - 300 + 500 - 200
        }
    }
    
    @Nested
    @DisplayName("Parte D: Composición de Funciones")
    inner class FunctionComposition {
        
        @Test
        @DisplayName("Debe procesar pipeline de funciones")
        fun procesarPipeline() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PENDIENTE),
                Transaccion("T003", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.PROCESADA),
                Transaccion("T004", 75.0, TipoTransaccion.INGRESO, "Servicios", "2024-01-04", EstadoTransaccion.PROCESADA)
            )
            
            // Pipeline: filtrar procesadas -> solo ingresos -> aplicar comisión -> sumar total
            val resultado = procesador.ejecutarPipeline(
                transacciones,
                filtro1 = { it.estado == EstadoTransaccion.PROCESADA },
                filtro2 = { it.tipo == TipoTransaccion.INGRESO },
                transformacion = { it.monto * 0.1 }, // 10% comisión
                agregacion = { acc, valor -> acc + valor }
            )
            
            // Solo T001, T003 y T004 (procesadas e ingresos)
            // Comisiones: 10.0 + 20.0 + 7.5 = 37.5
            assertEquals(37.5, resultado)
        }
        
        @Test
        @DisplayName("Debe crear procesador configurable con funciones")
        fun procesadorConfigurable() {
            val procesador = ProcesadorTransacciones()
            
            // Configuración para análisis de ventas
            val configuracionVentas = ConfiguracionProcesamiento(
                filtro = { it.categoria == "Ventas" && it.estado == EstadoTransaccion.PROCESADA },
                transformacion = { it.monto * 1.15 }, // Agregar 15% de ganancia
                formateo = { "Venta procesada: $%.1f".format(Locale.ENGLISH, it) }
            )
            
            // Configuración para análisis de gastos
            val configuracionGastos = ConfiguracionProcesamiento(
                filtro = { it.tipo == TipoTransaccion.EGRESO },
                transformacion = { it.monto * -1 }, // Convertir a negativo
                formateo = { "Gasto registrado: $%.1f".format(Locale.ENGLISH, it)}
            )
            
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 50.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 200.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-03", EstadoTransaccion.PROCESADA)
            )
            
            val resultadoVentas = procesador.procesarConConfiguracion(transacciones, configuracionVentas)
            val resultadoGastos = procesador.procesarConConfiguracion(transacciones, configuracionGastos)
            
            assertEquals(listOf("Venta procesada: $115.0", "Venta procesada: $230.0"), resultadoVentas)
            assertEquals(listOf("Gasto registrado: $-50.0"), resultadoGastos)
        }
        
        @Test
        @DisplayName("Debe manejar callbacks de eventos")
        fun manejarCallbacks() {
            val procesador = ProcesadorTransacciones()
            val transacciones = listOf(
                Transaccion("T001", 100.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-01", EstadoTransaccion.PROCESADA),
                Transaccion("T002", 5000.0, TipoTransaccion.INGRESO, "Ventas", "2024-01-02", EstadoTransaccion.PROCESADA),
                Transaccion("T003", 200.0, TipoTransaccion.EGRESO, "Gastos", "2024-01-03", EstadoTransaccion.RECHAZADA)
            )
            
            var alertasGeneradas = mutableListOf<String>()
            var rechazadasDetectadas = 0
            
            // Callbacks para diferentes eventos
            val onMontoAlto: (Transaccion) -> Unit = { t ->
                if (t.monto > 1000) {
                    alertasGeneradas.add("Monto alto detectado: ${t.id}")
                }
            }
            
            val onRechazada: (Transaccion) -> Unit = { 
                rechazadasDetectadas++
            }
            
            procesador.procesarConEventos(
                transacciones,
                onTransaccionProcesada = onMontoAlto,
                onTransaccionRechazada = onRechazada
            )
            
            assertEquals(1, alertasGeneradas.size)
            assertEquals("Monto alto detectado: T002", alertasGeneradas[0])
            assertEquals(1, rechazadasDetectadas)
        }
    }
    
}
