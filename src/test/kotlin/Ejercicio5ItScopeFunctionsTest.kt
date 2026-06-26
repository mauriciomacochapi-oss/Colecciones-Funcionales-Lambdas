import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

/**
 * Ejercicio 5: It y Scope Functions (run, apply, also, let)
 * 
 * Este ejercicio explora el uso del parámetro implícito 'it' en lambdas
 * y las scope functions de Kotlin que facilitan la manipulación de objetos.
 * 
 * Objetivo: Implementar un constructor de configuraciones que utilice
 * 'it' eficientemente y aplique scope functions para crear código más limpio.
 */
class Ejercicio5ItScopeFunctionsTest {
    @Nested
    @DisplayName("Parte A: Uso del parámetro implícito 'it'")
    inner class ItParameter {
        
        @Test
        @DisplayName("Debe usar 'it' en operaciones con colecciones")
        fun usarItEnColecciones() {
            val builder = UsuarioBuilder()
            val numeros = listOf(1, 2, 3, 4, 5)
            
            // Usar 'it' para transformar y filtrar
            val procesados = builder.procesarNumeros(numeros)
            
            // Debe filtrar pares (2, 4) y multiplicar por 10 (20, 40)
            assertEquals(listOf(20, 40), procesados)
        }
        
        @Test
        @DisplayName("Debe validar usando 'it' en predicados")
        fun validarUsandoIt() {
            val builder = UsuarioBuilder()
            val usuarios = listOf(
                Usuario(1, "Ana", "ana@email.com", true, mutableListOf("USER")),
                Usuario(2, "", "bob@email.com", true, mutableListOf()),
                Usuario(3, "Carlos", "invalid-email", false, mutableListOf("ADMIN"))
            )
            
            // Validar usuarios usando 'it'
            val validaciones = builder.validarUsuarios(usuarios)
            
            assertEquals(3, validaciones.size)
            assertTrue(validaciones[0].all { it.valido }) // Ana es válida
            assertFalse(validaciones[1].all { it.valido }) // Bob no tiene nombre
            assertFalse(validaciones[2].find { it.campo == "email" }?.valido ?: true) // Carlos tiene email inválido
        }
        
        @Test
        @DisplayName("Debe encadenar operaciones usando 'it'")
        fun encadenarOperacionesConIt() {
            val builder = UsuarioBuilder()
            val textos = listOf("  kotlin  ", "JAVA", "  Scala  ", "")
            
            // Limpiar, convertir a minúsculas y filtrar vacíos usando 'it'
            val procesados = builder.procesarTextos(textos)
            
            assertEquals(listOf("kotlin", "java", "scala"), procesados)
        }
    }
    
    @Nested
    @DisplayName("Parte B: Función run")
    inner class RunFunction {
        
        @Test
        @DisplayName("Debe usar run para cálculos complejos")
        fun usarRunParaCalculos() {
            val builder = UsuarioBuilder()
            val usuario = Usuario(
                id = 1,
                nombre = "Ana García",
                email = "ana@empresa.com",
                activo = true,
                roles = mutableListOf("USER", "EDITOR", "REVIEWER")
            )
            
            // Usar run para calcular nivel de acceso basado en múltiples factores
            val nivelAcceso = builder.calcularNivelAcceso(usuario)
            
            // activo (10) + roles.size * 5 (15) + email corporativo (5) = 30
            assertEquals(30, nivelAcceso)
        }
        
        @Test
        @DisplayName("Debe usar run para inicialización condicional")
        fun usarRunParaInicializacion() {
            val builder = UsuarioBuilder()
            
            // Crear usuario con configuración basada en tipo
            val usuarioAdmin = builder.crearUsuarioConTipo("ADMIN")
            val usuarioBasico = builder.crearUsuarioConTipo("USER")
            
            assertTrue(usuarioAdmin.roles.contains("ADMIN"))
            assertTrue(usuarioAdmin.configuracion.notificaciones)
            assertEquals(3, usuarioAdmin.configuracion.nivelPrivacidad)
            
            assertTrue(usuarioBasico.roles.contains("USER"))
            assertEquals(1, usuarioBasico.configuracion.nivelPrivacidad)
        }
    }
    
    @Nested
    @DisplayName("Parte C: Función apply")
    inner class ApplyFunction {
        
        @Test
        @DisplayName("Debe usar apply para configuración de objetos")
        fun usarApplyParaConfiguracion() {
            val builder = UsuarioBuilder()
            
            val usuario = builder.crearUsuarioCompleto(
                nombre = "Carlos Ruiz",
                email = "carlos@test.com",
                roles = listOf("USER", "MODERATOR")
            )
            
            assertEquals("Carlos Ruiz", usuario.nombre)
            assertEquals("carlos@test.com", usuario.email)
            assertTrue(usuario.activo)
            assertEquals(2, usuario.roles.size)
            assertNotNull(usuario.configuracion)
        }
        
        @Test
        @DisplayName("Debe usar apply para modificación en cadena")
        fun usarApplyParaModificacion() {
            val builder = UsuarioBuilder()
            val usuario = Usuario(1, "Test", "test@email.com")
            
            val usuarioModificado = builder.actualizarUsuario(usuario) {
                // Esta lambda se ejecuta dentro de apply
                activo = true
                roles.add("PREMIUM")
                configuracion.tema = "oscuro"
                configuracion.idioma = "en"
            }
            
            assertTrue(usuarioModificado.activo)
            assertTrue(usuarioModificado.roles.contains("PREMIUM"))
            assertEquals("oscuro", usuarioModificado.configuracion.tema)
            assertEquals("en", usuarioModificado.configuracion.idioma)
        }
    }
    
