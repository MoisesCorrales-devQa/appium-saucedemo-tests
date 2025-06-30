Feature: Funcionalidad del check out de productos

  Background:
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "bob@example.com" y password "10203040"
    And el usuario añade el primer producto al carrito

  @completeCheckout
  Scenario: Compra exitosa con usuario válido y productos

    When el usuario accede al proceso de checkout

    # Información de envío
    Then el usuario debería ver la pantalla de información de envío
    And introduce "Juan Pérez" como nombre
    And introduce "Calle Falsa 123" como dirección
    And introduce "Madrid" como ciudad
    And introduce "28080" como código postal
    And introduce "España" como país
    When confirma la información de envío

    # Datos de pago
    Then el usuario debería ver la pantalla de datos de pago
    And introduce "Juan Pérez" como nombre completo en los datos de pago
    And introduce "4111111111111111" como número de tarjeta
    And introduce "12/26" como fecha de caducidad
    And introduce "123" como código CVV
    When confirma los datos de pago

    # Pantalla de resumen
    Then el usuario debería ver la pantalla de resumen del pedido
    And verifica los productos en el resumen
    And verifica el precio total del pedido

    When confirma el pedido
    Then el mensaje de éxito "¡Gracias por tu pedido!" debe ser visible

  @shippingFormErrors
  Scenario Outline: Validación de campo obligatorio en formulario de envío
    When el usuario accede al proceso de checkout
    And deja vacio el campo de dirección "<campo>"
    And confirma la información de envío
    Then ve el mensaje de error "<mensajeError>"

    Examples:
      | campo         | mensajeError                           |
      | nombre        | Please provide your full name.         |
      | direccion     | Please provide your address.           |
      | ciudad        | Please provide your city.              |
      | codigo postal | Please provide your zip code.          |
      | pais          | Please provide your country.           |


  @paymentFormErrors
  Scenario Outline: Validación de campo obligatorio en formulario de pago
    When el usuario accede al proceso de checkout
    And el usuario rellena el formulario de dirección correctamente
    And confirma la información de envío
    And deja vacio el campo de pago "<campo>"
    And confirma los datos de pago
    Then ve el mensaje de error "<mensajeError>"

    Examples:
      | campo             | mensajeError                         |
      | nombre tarjeta    | Value looks invalid.            |
      | numero tarjeta    | Value looks invalid.            |
      | caducidad         | Value looks invalid.            |
      | cvv               | Value looks invalid.            |