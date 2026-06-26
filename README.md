# Trabajo Práctico: Colecciones Funcionales y Lambdas

## Objetivo General

Este trabajo práctico tiene como objetivo introducir y evaluar el dominio de las operaciones funcionales sobre colecciones en Kotlin, el uso de expresiones lambda y las scope functions. A través de ejercicios guiados por tests (TDD), los estudiantes desarrollarán habilidades para escribir código más expresivo y funcional.

## Temas Evaluados

1. **map** - Transformación de elementos en colecciones
2. **filter** - Filtrado de elementos según predicados
3. **find** - Búsqueda de elementos específicos
4. **any** - Verificación de existencia con predicados
5. **all** - Validación completa de colecciones
6. **sortBy** - Ordenamiento personalizado
7. **lambda** - Expresiones lambda y sintaxis
8. **funciones como argumentos** - Funciones de orden superior
9. **it** - Parámetro implícito en lambdas
10. **run** - Scope function para ejecutar bloques
11. **apply** - Scope function para configuración
12. **also** - Scope function para efectos secundarios

## Reglas de Trabajo

### Metodología TDD (Test Driven Development)

1. **NO modifiques los tests**: Los tests proporcionados son tu especificación
2. **Ciclo Red-Green-Refactor**:
   - **Red**: Ejecuta los tests y observa que fallan
   - **Green**: Implementa el código mínimo para que pasen
   - **Refactor**: Mejora el código manteniendo los tests en verde

3. **Progresión incremental**: Resuelve los tests de uno en uno
4. **Commits frecuentes**: Realiza commits cada vez que logres pasar un test

### Estructura del Proyecto

```
colecciones-funcionales-y-lambdas/
├── build.gradle.kts
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       ├── ProductoManager.kt         # Ejercicio 1
│   │       ├── GestorTareas.kt           # Ejercicio 2
│   │       ├── SistemaRanking.kt         # Ejercicio 3
│   │       ├── ProcesadorTransacciones.kt # Ejercicio 4
│   │       └── UsuarioBuilder.kt         # Ejercicio 5
│   └── test/
│       └── kotlin/
│           ├── Ejercicio1MapFilterTest.kt
│           ├── Ejercicio2FindAnyAllTest.kt
│           ├── Ejercicio3SortByLambdaTest.kt
│           ├── Ejercicio4FuncionesComoArgumentosTest.kt
│           └── Ejercicio5ItScopeFunctionsTest.kt
└── README.md
```

## Ejercicios

### Ejercicio 1: Map y Filter
Implementa la clase `ProductoManager` que gestione productos usando transformaciones y filtros.

**Conceptos clave**:
- Transformar listas de objetos en listas de valores
- Filtrar elementos según condiciones
- Combinar map y filter en pipelines

### Ejercicio 2: Find, Any y All
Implementa `GestorTareas` para gestionar tareas con operaciones de búsqueda y validación.

**Conceptos clave**:
- Buscar elementos específicos con `find`
- Verificar existencia con `any`
- Validar condiciones completas con `all`

### Ejercicio 3: SortBy y Lambdas
Desarrolla `SistemaRanking` para ordenar empleados según diferentes criterios.

**Conceptos clave**:
- Ordenamiento personalizado con lambdas
- Lambdas con cálculos complejos
- Ordenamiento múltiple y condicional

### Ejercicio 4: Funciones como Argumentos
Crea `ProcesadorTransacciones` que reciba funciones para procesar datos.

**Conceptos clave**:
- Funciones de orden superior
- Estrategias de procesamiento configurables
- Composición de funciones

### Ejercicio 5: It y Scope Functions
Construye `UsuarioBuilder` utilizando scope functions y el parámetro implícito `it`.

**Conceptos clave**:
- Uso eficiente de `it` en lambdas
- `run` para cálculos y decisiones
- `apply` para configuración de objetos
- `also` para efectos secundarios
- Combinación de scope functions

## Instrucciones de Ejecución

### Requisitos Previos
- Java 17 o superior
- Gradle 8.0 o superior (se incluye wrapper)

### Ejecutar Tests Localmente

#### Usando Gradle Wrapper (Recomendado)

**Linux/Mac:**
```bash
./gradlew test
```

**Windows:**
```cmd
gradlew.bat test
```

#### Ejecutar tests específicos

Para ejecutar solo los tests de un ejercicio:
```bash
./gradlew test --tests "Ejercicio1MapFilterTest"
```

Para ejecutar un test específico:
```bash
./gradlew test --tests "Ejercicio1MapFilterTest.obtenerNombresDeProductos"
```

#### Ver reporte detallado

Los reportes de tests se generan en:
```
build/reports/tests/test/index.html
```

### Verificar Progreso

Para ver un resumen del estado de los tests:
```bash
./gradlew test --info
```

## Criterios de Evaluación

1. **Funcionalidad (40%)**: Todos los tests deben pasar
2. **Uso correcto de funciones (30%)**: Aplicación apropiada de map, filter, find, etc.
3. **Expresividad del código (20%)**: Uso idiomático de Kotlin y lambdas
4. **Proceso TDD (10%)**: Evidencia de commits incrementales

## Tips y Recomendaciones

### Para map y filter
- Recuerda que `map` transforma cada elemento
- `filter` retorna una nueva lista con elementos que cumplen la condición

### Para find, any y all
- `find` retorna el primer elemento o null
- `any` retorna true si al menos uno cumple
- `all` retorna true solo si todos cumplen

### Para sortBy
- `sortBy` ordena ascendentemente por defecto
- Para orden descendente usa `sortByDescending`
- Puedes ordenar por expresiones calculadas

### Para lambdas
- Usa `it` cuando la lambda tiene un solo parámetro
- Las lambdas pueden capturar variables del scope externo
- Prefiere lambdas concisas y expresivas

### Para scope functions
- `run`: úsalo para cálculos que requieren un resultado
- `apply`: ideal para configurar objetos
- `also`: perfecto para logging o efectos secundarios
- `let`: útil para transformaciones null-safe

## Recursos Adicionales

- [Documentación oficial de Kotlin - Collections](https://kotlinlang.org/docs/collections-overview.html)
- [Kotlin Koans - Ejercicios interactivos](https://play.kotlinlang.org/koans)
- [Scope Functions](https://kotlinlang.org/docs/scope-functions.html)

## Entrega

1. Clona este repositorio
2. Crea una rama con tu nombre: `git checkout -b nombre-apellido`
3. Implementa las soluciones siguiendo TDD
4. Asegúrate de que todos los tests pasen
5. Realiza push de tu rama
6. Crea un Pull Request

**Fecha límite**: Consultar con el docente

---

**Nota**: Este trabajo práctico está diseñado para ser resuelto de manera incremental. No intentes resolver todos los ejercicios de una vez. Sigue el proceso TDD y aprende de cada test que logres pasar.

¡Éxito!