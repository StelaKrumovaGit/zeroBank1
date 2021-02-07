package Pages;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import ZeroBankUtils.base;

public class AccountSummaryPage extends base {
 
	
	 
	
	public WebElement cashAccountsTitle () {
		return element = super.findElement("xpath", "//h2[contains(text(),'Cash Accounts')]");
	}
	
	public WebElement cashAccountsAccountTxt () {
		return element = super.findElement("cssSelector", ".board:nth-child(2) tr th:nth-of-type(1)");
	}
	
	public WebElement cashAccountsBallanceTxt  () {
		return element = super.findElement("cssSelector", "div:nth-of-type(1) > .board-content > .table > thead > tr > th:nth-of-type(3)");
	}
	
	public WebElement cashAccountsSavingsLink () {
		return element = super.findElement("cssSelector", "div:nth-of-type(1) > .board-content > .table > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > a");
	}
	
	public WebElement cashAccountsSavingsLink2 () {
		return element = super.findElement("cssSelector", "div:nth-of-type(1) > .board-content > .table > tbody > tr:nth-of-type(2) > td:nth-of-type(1) > a");
	}
	
	public WebElement investmentAccountsTitle () {
		return element = super.findElement("cssSelector", ".offset2.span8 > h2:nth-of-type(2)");
	}
	
	public WebElement investmentAccountsBrokerageLink () {
		return element = super.findElement("cssSelector", "div:nth-of-type(2) > .board-content > .table a");
	}
	
	public WebElement creditAccountsTitle () {
		return element = super.findElement("cssSelector", ".offset2.span8 > h2:nth-of-type(3)");
	}
	
	public WebElement creditAccountsCheckingLink () {
		return element = super.findElement("xpath", "//a[contains(text(),'Checking')]");
	}
	
	public WebElement creditAccountsCreditCardLink () {
		return element = super.findElement("xpath", "//a[contains(text(),'Credit Card')]");
	}
	
	public WebElement loanAccountsTitle () {
		return element = super.findElement("xpath", "//h2[contains(text(),'Loan Accounts')]");
	}

	public WebElement loanAccountsLoanLink () {
		return element = super.findElement("xpath", "//a[contains(text(),'Loan')]");
	}
	
	public WebElement creditCardDetails () {
		return element = super.findElement("cssSelector", "div:nth-of-type(3) > .board-content > .table > tbody > tr:nth-of-type(1) > td:nth-of-type(2)");
	}
	
	
	public void navigateToAccountSummaryLink (WebElement ele) {
		try {
			base.jSClick(ele);
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public AccountSummaryPage() {
		super.assertElementIsDisplayed(cashAccountsTitle());
		super.assertElementIsDisplayed(cashAccountsAccountTxt());
		super.assertElementIsDisplayed(cashAccountsBallanceTxt());
		super.assertElementIsDisplayed(cashAccountsSavingsLink());
		super.assertElementIsDisplayed(cashAccountsSavingsLink2());
		System.out.println("All Elements on Account Summary Page are displayed");
		Reporter.log("All Elements on Account Summary Page are displayed");
		
	}

}
