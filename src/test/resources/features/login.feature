Feature: Login de usuario

  @login_valido
  Scenario: Login válido
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "bob@example.com" y password "10203040"
    Then accede correctamente al panel principal

  @login_fallido
  Scenario: Login fallido
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "error@example.com" y password "error"
    Then se muestra el mensaje de no existe ningun usuario con esas credenciales

  @login_bloqueado
  Scenario: Login bloqueado
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "alice@example.com" y password "10203040"
    Then se muestra un mensaje de error de 'Usuario bloqueado'

  @login_empty_password
  Scenario: Login bloqueado
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "bob@example.com" y password ""
    Then se muestra un mensaje de error de contraseña requerida

  @login_empty_username
  Scenario: Login bloqueado
    Given el usuario abre la app y accede a la pestaña de login
    When introduce el email "" y password ""
    Then se muestra un mensaje de error de usuario requerido