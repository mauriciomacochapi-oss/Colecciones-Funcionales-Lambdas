import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

/**
 * Ejercicio 3: SortBy y Lambdas
 * 
 * En este ejercicio profundizarás en el uso de lambdas para ordenamiento
 * y aprenderás a escribir expresiones lambda más complejas.
 * 
 * Objetivo: Implementar un sistema de ranking que ordene elementos
 * según diferentes criterios usando sortBy y lambdas personalizadas.
 */
class Ejercicio3SortByLambdaTest {
    

    @Nested
    @DisplayName("Parte A: Ordenamiento Simple con sortBy")
    inner class SimpleSorting {
        
        @Test
        @DisplayName("Debe ordenar empleados por salario ascendente")
        fun ordenarPorSalario() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.5, 20),
                Empleado(3, "Beatriz", "RRHH", 40000.0, 2, 3.8, 10)
            )
            
            val ordenados = sistema.ordenarPorSalario(empleados)
            
            assertEquals("Beatriz", ordenados[0].nombre)
            assertEquals("Ana", ordenados[1].nombre)
            assertEquals("Carlos", ordenados[2].nombre)
        }
        
        @Test
        @DisplayName("Debe ordenar por años de experiencia descendente")
        fun ordenarPorExperienciaDescendente() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 7, 4.5, 25),
                Empleado(3, "David", "Marketing", 50000.0, 5, 4.0, 18),
                Empleado(4, "Elena", "IT", 70000.0, 10, 4.8, 30)
            )
            
            val veteranos = sistema.ordenarPorExperienciaDesc(empleados)
            
            assertEquals(10, veteranos[0].anosExperiencia)
            assertEquals(7, veteranos[1].anosExperiencia)
            assertEquals(5, veteranos[2].anosExperiencia)
            assertEquals(3, veteranos[3].anosExperiencia)
        }
        
        @Test
        @DisplayName("Debe ordenar alfabéticamente por nombre")
        fun ordenarAlfabeticamente() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Zoe", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Alberto", "IT", 65000.0, 5, 4.5, 20),
                Empleado(3, "Maria", "RRHH", 40000.0, 2, 3.8, 10)
            )
            
            val ordenadosAlfabeticamente = sistema.ordenarPorNombre(empleados)
            
            assertEquals("Alberto", ordenadosAlfabeticamente[0].nombre)
            assertEquals("Maria", ordenadosAlfabeticamente[1].nombre)
            assertEquals("Zoe", ordenadosAlfabeticamente[2].nombre)
        }
    }
    
    @Nested
    @DisplayName("Parte B: Lambdas Complejas")
    inner class ComplexLambdas {
        
        @Test
        @DisplayName("Debe ordenar por criterio calculado (eficiencia)")
        fun ordenarPorEficiencia() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 2, 4.2, 10),  // Eficiencia: 5.0
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.5, 15),   // Eficiencia: 3.0
                Empleado(3, "Beatriz", "RRHH", 40000.0, 1, 3.8, 8)  // Eficiencia: 8.0
            )
            
            // Eficiencia = proyectosCompletados / añosExperiencia
            val porEficiencia = sistema.ordenarPorEficiencia(empleados)
            
            assertEquals("Beatriz", porEficiencia[0].nombre) // 8.0
            assertEquals("Ana", porEficiencia[1].nombre)     // 5.0
            assertEquals("Carlos", porEficiencia[2].nombre)  // 3.0
        }
        
        @Test
        @DisplayName("Debe ordenar por puntuación compuesta")
        fun ordenarPorPuntuacionCompuesta() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.0, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.5, 20),
                Empleado(3, "Beatriz", "RRHH", 40000.0, 2, 5.0, 10)
            )
            
            // Puntuación = (evaluacionDesempeño * 2) + (proyectosCompletados * 0.1)
            val porPuntuacion = sistema.ordenarPorPuntuacionCompuesta(empleados)
            
            assertEquals("Beatriz", porPuntuacion[0].nombre) // 5.0*2 + 10*0.1 = 11.0
            assertEquals("Carlos", porPuntuacion[1].nombre)  // 4.5*2 + 20*0.1 = 11.0 (pero segundo por orden)
            assertEquals("Ana", porPuntuacion[2].nombre)     // 4.0*2 + 15*0.1 = 9.5
        }
        
        @Test
        @DisplayName("Debe usar lambda con condiciones")
        fun ordenarConCondiciones() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 75000.0, 8, 4.5, 25),
                Empleado(3, "Beatriz", "RRHH", 40000.0, 2, 3.8, 10),
                Empleado(4, "David", "IT", 55000.0, 4, 4.0, 12)
            )
            
            // Ordenar por: primero departamento IT, luego por salario
            val ordenadosPorPrioridad = sistema.ordenarITPrimero(empleados)
            
            // Primero deben aparecer los de IT ordenados por salario
            assertEquals("IT", ordenadosPorPrioridad[0].departamento)
            assertEquals("IT", ordenadosPorPrioridad[1].departamento)
            assertEquals("David", ordenadosPorPrioridad[0].nombre) // IT con menor salario
            assertEquals("Carlos", ordenadosPorPrioridad[1].nombre) // IT con mayor salario
        }
    }
    
    @Nested
    @DisplayName("Parte C: Ordenamiento Múltiple")
    inner class MultipleOrdering {
        
        @Test
        @DisplayName("Debe ordenar por múltiples criterios en secuencia")
        fun ordenarMultiplesCriterios() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.2, 20),
                Empleado(3, "Beatriz", "Ventas", 45000.0, 2, 4.5, 10),
                Empleado(4, "David", "IT", 65000.0, 4, 4.2, 18)
            )
            
            // Ordenar por: 1) Departamento, 2) Salario descendente, 3) Experiencia
            val ordenados = sistema.ordenarPorDepartamentoYSalario(empleados)
            
            // IT debe venir primero (alfabéticamente)
            assertEquals("IT", ordenados[0].departamento)
            assertEquals("IT", ordenados[1].departamento)
            // Dentro de IT, Carlos y David tienen mismo salario, ordena por experiencia
            assertEquals("David", ordenados[0].nombre) // Menos experiencia primero
            assertEquals("Carlos", ordenados[1].nombre)
            // Luego Ventas
            assertEquals("Ventas", ordenados[2].departamento)
            assertEquals("Ventas", ordenados[3].departamento)
        }
        
        @Test
        @DisplayName("Debe aplicar diferentes ordenamientos según condición")
        fun ordenamientoCondicional() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Junior1", "Ventas", 30000.0, 1, 3.5, 5),
                Empleado(2, "Senior1", "IT", 80000.0, 10, 4.5, 50),
                Empleado(3, "Junior2", "IT", 35000.0, 2, 4.0, 8),
                Empleado(4, "Senior2", "RRHH", 70000.0, 8, 4.2, 35)
            )
            
            // Si experiencia < 5 años: ordenar por evaluación
            // Si experiencia >= 5 años: ordenar por proyectos completados
            val ordenadosCondicionalmente = sistema.ordenarSegunSeniority(empleados)
            
            // Los juniors (< 5 años) deben estar ordenados por evaluación
            // Los seniors (>= 5 años) deben estar ordenados por proyectos
            val juniors = ordenadosCondicionalmente.filter { it.anosExperiencia < 5 }
            val seniors = ordenadosCondicionalmente.filter { it.anosExperiencia >= 5 }
            
            assertTrue(juniors.zipWithNext().all { (a, b) -> 
                a.evaluacionDesempeno >= b.evaluacionDesempeno
            })
            assertTrue(seniors.zipWithNext().all { (a, b) -> 
                a.proyectosCompletados >= b.proyectosCompletados 
            })
        }
    }
    
    @Nested
    @DisplayName("Parte D: Lambdas como Parámetros de Configuración")
    inner class LambdaConfiguration {
        
        @Test
        @DisplayName("Debe permitir ordenamiento personalizable")
        fun ordenamientoPersonalizable() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.5, 20),
                Empleado(3, "Beatriz", "RRHH", 40000.0, 2, 3.8, 10)
            )
            
            // Crear diferentes estrategias de ordenamiento con lambdas
            val porValorEconomico = { e: Empleado -> 
                e.salario + (e.proyectosCompletados * 1000)
            }
            
            val porRendimiento = { e: Empleado ->
                e.evaluacionDesempeno * e.proyectosCompletados
            }
            
            val ordenadosPorValor = sistema.ordenarConEstrategia(empleados, porValorEconomico)
            val ordenadosPorRendimiento = sistema.ordenarConEstrategia(empleados, porRendimiento)
            
            // Por valor económico: Carlos (65000 + 20000 = 85000) debe ser primero
            assertEquals("Carlos", ordenadosPorValor[0].nombre)
            
            // Por rendimiento: Carlos (4.5 * 20 = 90) debe ser primero
            assertEquals("Carlos", ordenadosPorRendimiento[0].nombre)
        }
        
        @Test
        @DisplayName("Debe componer múltiples lambdas")
        fun composicionDeLambdas() {
            val sistema = SistemaRanking()
            val empleados = listOf(
                Empleado(1, "Ana", "Ventas", 45000.0, 3, 4.2, 15),
                Empleado(2, "Carlos", "IT", 65000.0, 5, 4.5, 20),
                Empleado(3, "Beatriz", "RRHH", 40000.0, 6, 4.8, 25),
                Empleado(4, "David", "IT", 55000.0, 4, 3.9, 12)
            )
            
            // Aplicar filtro y luego ordenamiento, todo con lambdas
            val empleadosTop = sistema.obtenerTopEmpleados(
                empleados,
                filtro = { it.evaluacionDesempeno >= 4.0 },
                ordenamiento = { it.salario * it.evaluacionDesempeno },
                limite = 2
            )
            
            assertEquals(2, empleadosTop.size)
            assertEquals("Carlos", empleadosTop[0].nombre)
            assertTrue(empleadosTop.all { it.evaluacionDesempeno >= 4.0 })
        }
    }
}