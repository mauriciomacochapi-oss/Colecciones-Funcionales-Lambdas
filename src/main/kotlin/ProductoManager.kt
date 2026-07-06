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
        return productos.map { it.nombre }

    }

    fun aplicarDescuento(
        productos: List<Producto>,
        descuentoPorcentaje: Double,
    ): List<Double> {
        return productos.map { it.precio * (1 - descuentoPorcentaje / 100.0) }

    }

    fun generarEtiquetas(productos: List<Producto>): List<String> {
        return productos.map { producto ->
            val estado = if (producto.enStock) "Disponible" else "Agotado"
            "${producto.nombre} - \$${producto.precio} ($estado)"
        }
    }

    // Parte B: Operaciones con Filter

    fun obtenerProductosEnStock(productos: List<Producto>): List<Producto> {
        return productos.filter { it.enStock }
    }

    fun filtrarPorPrecio(
        productos: List<Producto>,
        precioMin: Double,
        precioMax: Double,
    ): List<Producto> {
        return productos.filter { it.precio in precioMin..precioMax }
    }

    fun filtrarPorCategoria(
        productos: List<Producto>,
        categoria: String,
    ): List<Producto> {
        return productos.filter { it.categoria == categoria }
    }

    // Parte C: Combinación de Map y Filter

    fun obtenerNombresProductosDisponibles(productos: List<Producto>): List<String> {
        return productos
            .filter { it.enStock }
            .map { it.nombre }
    }

    fun aplicarDescuentoCategoria(
        productos: List<Producto>,
        categoria: String,
        descuentoPorcentaje: Double,
    ): List<Double> {
        return productos
            .filter { it.categoria == categoria }
            .map { it.precio * (1 - descuentoPorcentaje / 100.0) }
    }

    fun generarReporteProductosCaros(
        productos: List<Producto>,
        precioMinimo: Double,
    ): List<String> {
        return productos
            .filter { it.enStock && it.precio > precioMinimo }
            .map { "PRODUCTO PREMIUM: ${it.nombre} (\$${it.precio})" }
    }
}
