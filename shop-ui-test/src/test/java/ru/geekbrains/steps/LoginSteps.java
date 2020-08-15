package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import ru.geekbrains.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.steps.InitSteps.getWebDriver;

public class LoginSteps {

    @When("Go to login page")
    public void iNavigateToLoginHtmlPage() {
        getWebDriver().get(DriverInitializer.getProperty("url.admin.login"));
    }

    @When("Fill form with {} and {}")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws InterruptedException {
        getWebDriver().findElement(By.id("username"))
                .sendKeys(username);
        Thread.sleep(1000);
        getWebDriver().findElement(By.id("password"))
                .sendKeys(password);
        Thread.sleep(1000);
    }

    @When("Click on login button")
    public void iClickOnLoginButton() throws InterruptedException {
        getWebDriver().findElement(By.className("btn"))
                .click();
        Thread.sleep(2000);
    }

    @Then("Check name that should be {}")
    public void nameShouldBe(String name) {
        assertThat(getWebDriver().findElement(By.id("dd_user")).getText().trim()).isEqualTo(name);
    }

    @When("Open dropdown menu")
    public void openDropDownMenu() throws InterruptedException {
        getWebDriver().findElement(By.id("usermenu"))
                .click();
        Thread.sleep(3000);
    }

    @When("Click logout button")
    public void clickLogoutButton() {
        getWebDriver().findElement(By.id("logoutForm")).findElement(By.tagName("a"))
                .click();
    }

    @Then("User logged out")
    public void userLoggedOut() {
        getWebDriver().findElement(By.id("username"));
        getWebDriver().findElement(By.id("password"));
    }

    @After
    public void quitBrowser() {
        getWebDriver().quit();
    }
}
