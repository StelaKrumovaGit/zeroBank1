package zeroBankTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.LoginPage;
import ZeroBankUtils.DP;
import ZeroBankUtils.base;

public class AccountSummaryCreditCardAccounts extends base {

	/*  (Valid data test)
	 * Login to the application"
	 * Navigate to Account Summary and verify in Credit Accounts > Credit card Number
	 **/

	@BeforeClass
	public void setUp() {
		baseUrl = "http://zero.webappsecurity.com/login.html";
		base.openUrl(baseUrl);
		LoginPage = new LoginPage();
	

	}

	@Test(dataProvider = "myData", 
			dataProviderClass = DP.class, 
			alwaysRun = true, 
			description = "Login to the application"
			+ "Navigate to Account Summary and verify in Credit Accounts > Credit card Number",
			enabled = true,
			priority = 0)
	public void CreditCardVerfyValid(String user, String password,String card) throws InterruptedException {
		/**Necessary steps to Navigate to the Account Summary page */
		LoginPage.enterUserName(user);
		LoginPage.enterPassword(password);
		LoginPage.clickOnSignInButton();		
		AccountSummaryPage = new Pages.AccountSummaryPage();
		
		/*Get the Credit Card details ana save it in TXT*/
		try {
			super.FileWriter(AccountSummaryPage.creditCardDetails(), System.getProperty("user.dir")+"\\txt\\cardDetails.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String actualCardText =AccountSummaryPage.creditCardDetails().getText();
		actualCardText.replaceAll("\\s+","");  // replace all whitespaces from the Card text
		Assert.assertEquals(actualCardText, card);


	}

	/*  (Invalid data test)
	 * Login to the application"
	 * Navigate to Account Summary and try to verify in Credit Accounts > Credit card Number with not
	 * valid data.
	 * 
	 * This test should FAIL becasue of the verifying of invalid credit card number
	 **/
	
	
	@Test(dataProvider = "myData", 
			dataProviderClass = DP.class, 
			alwaysRun = true, 
			description = "Login to the application"
					+ "Navigate to Account Summary and verify in Credit Accounts > Credit card Number"
					+ "try to verify the Credit card details with invalid data",
			enabled = true,
			priority = 1)
	public void CreditCardVerfyInvalid(String user, String password,String card) throws InterruptedException {

		String actualCardText =AccountSummaryPage.creditCardDetails().getText();
		actualCardText.replaceAll("\\s+","");  // replace all whitespaces from the Card text
		Assert.assertEquals(actualCardText, card);


	}
	
}
