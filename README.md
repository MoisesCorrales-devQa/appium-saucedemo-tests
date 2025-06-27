# Proyecto de Automatización de Pruebas en Aplicaciones Móviles

Este proyecto tiene como propósito **demostrar mis capacidades como ingeniero de automatización de pruebas (QA Automation Engineer)** en el contexto de aplicaciones móviles. Para ello, he desarrollado dos enfoques diferenciados:

- Un enfoque tradicional de pruebas automatizadas con **Appium + Java + JUnit 5**.
- Un enfoque basado en **BDD (Behavior-Driven Development)** con **Appium + Java + JUnit 4 + Cucumber**.

Cada enfoque está desarrollado en su propia rama dentro del repositorio para mayor claridad y separación de responsabilidades.

---

## 🔧 Tecnologías utilizadas

| Tecnología   | Descripción                                         |
|--------------|-----------------------------------------------------|
| **Appium**   | Framework para automatización de apps móviles       |
| **Java**     | Lenguaje principal del proyecto                     |
| **JUnit 5**  | Testing framework usado en la versión estándar      |
| **JUnit 4**  | Utilizado en la versión Cucumber por compatibilidad |
| **Cucumber** | Framework BDD para pruebas legibles por negocio     |

---

## 📁 Estructura del Proyecto

- **`feature/junit-tests`**: contiene pruebas funcionales desarrolladas con Appium y JUnit 5.
- **`feature/cucumber-tests`**: contiene pruebas escritas en estilo BDD con Cucumber, Gherkin y Appium.

Cada rama cuenta con su propia estructura organizada, clases separadas por funcionalidad y documentación mínima en código para facilitar su comprensión.

---

## ✅ Casos de Prueba Cubiertos

Actualmente, la suite automatizada cubre las siguientes funcionalidades clave de la aplicación:

### 🔐 Autenticación (Login/Logout)
- Login exitoso
- Login con credenciales incorrectas
- Login de usuario bloqueado
- Logout exitoso
- Cancelación de logout en mitad del flujo

### 📱 Navegación
- Validación de todas las opciones del menú contextual

### 🛒 Carrito de Compras
- Añadir productos al carrito
- Eliminar un producto del carrito
- Añadir múltiples productos y verificar que el precio total del carrito se calcula correctamente

---

## 🚀 Cómo ejecutar las pruebas

1. **Clonar el repositorio** y posicionarte en la rama deseada:

   ```bash
   git clone https://github.com/tu_usuario/tu_repo.git
   git checkout feature/junit-tests  # o feature/cucumber-tests
   ```

2. **Instalar las dependencias del proyecto**:

   ```bash
   mvn clean install
   ```

3. **Iniciar el servidor Appium** (localmente o en SauceLabs).

4. **Iniciar un emulador Android o conectar un dispositivo físico.**

5. **Configurar las capabilities necesarias** en la clase `DriverFactory.java` antes de ejecutar las pruebas.

6. **Ejecutar las pruebas** con Maven:

   ```bash
   mvn test
   ```

También puedes ejecutar las pruebas desde un entorno como IntelliJ IDEA o VS Code si prefieres una ejecución más visual.

---

## 📈 Posibilidades de Expansión

Mi objetivo es continuar ampliando esta suite de pruebas para cubrir la mayor cantidad posible de funcionalidades, incluyendo:
- Escenarios más complejos de usuario
- Validaciones en dispositivos reales
- Integración con pipelines de CI/CD
- Pruebas multiplataforma Android/iOS

---

## 🙋 Sobre el autor

Este repositorio forma parte de mi **portafolio como QA Automation Engineer** especializado en testing móvil.  
Estoy abierto a colaboraciones y oportunidades freelance o profesionales.

Puedes contactar conmigo a través de [LinkedIn](https://www.linkedin.com/) o visitar mi [perfil de GitHub](https://github.com/tu_usuario).
