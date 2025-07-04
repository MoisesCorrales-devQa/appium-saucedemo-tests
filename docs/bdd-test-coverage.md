# 📋 Matriz de Cobertura de Pruebas BDD (Mobile App)

Este documento resume las funcionalidades automatizadas con Cucumber & Gherkin en el proyecto de pruebas móviles.

| ID       | Feature                    | Escenario                                             | Automatizado | Archivo Feature         | Comentarios          |
|----------|----------------------------|-------------------------------------------------------|--------------|-------------------------|----------------------|
| F001-S01 | Login de usuario           | Login válido                                          | ✅ Sí         | login.feature           |                      |
| F001-S02 | Login de usuario           | Login fallido                                         | ✅ Sí         | login.feature           |                      |
| F001-S03 | Login de usuario           | Login bloqueado                                       | ✅ Sí         | login.feature           |                      |
| F001-S04 | Login de usuario           | Login con contraseña vacía                            | ✅ Sí         | login.feature           |                      |
| F001-S05 | Login de usuario           | Login con usuario vacío                               | ✅ Sí         | login.feature           |                      |
| F002-S01 | Funcionalidad del carrito  | Añadir un producto al carrito                         | ✅ Sí         | cart.feature            |                      |
| F002-S02 | Funcionalidad del carrito  | Eliminar un producto del carrito                      | ✅ Sí         | cart.feature            |                      |
| F002-S03 | Funcionalidad del carrito  | Añadir múltiples productos y verificar el total       | ✅ Sí         | cart.feature            |                      |
| F003-S01 | Funcionalidad del checkout | Compra exitosa con usuario válido y productos         | ✅ Sí         | checkout.feature        | Escenario end-to-end |
| F003-S02 | Funcionalidad del checkout | Validación de campos obligatorios en formulario envío | ✅ Sí         | checkout.feature        | Escenario Outline    |
| F003-S03 | Funcionalidad del checkout | Validación de campos obligatorios en formulario pago  | ✅ Sí         | checkout.feature        | Escenario Outline    |
| F004-S01 | Acceso a menú contextual   | Acceso a catálogo                                     | ✅ Sí         | contextual-menu.feature |                      |
| F004-S02 | Acceso a menú contextual   | Acceso a geolocalización                              | ✅ Sí         | contextual-menu.feature |                      |
| F004-S03 | Acceso a menú contextual   | Acceso a reset app                                    | ✅ Sí         | contextual-menu.feature |                      |
| F004-S04 | Acceso a menú contextual   | Acceso a webview                                      | ✅ Sí         | contextual-menu.feature |                      |
| F004-S05 | Acceso a menú contextual   | Acceso a QR                                           | ✅ Sí         | contextual-menu.feature |                      |
| F004-S06 | Acceso a menú contextual   | Acceso a dibujo                                       | ✅ Sí         | contextual-menu.feature |                      |
| F004-S07 | Acceso a menú contextual   | Acceso a acerca de                                    | ✅ Sí         | contextual-menu.feature |                      |
| F004-S08 | Acceso a menú contextual   | Acceso a iniciar sesión                               | ✅ Sí         | contextual-menu.feature |                      |
| F004-S09 | Acceso a menú contextual   | Acceso a cerrar sesión                                | ✅ Sí         | contextual-menu.feature |                      |
| F004-S10 | Acceso a menú contextual   | Acceso a API                                          | ✅ Sí         | contextual-menu.feature |                      |
| F004-S11 | Acceso a menú contextual   | Acceso a video sauce bot                              | ✅ Sí         | contextual-menu.feature |                      |

