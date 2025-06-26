Feature: Acceso a menu contextual

  @catalogAccess
  Scenario: Acceso a catálogo
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "catálogo"
    Then se muestra la pantalla de "catálogo"

  @geoLocationAccess
  Scenario: Acceso a geolocalización
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "geolocalización"
    Then se muestra la pantalla de "geolocalización"

  @resetAppAccess
  Scenario: Acceso a reset app
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "reset app"
    Then se muestra el popup de "reset app"

  @webviewAccess
  Scenario: Acceso a webview
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "webview"
    Then se muestra la pantalla de "webview"

  @qrAccess
  Scenario: Acceso a QR
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "qr"
    Then se muestra la pantalla de "qr"

  @drawingAccess
  Scenario: Acceso a dibujo
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "dibujo"
    Then se muestra la pantalla de "dibujo"

  @aboutAccess
  Scenario: Acceso a acerca de
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "acerca de"
    Then se muestra la pantalla de "acerca de"

  @biometricsAccess
  Scenario: Acceso a biometría
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "biometría"
    Then se muestra la pantalla de "biometría"

  @loginAccess
  Scenario: Acceso a iniciar sesión
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "iniciar sesión"
    Then se muestra la pantalla de "iniciar sesión"

  @logoutAccess
  Scenario: Acceso a cerrar sesión
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "cerrar sesión"
    Then se muestra el popup de "cerrar sesión"

  @apiCallsAccess
  Scenario: Acceso a API
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "api"
    Then se muestra la pantalla de "api"

  @sauceBotVideoAccess
  Scenario: Acceso a video sauce bot
    Given el usuario abre la app y accede al menú contextual
    When el user accede al item "video sauce bot"
    Then se muestra la pantalla de "video sauce bot"



