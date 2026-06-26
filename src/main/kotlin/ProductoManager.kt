/**
 * Ejercicio 1: Map y Filter
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio1MapFilterTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 */

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val enStock: Boolean,
)

class ProductoManager {
    // Parte A: Operaciones con Map

    fun obtenerNombres(productos: List<Producto>): List<String> {
        TODO("Implementar: Debe retornar una lista con solo los nombres de los productos")
    }

    fun aplicarDescuento(
        productos: List<Producto>,
        descuentoPorcentaje: Double,
    ): List<Double> {
        TODO("Implementar: Debe retornar una lista con los precios después de aplicar el descuento")
    }

    fun generarEtiquetas(productos: List<Producto>): List<String> {
        TODO("Implementar: Debe generar etiquetas en formato 'nombre - \$precio (estado)'")
    }

    // Parte B: Operaciones con Filter

    fun obtenerProductosEnStock(productos: List<Producto>): List<Producto> {
        TODO("Implementar: Debe retornar solo los productos que están en stock")
    }

    fun filtrarPorPrecio(
        productos: List<Producto>,
        precioMin: Double,
        precioMax: Double,
    ): List<Producto> {
        TODO("Implementar: Debe retornar productos cuyo precio esté entre precioMin y precioMax")
    }

    fun filtrarPorCategoria(
        productos: List<Producto>,
        categoria: String,
    ): List<Producto> {
        TODO("Implementar: Debe retornar solo los productos de la categoría especificada")
    }

    // Parte C: Combinación de Map y Filter

    fun obtenerNombresProductosDisponibles(productos: List<Producto>): List<String> {
        TODO("Implementar: Debe filtrar productos en stock y retornar sus nombres")
    }

    fun aplicarDescuentoCategoria(
        productos: List<Producto>,
        categoria: String,
        descuentoPorcentaje: Double,
    ): List<Double> {
        TODO("Implementar: Debe aplicar descuento solo a productos de la categoría especificada")
    }

    fun generarReporteProductosCaros(
        productos: List<Producto>,
        precioMinimo: Double,
    ): List<String> {
        TODO("Implementar: Debe generar reporte de productos caros disponibles en formato especial")
    }
}
