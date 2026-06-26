/**
 * Ejercicio 5: It y Scope Functions (run, apply, also, let)
 *
 * Implementa los métodos de esta clase para que pasen todos los tests
 * del archivo Ejercicio5ItScopeFunctionsTest.kt
 *
 * IMPORTANTE: No modifiques la firma de los métodos, solo implementa su lógica.
 * IMPORTANTE: Debes usar las scope functions indicadas en cada sección.
 */

data class Usuario(
    var id: Int = 0,
    var nombre: String = "",
    var email: String = "",
    var activo: Boolean = false,
    var roles: MutableList<String> = mutableListOf(),
    var configuracion: ConfiguracionUsuario = ConfiguracionUsuario(),
)

data class ConfiguracionUsuario(
    var tema: String = "claro",
    var idioma: String = "es",
    var notificaciones: Boolean = true,
    var nivelPrivacidad: Int = 1,
)

data class Validacion(
    val campo: String,
    val valido: Boolean,
    val mensaje: String,
)

class UsuarioBuilder {
    // Parte A: Uso del parámetro implícito 'it'

    fun procesarNumeros(numeros: List<Int>): List<Int> {
        TODO("Implementar: Filtrar números pares y multiplicarlos por 10, usando 'it'")
    }

    fun validarUsuarios(usuarios: List<Usuario>): List<List<Validacion>> {
        TODO(
            """
            Implementar validación de usuarios usando 'it':
            - Validar que el nombre no esté vacío
            - Validar que el email contenga '@'
            - Validar que tenga al menos un rol
            Retornar lista de validaciones por cada usuario
        """,
        )
    }

    fun procesarTextos(textos: List<String>): List<String> {
        TODO("Implementar: Limpiar espacios, convertir a minúsculas y filtrar vacíos usando 'it'")
    }

    // Parte B: Función run

    fun calcularNivelAcceso(usuario: Usuario): Int {
        TODO(
            """
            Implementar usando 'run':
            - Si activo: +10 puntos
            - Por cada rol: +5 puntos
            - Si email contiene '@empresa.com': +5 puntos
        """,
        )
    }

    fun crearUsuarioConTipo(tipo: String): Usuario {
        TODO(
            """
            Implementar usando 'run' para decidir configuración:
            - Si tipo es "ADMIN": roles=[ADMIN], nivelPrivacidad=3, notificaciones=true
            - Si tipo es "USER": roles=[USER], nivelPrivacidad=1, notificaciones=false
            - Otros casos: configuración por defecto
        """,
        )
    }

    // Parte C: Función apply

    fun crearUsuarioCompleto(
        nombre: String,
        email: String,
        roles: List<String>,
    ): Usuario {
        TODO(
            """
            Implementar usando 'apply':
            - Crear usuario y configurar todas sus propiedades
            - Establecer activo = true
            - Asignar roles
            - Crear configuración por defecto
        """,
        )
    }

    fun actualizarUsuario(
        usuario: Usuario,
        actualizacion: Usuario.() -> Unit,
    ): Usuario {
        TODO("Implementar: Usar 'apply' para aplicar la función de actualización al usuario")
    }

    // Parte D: Función also

    fun crearUsuarioConLog(
        nombre: String,
        email: String,
        onLog: (String) -> Unit,
    ): Usuario {
        TODO(
            """
            Implementar usando 'also' para logging:
            - Crear usuario
            - Loggear "Usuario creado: [nombre]"
            - Asignar email y loggear "Email asignado: [email]"
            - Activar usuario y loggear "Usuario activado"
        """,
        )
    }

    fun crearYValidar(
        nombre: String,
        email: String,
    ): Pair<Usuario, Boolean> {
        TODO(
            """
            Implementar usando 'also' para validación:
            - Crear usuario
            - Validar que nombre no esté vacío y email contenga '@'
            - Retornar par (usuario, esValido)
        """,
        )
    }

    // Parte E: Función let

    fun procesarEmailOpcional(email: String?): String {
        TODO(
            """
            Implementar usando 'let':
            - Si email no es null: "Usuario con email: [email]"
            - Si email es null: "Usuario sin email"
        """,
        )
    }

    fun generarMensajesBienvenida(usuarios: List<Usuario>): List<String> {
        TODO(
            """
            Implementar usando 'let':
            - Solo procesar usuarios activos con email no vacío
            - Generar mensaje "Bienvenido/a [nombre] ([email])"
        """,
        )
    }

    // Parte F: Combinación de Scope Functions

    fun procesarUsuarioComplejo(datosBase: Map<String, String>): Usuario? {
        TODO(
            """
            Implementar combinando scope functions:
            1. Verificar que existan 'nombre' y 'email' (si no, retornar null)
            2. Crear usuario con 'run'
            3. Configurar propiedades con 'apply'
            4. Si departamento es "IT", usar 'also' para configuración especial (tema oscuro, rol IT_USER)
            5. Retornar usuario configurado
        """,
        )
    }

    fun procesarLoteUsuarios(usuarios: List<Usuario>): List<Usuario> {
        TODO(
            """
            Implementar pipeline con scope functions:
            1. Activar todos los usuarios (apply)
            2. Asignar rol USER si no tienen roles (also)
            3. Configurar notificaciones = true (apply)
            4. Si nombre es "Admin", agregar rol ADMIN y nivelPrivacidad = 3 (run)
        """,
        )
    }

    fun parsearYCrearUsuario(datosRaw: String): Usuario? {
        TODO(
            """
            Implementar parsing completo:
            1. Parsear formato "clave:valor|clave:valor|..."
            2. Crear usuario con los datos parseados
            3. Usar scope functions apropiadas para cada transformación
            4. Retornar null si el formato es inválido
        """,
        )
    }
}

