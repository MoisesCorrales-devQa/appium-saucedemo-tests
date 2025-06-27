package suites;

import myDemoAppTests.auth.LoginTests;
import myDemoAppTests.auth.LogoutTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
        LoginTests.class,
        LogoutTests.class
})
public class LoginLogoutSuite {
}
