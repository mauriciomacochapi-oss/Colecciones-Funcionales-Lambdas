import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

/**
 * Ejercicio 1: Map y Filter
 * 
 * En este ejercicio trabajarás con las operaciones map y filter,
 * dos de las funciones más fundamentales para transformar y filtrar colecciones.
 * 
 * Objetivo: Implementar una clase ProductoManager que gestione una lista de productos
 * usando map para transformaciones y filter para búsquedas.
 */
class Ejercicio1MapFilterTest {
    @Nested
    @DisplayName("Parte A: Operaciones con Map")
    inner class MapOperations {
        
        @Test
        @DisplayName("Debe obtener una lista con solo los nombres de los productos")
        fun obtenerNombresDeProductos() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", true),
                Producto(3, "Cuaderno", 5.0, "Papelería", false)
            )
            
            val nombres = manager.obtenerNombres(productos)
            
            assertEquals(listOf("Laptop", "Mouse", "Cuaderno"), nombres)
        }
        
        @Test
        @DisplayName("Debe calcular precios con descuento aplicado")
        fun calcularPreciosConDescuento() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1000.0, "Electrónica", true),
                Producto(2, "Mouse", 50.0, "Electrónica", true)
            )
            val descuentoPorcentaje = 10.0
            
            val preciosConDescuento = manager.aplicarDescuento(productos, descuentoPorcentaje)
            
            assertEquals(listOf(900.0, 45.0), preciosConDescuento)
        }
        
        @Test
        @DisplayName("Debe crear etiquetas descriptivas para cada producto")
        fun crearEtiquetasDeProductos() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Cuaderno", 5.0, "Papelería", false)
            )
            
            val etiquetas = manager.generarEtiquetas(productos)
            
            assertEquals(
                listOf(
                    "Laptop - $1500.0 (Disponible)",
                    "Cuaderno - $5.0 (Agotado)"
                ),
                etiquetas
            )
        }
    }
    
    @Nested
    @DisplayName("Parte B: Operaciones con Filter")
    inner class FilterOperations {
        
        @Test
        @DisplayName("Debe filtrar productos en stock")
        fun filtrarProductosEnStock() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", false),
                Producto(3, "Cuaderno", 5.0, "Papelería", true)
            )
            
            val productosDisponibles = manager.obtenerProductosEnStock(productos)
            
            assertEquals(2, productosDisponibles.size)
            assertTrue(productosDisponibles.all { it.enStock })
        }
        
        @Test
        @DisplayName("Debe filtrar productos por rango de precio")
        fun filtrarPorRangoDePrecio() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", true),
                Producto(3, "Cuaderno", 5.0, "Papelería", true),
                Producto(4, "Monitor", 300.0, "Electrónica", true)
            )
            
            val productosEnRango = manager.filtrarPorPrecio(productos, 20.0, 400.0)
            
            assertEquals(2, productosEnRango.size)
            assertTrue(productosEnRango.all { it.precio >= 20.0 && it.precio <= 400.0 })
        }
        
        @Test
        @DisplayName("Debe filtrar productos por categoría")
        fun filtrarPorCategoria() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", true),
                Producto(3, "Cuaderno", 5.0, "Papelería", true),
                Producto(4, "Lápiz", 1.0, "Papelería", true)
            )
            
            val productosElectronicos = manager.filtrarPorCategoria(productos, "Electrónica")
            
            assertEquals(2, productosElectronicos.size)
            assertTrue(productosElectronicos.all { it.categoria == "Electrónica" })
        }
    }
    
    @Nested
    @DisplayName("Parte C: Combinación de Map y Filter")
    inner class MapFilterCombined {
        
        @Test
        @DisplayName("Debe obtener nombres de productos en stock")
        fun nombresDeProductosEnStock() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1500.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", false),
                Producto(3, "Cuaderno", 5.0, "Papelería", true),
                Producto(4, "Monitor", 300.0, "Electrónica", false)
            )
            
            val nombresDisponibles = manager.obtenerNombresProductosDisponibles(productos)
            
            assertEquals(listOf("Laptop", "Cuaderno"), nombresDisponibles)
        }
        
        @Test
        @DisplayName("Debe aplicar descuento solo a productos de cierta categoría")
        fun descuentoPorCategoria() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop", 1000.0, "Electrónica", true),
                Producto(2, "Mouse", 50.0, "Electrónica", true),
                Producto(3, "Cuaderno", 10.0, "Papelería", true)
            )
            
            val preciosConDescuento = manager.aplicarDescuentoCategoria(
                productos, 
                "Electrónica", 
                20.0
            )
            
            // Solo productos de Electrónica con descuento aplicado
            assertEquals(listOf(800.0, 40.0), preciosConDescuento)
        }
        
        @Test
        @DisplayName("Debe generar reporte de productos caros disponibles")
        fun reporteProductosCaros() {
            val manager = ProductoManager()
            val productos = listOf(
                Producto(1, "Laptop Premium", 2000.0, "Electrónica", true),
                Producto(2, "Mouse", 25.0, "Electrónica", true),
                Producto(3, "Monitor 4K", 800.0, "Electrónica", false),
                Producto(4, "Tablet", 600.0, "Electrónica", true)
            )
            
            val reporte = manager.generarReporteProductosCaros(productos, 500.0)
            
            assertEquals(
                listOf(
                    "PRODUCTO PREMIUM: Laptop Premium ($2000.0)",
                    "PRODUCTO PREMIUM: Tablet ($600.0)"
                ),
                reporte
            )
        }
    }
}
