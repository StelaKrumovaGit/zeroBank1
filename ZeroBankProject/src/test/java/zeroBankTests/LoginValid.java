package zeroBankTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.SignInPage;
import ZeroBankUtils.DP;
import ZeroBankUtils.base;

public class LoginValid extends base {

	/*
	 * SCOPE of the test -Navigate to SignIn > Login Page -Verify all elements on
	 * SignIn page exists -Verify all elements on Login page exists -do Login with
	 * valid crentials -Assert successful Login -used @DataProvider with single set
	 * of data .
	 **/

	@BeforeMethod
	public void setUp() {
		baseUrl = "http://zero.webappsecurity.com/";
		base.openUrl(baseUrl);
	}

	@Test(dataProvider = "myData", 
			dataProviderClass = DP.class, 
			alwaysRun = true, 
			description = "Navigate to SignIn > Login Page,"
			+ "Verify all elements on SignIn and Login page exists,"
			+ "do Login and Assert success login",
			enabled = true)
	public void loginValid(String user, String password) throws InterruptedException {
		/**
		 * create an object from SignInPage and perform all autmatic check from the
		 * constructor
		 */
		SignInPage = new SignInPage();
		/* click on SignInButton and navigate to the Login Page */
		SignInPage.signInButton().click();

		/**
		 * create object of Login page after the click and perform all checks from the
		 * Login page constructor
		 */
		LoginPage = new LoginPage();
		LoginPage.enterUserName(user);
		LoginPage.enterPassword(password);

		/**
		 * @clickOnSignInButton() returns AccountSummaryPage constructor call click
		 *                        method > after successful Login perform all checks
		 *                        from the AccountSummaryPage page constructor
		 */
		LoginPage.clickOnSignInButton();
		AccountSummaryPage = new Pages.AccountSummaryPage();
		Assert.assertTrue(AccountSummaryPage.cashAccountsTitle().getText().equals("Cash Accounts"));

	}

	
}
