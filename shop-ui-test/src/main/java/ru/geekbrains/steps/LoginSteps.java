package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("url.admin.login"));
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.className("btn"));
        webElement.click();
        Thread.sleep(2000);
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);
        Thread.sleep(1000);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
        Thread.sleep(1000);
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText().trim()).isEqualTo(name);
    }

    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("usermenu"));
        Thread.sleep(1000);
        webElement.click();
        Thread.sleep(3000);
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("logoutForm")).findElement(By.tagName("a"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("username"));
        webDriver.findElement(By.id("password"));
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
