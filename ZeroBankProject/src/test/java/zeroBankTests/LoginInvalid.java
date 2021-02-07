package zeroBankTests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.SignInPage;
import ZeroBankUtils.DP;
import ZeroBankUtils.base;

public class LoginInvalid extends base {

	/*
	 * SCOPE of the test:
	 * - Navigate to SignIn > Login Page Verify all elements on
	 * - SignIn page exists Verify all elements on Login page exists
	 * - try to Login with different Invalid crentials and Assert proper Error message appears 
	 *   - used @DataProvider for multiple parsing of invalid data.
	 **/

	@BeforeClass
	public void before() {
		baseUrl = "http://zero.webappsecurity.com/";
		base.openUrl(baseUrl);
		super.SignInPage = new SignInPage();
		SignInPage.clickOnSignInButtonAndLandOnLogin();
	}

	@Test(dataProvider = "myData", dataProviderClass = DP.class, enabled = true)
	public void loginInvalid(String user, String password) {
		/**
		 * create object of Login page after the click and perform all checks from the
		 * Login page constructor
		 */

		LoginPage = new LoginPage();
		LoginPage.enterUserName(user);
		LoginPage.enterPassword(password);
		LoginPage.signInButton().click();
		super.verifyEqualTexts(LoginPage.errorMessInvalidData(), "Login and/or password are wrong.");

	}

}
