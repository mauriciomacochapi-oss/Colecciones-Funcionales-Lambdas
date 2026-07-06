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
        return numeros.filter { it % 2 == 0 }.map { it * 10 }
    }

    fun validarUsuarios(usuarios: List<Usuario>): List<List<Validacion>> {
        return usuarios.map {
            listOf(
                Validacion("nombre", it.nombre.isNotEmpty(), "El nombre no debe estar vacío"),
                Validacion("email", it.email.contains("@"), "El email debe contener @"),
                Validacion("roles", it.roles.isNotEmpty(), "Debe tener al menos un rol")
            )
        }
    }

    fun procesarTextos(textos: List<String>): List<String> {
        return textos.map { it.trim() }.map { it.lowercase() }.filter { it.isNotEmpty() }
    }

    // Parte B: Función run

    fun calcularNivelAcceso(usuario: Usuario): Int {
        return usuario.run {
            var puntos = 0
            if (activo) puntos += 10
            puntos += roles.size * 5
            if (email.contains("@empresa.com")) puntos += 5
            puntos
        }
    }

    fun crearUsuarioConTipo(tipo: String): Usuario {
        return Usuario().run {
            when (tipo) {
                "ADMIN" -> {
                    roles.add("ADMIN")
                    configuracion.nivelPrivacidad = 3
                    configuracion.notificaciones = true
                }
                "USER" -> {
                    roles.add("USER")
                    configuracion.nivelPrivacidad = 1
                    configuracion.notificaciones = false
                }
            }
            this
        }
    }

    // Parte C: Función apply

    fun crearUsuarioCompleto(
        nombre: String,
        email: String,
        roles: List<String>,
    ): Usuario {
        return Usuario().apply {
            this.nombre = nombre
            this.email = email
            this.activo = true
            this.roles = roles.toMutableList()
            this.configuracion = ConfiguracionUsuario()
        }
    }

    fun actualizarUsuario(
        usuario: Usuario,
        actualizacion: Usuario.() -> Unit,
    ): Usuario {
        return usuario.apply(actualizacion)
    }

    // Parte D: Función also

    fun crearUsuarioConLog(
        nombre: String,
        email: String,
        onLog: (String) -> Unit,
    ): Usuario {
        return Usuario().also { onLog("Usuario creado: $nombre") }
            .apply { this.nombre = nombre; this.email = email }
            .also { onLog("Email asignado: $email") }
            .apply { this.activo = true }
            .also { onLog("Usuario activado") }
    }

    fun crearYValidar(
        nombre: String,
        email: String,
    ): Pair<Usuario, Boolean> {
        var esValido = false
        val usuario = Usuario(nombre = nombre, email = email).also {
            esValido = it.nombre.isNotEmpty() && it.email.contains("@")
        }
        return Pair(usuario, esValido)
    }


    // Parte E: Función let

    fun procesarEmailOpcional(email: String?): String {
        return email?.let { "Usuario con email: $it" } ?: "Usuario sin email"
    }

    fun generarMensajesBienvenida(usuarios: List<Usuario>): List<String> {
        return usuarios
            .filter { it.activo && it.email.isNotEmpty() }
            .map { usuario ->
                usuario.let { "Bienvenido/a ${it.nombre} (${it.email})" }
            }
    }

    // Parte F: Combinación de Scope Functions

    fun procesarUsuarioComplejo(datosBase: Map<String, String>): Usuario? {
        val nombre = datosBase["nombre"] ?: return null
        val email = datosBase["email"] ?: return null
        val departamento = datosBase["departamento"]

        return Usuario().run {
            apply {
                this.nombre = nombre
                this.email = email
            }.also {
                if (departamento == "IT") {
                    it.roles.add("IT_USER")
                    it.configuracion.tema = "oscuro"
                }
            }
        }
    }

    fun procesarLoteUsuarios(usuarios: List<Usuario>): List<Usuario> {
        return usuarios.map { usuario ->
            usuario.apply {
                activo = true
            }.also {
                if (it.roles.isEmpty()) {
                    it.roles.add("USER")
                }
            }.apply {
                configuracion.notificaciones = true
            }.run {
                if (nombre == "Admin") {
                    roles.add("ADMIN")
                    configuracion.nivelPrivacidad = 3
                }
                this
            }
        }
    }

    fun parsearYCrearUsuario(datosRaw: String): Usuario? {
        return try {
            val pares = datosRaw.split("|")
                .filter { it.isNotBlank() }
                .associate {
                    val tokens = it.split(":")
                    tokens[0].trim() to tokens[1].trim()
                }

            val idStr = pares["id"] ?: return null
            val nombre = pares["nombre"] ?: return null
            val email = pares["email"] ?: return null

            Usuario().apply {
                this.id = idStr.toInt()
                this.nombre = nombre
                this.email = email
                this.activo = pares["activo"]?.toBoolean() ?: false
                pares["roles"]?.let { rStr ->
                    this.roles = rStr.split(",").map { it.trim() }.toMutableList()
                }
                this.configuracion = ConfiguracionUsuario().apply {
                    pares["tema"]?.let { this.tema = it }
                    pares["idioma"]?.let { this.idioma = it }
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}

