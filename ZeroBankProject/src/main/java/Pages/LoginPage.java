package Pages;

import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ZeroBankUtils.base;

public class LoginPage extends base{
	
	
	
	public WebElement loginField () {
		return element = super.findElement("xpath", "//input[@id='user_login']");
	}
	
	public WebElement passwordField () {
		return element = super.findElement("xpath", "//input[@id='user_password']");
	}
	
	public WebElement keepMeSignInCheckbox () {
		return element = super.findElement("cssSelector", "#user_remember_me");
	}
	
	public WebElement signInButton () {
		return element = super.findElement("cssSelector", "input[name='submit']");
	}
	
	public WebElement forgotYourPasswordLink () {
		return element = super.findElement("cssSelector", ".wrapper > .container a");
	}
	
	public WebElement errorMessInvalidData () {
		return element = super.findElement("xpath", "//div[contains(text(),'Login and/or password are wrong.')]");
	}
	
	public void enterUserName (String user) {
		try {
			base.findFieldAndSentKeys(loginField(), user);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void enterPassword (String password) {
		try {
			base.findFieldAndSentKeys(passwordField(), password);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public AccountSummaryPage clickOnSignInButton () {
		try {
			signInButton().click();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		return new AccountSummaryPage();
	}
	
	/*TO DO methods for other things on the page*/
	
	
	
	
	
	/*constructor having verification for all page elemtns*/
	public LoginPage() {
		base.assertElementIsDisplayed(loginField());
		base.assertElementIsDisplayed(passwordField());
		base.assertElementIsDisplayed(keepMeSignInCheckbox());
		base.assertElementIsDisplayed(signInButton());
		base.assertElementIsDisplayed(forgotYourPasswordLink());
		
	}

}