    @Nested
    @DisplayName("Parte D: Función also")
    inner class AlsoFunction {
        
        @Test
        @DisplayName("Debe usar also para efectos secundarios")
        fun usarAlsoParaEfectosSecundarios() {
            val builder = UsuarioBuilder()
            val logEventos = mutableListOf<String>()
            
            val usuario = builder.crearUsuarioConLog(
                nombre = "Diana López",
                email = "diana@corp.com",
                onLog = { evento -> logEventos.add(evento) }
            )
            
            // also debe usarse para registrar eventos sin modificar el objeto
            assertTrue(logEventos.contains("Usuario creado: Diana López"))
            assertTrue(logEventos.contains("Email asignado: diana@corp.com"))
            assertTrue(logEventos.contains("Usuario activado"))
            
            assertEquals("Diana López", usuario.nombre)
            assertTrue(usuario.activo)
        }
        
        @Test
        @DisplayName("Debe usar also para validación post-creación")
        fun usarAlsoParaValidacion() {
            val builder = UsuarioBuilder()
            
            val resultadoExitoso = builder.crearYValidar(
                nombre = "Elena Martinez",
                email = "elena@valid.com"
            )
            
            val resultadoFallido = builder.crearYValidar(
                nombre = "",
                email = "invalid-email"
            )
            
            assertTrue(resultadoExitoso.second) // Validación exitosa
            assertFalse(resultadoFallido.second) // Validación fallida
        }
    }
    
    @Nested
    @DisplayName("Parte E: Función let")
    inner class LetFunction {
        
        @Test
        @DisplayName("Debe usar let para transformaciones null-safe")
        fun usarLetParaNullSafety() {
            val builder = UsuarioBuilder()
            
            val emailValido: String? = "user@domain.com"
            val emailNulo: String? = null
            
            val resultadoValido = builder.procesarEmailOpcional(emailValido)
            val resultadoNulo = builder.procesarEmailOpcional(emailNulo)
            
            assertEquals("Usuario con email: user@domain.com", resultadoValido)
            assertEquals("Usuario sin email", resultadoNulo)
        }
        
        @Test
        @DisplayName("Debe usar let para mapeo condicional")
        fun usarLetParaMapeo() {
            val builder = UsuarioBuilder()
            val usuarios = listOf(
                Usuario(1, "Ana", "ana@test.com", true),
                Usuario(2, "Bob", "", false),
                Usuario(3, "Carlos", "carlos@test.com", true)
            )
            
            // Usar let para procesar solo usuarios activos con email
            val mensajes = builder.generarMensajesBienvenida(usuarios)
            
            assertEquals(2, mensajes.size)
            assertTrue(mensajes.contains("Bienvenido/a Ana (ana@test.com)"))
            assertTrue(mensajes.contains("Bienvenido/a Carlos (carlos@test.com)"))
        }
    }
    
    @Nested
    @DisplayName("Parte F: Combinación de Scope Functions")
    inner class CombinedScopeFunctions {
        
        @Test
        @DisplayName("Debe combinar múltiples scope functions")
        fun combinarScopeFunctions() {
            val builder = UsuarioBuilder()
            
            val resultado = builder.procesarUsuarioComplejo(
                datosBase = mapOf(
                    "nombre" to "Fernando Silva",
                    "email" to "fernando@empresa.com",
                    "departamento" to "IT"
                )
            )
            
            assertNotNull(resultado)
            assertEquals("Fernando Silva", resultado?.nombre)
            assertEquals("fernando@empresa.com", resultado?.email)
            assertTrue(resultado?.roles?.contains("IT_USER") ?: false)
            assertEquals("oscuro", resultado?.configuracion?.tema) // IT tiene tema oscuro por defecto
        }
        
        @Test
        @DisplayName("Debe crear pipeline fluido con scope functions")
        fun crearPipelineFluido() {
            val builder = UsuarioBuilder()
            
            val usuarios = listOf(
                Usuario(1, "User1", "user1@test.com"),
                Usuario(2, "User2", "user2@test.com"),
                Usuario(3, "Admin", "admin@test.com")
            )
            
            // Pipeline: activar -> asignar roles -> configurar -> validar -> loggear
            val procesados = builder.procesarLoteUsuarios(usuarios)
            
            assertEquals(3, procesados.size)
            assertTrue(procesados.all { it.activo })
            assertTrue(procesados.all { it.roles.isNotEmpty() })
            assertTrue(procesados.all { it.configuracion.notificaciones })
            
            // Admin debe tener configuración especial
            val admin = procesados.find { it.nombre == "Admin" }
            assertTrue(admin?.roles?.contains("ADMIN") ?: false)
            assertEquals(3, admin?.configuracion?.nivelPrivacidad)
        }
        
        @Test
        @DisplayName("Debe manejar transformación compleja con todas las scope functions")
        fun transformacionCompletaConScopeFunctions() {
            val builder = UsuarioBuilder()
            
            // Datos crudos desde una fuente externa
            val datosRaw = """
                id:5|nombre:Gabriela Mendez|email:gabriela@corp.com|
                roles:USER,EDITOR|activo:true|tema:auto|idioma:es
            """.trimIndent()
            
            val usuario = builder.parsearYCrearUsuario(datosRaw)
            
            assertNotNull(usuario)
            assertEquals(5, usuario?.id)
            assertEquals("Gabriela Mendez", usuario?.nombre)
            assertEquals("gabriela@corp.com", usuario?.email)
            assertTrue(usuario?.activo ?: false)
            assertEquals(2, usuario?.roles?.size)
            assertTrue(usuario?.roles?.contains("USER") ?: false)
            assertTrue(usuario?.roles?.contains("EDITOR") ?: false)
            assertEquals("auto", usuario?.configuracion?.tema)
            assertEquals("es", usuario?.configuracion?.idioma)
        }
    }
}
