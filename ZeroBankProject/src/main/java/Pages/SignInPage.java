package Pages;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ZeroBankUtils.base;


public class SignInPage extends base{
	
	
	
	
	/*Zero Bank top left logo/button */
	public WebElement logo() {
		return element = super.findElement("xpath", "//a[contains(text(),'Zero Bank')]");
	}
	
	/*Serach field  */
	public WebElement searchField () {
		return element = super.findElement("xpath", "//input[@id='searchTerm']");
	}
	
	public WebElement homeTab() {
		return element = super.findElement("cssSelector", "li#homeMenu > div");
	}
	
	public WebElement onlineBankingTab() {
		return element = super.findElement("xpath", "//strong[contains(text(),'Online Banking')]");
	}
	
	public WebElement feedBackTab() {
		return element = super.findElement("cssSelector", "li#feedback > div");
	}
	
	public WebElement signInButton () {
		return element = super.findElement("xpath", "//button[@id='signin_button']");
	}
	
	public WebElement moreServicesButton() {
		return element = super.findElement("xpath", "//a[@id='online-banking']");
	}
	
	/*this method returns a LoginPage constructor anfter click on the SignInButton
	 * and performs all necessary checks from the LoginPage automatically with from the 
	 * LoginPage constructor*/
	public LoginPage clickOnSignInButtonAndLandOnLogin () {
		try {
			signInButton().click();
		} catch (ElementNotFoundException e) {
			JSclickOnWebElement(signInButton());
		}
		
		return LoginPage;
		
	}

	
	/* TO DO find onthers things on the page s**/
	
	public LoginPage clickOnSignIn () {
		base.jSClick(signInButton());
		return new LoginPage();
	}
	/** page consructor containing boolean isDisplayed checks 
	 *  for particular elements are displayed.
	 *  perform the check each time object is created.
	 * */
	public SignInPage() {
		base.assertElementIsDisplayed(logo());
		base.assertElementIsDisplayed(searchField());
		base.assertElementIsDisplayed(homeTab());
		base.assertElementIsDisplayed(onlineBankingTab());
		base.assertElementIsDisplayed(feedBackTab());
		base.assertElementIsDisplayed(signInButton ());
		base.assertElementIsDisplayed(moreServicesButton());
		Reporter.log("All elements from SignIn Page are Displayed");
		System.out.println("All elements from SignIn Page are Displayed");
	}
}
