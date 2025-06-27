package suites;

import mispruebas.myDemoApp.auth.LoginTests;
import mispruebas.myDemoApp.auth.LogoutTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginTests.class,
        LogoutTests.class
})
public class LoginLogoutSuite {
}
