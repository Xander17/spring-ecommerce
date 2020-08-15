package ru.geekbrains.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import ru.geekbrains.DriverInitializer;

public class InitSteps {

    private static WebDriver webDriver = null;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    @Given("Open web browser")
    public void iOpenBrowser() {
        InitSteps.webDriver = DriverInitializer.getDriver();
    }

    @Then("Quit web browser")
    public void quit() {
        webDriver.quit();
    }
}
