/**
 * Ejercicio 3: SortBy y Lambdas
 * 
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio3SortByLambdaTest.kt
 * 
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 */

data class Empleado(
    val id: Int,
    val nombre: String,
    val departamento: String,
    val salario: Double,
    val anosExperiencia: Int,
    val evaluacionDesempeno: Double, // 0.0 a 5.0
    val proyectosCompletados: Int
)
class SistemaRanking {
    
    // Parte A: Ordenamiento Simple con sortBy
    
    fun ordenarPorSalario(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedBy { it.salario }

    }
    
    fun ordenarPorExperienciaDesc(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedByDescending { it.anosExperiencia }
    }
    
    fun ordenarPorNombre(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedBy { it.nombre }
    }
    
    // Parte B: Lambdas Complejas
    
    fun ordenarPorEficiencia(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedByDescending { it.proyectosCompletados.toDouble() / it.anosExperiencia }
    }
    
    fun ordenarPorPuntuacionCompuesta(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedWith(
            compareByDescending<Empleado> { (it.evaluacionDesempeno * 2) + (it.proyectosCompletados * 0.1) }
                .thenByDescending { it.evaluacionDesempeno }
        )
    }
    
    fun ordenarITPrimero(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedWith(
            compareBy<Empleado> { it.departamento != "IT" }.thenBy { it.salario }
        )
    }
    
    // Parte C: Ordenamiento Múltiple
    
    fun ordenarPorDepartamentoYSalario(empleados: List<Empleado>): List<Empleado> {
        return empleados.sortedWith(
            compareBy<Empleado> { it.departamento }
                .thenByDescending { it.salario }
                .thenBy { it.anosExperiencia }
        )

    }
    
    fun ordenarSegunSeniority(empleados: List<Empleado>): List<Empleado> {
        val juniors = empleados.filter { it.anosExperiencia < 5 }.sortedByDescending { it.evaluacionDesempeno }
        val seniors = empleados.filter { it.anosExperiencia >= 5 }.sortedByDescending { it.proyectosCompletados }
        return juniors + seniors
    }
    
    // Parte D: Lambdas como Parámetros de Configuración
    
    fun <T : Comparable<T>> ordenarConEstrategia(
        empleados: List<Empleado>,
        estrategia: (Empleado) -> T
    ): List<Empleado> {
        return empleados.sortedByDescending(estrategia)
    }
    
    fun obtenerTopEmpleados(
        empleados: List<Empleado>,
        filtro: (Empleado) -> Boolean,
        ordenamiento: (Empleado) -> Double,
        limite: Int
    ): List<Empleado> {
        return empleados
            .filter(filtro)
            .sortedByDescending(ordenamiento)
            .take(limite)
    }
}