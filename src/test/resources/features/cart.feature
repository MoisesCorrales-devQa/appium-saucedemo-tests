Feature: Funcionalidad del carrito de compras

  Scenario: Añadir un producto al carrito
    Given que el usuario está en la pantalla del catálogo
    When el usuario añade el primer producto al carrito
    Then el producto debe estar visible en el carrito

  Scenario: Eliminar un producto del carrito
    Given que el usuario añadió un producto al carrito
    When el usuario elimina el producto del carrito
    Then el carrito debe estar vacío

  Scenario: Añadir múltiples productos y verificar el total
    Given que el usuario está en la pantalla del catálogo
    When el usuario añade múltiples productos al carrito
    Then la suma total debe coincidir con el total mostrado en el carrito
