The Zero Bank project is a POM Maven project with ReportNG reporting

-base.java = base class having major methods used in the tests

-DP.java = @DataProvider class providing data to the tests

-Listeners.java - listener class applying actions over the tests - OnTestStart,onTestSuccess,onTestFinish...

package-ZeroBankUtils - contains the pages of the tested application

package-ZeroBankTests - contains the test clasess

tests should be runned from : \ZeroBankProject\Suites\LoginTestsSuite.xml

to trigger the ReportNG

location of the report:  \ZeroBankProject\test-output\html\index.html
