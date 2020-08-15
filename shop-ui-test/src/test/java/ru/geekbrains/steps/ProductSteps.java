package ru.geekbrains.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static ru.geekbrains.steps.InitSteps.getWebDriver;

public class ProductSteps {

    @Then("Go to product page")
    public void goToProductPage() {
        getWebDriver().get(DriverInitializer.getProperty("url.admin.products"));
    }

    @And("Click to Add Product button")
    public void clickToAddProductButton() throws InterruptedException {
        getWebDriver().findElement(By.id("btnAddProduct"))
                .click();
        Thread.sleep(2000);
    }

    @Then("Fill form with {}, {}, {}, {}")
    public void fillFormWithProductInfo(String title, String description, String category, String price) {
        getWebDriver().findElement(By.id("name"))
                .sendKeys(title);
        getWebDriver().findElement(By.id("description"))
                .sendKeys(description);
        getWebDriver().findElement(By.id("category"))
                .sendKeys(category);
        getWebDriver().findElement(By.id("price"))
                .sendKeys(price);
    }

    @And("Click Add button")
    public void clickAddButton() throws InterruptedException {
        getWebDriver().findElement(By.id("btnAddProduct"))
                .click();
        Thread.sleep(2000);
    }

    @Then("Fill filter with {} and set filter by button click")
    public void fillFilterWithTitleAndSetFilterByButtonClick(String title) throws InterruptedException {
        getWebDriver().findElement(By.id("titlePart"))
                .sendKeys(title);
        getWebDriver().findElement(By.id("btnSetFilter"))
                .click();
        Thread.sleep(2000);
    }

    @And("Find on page product: {}, {}, {}, {}")
    public void findOnPageProductInfo(String title, String description, String category, String price) {
        if (!findLineByProductInfo(title, description, category, price).isPresent()) {
            fail("No product");
        }
    }

    @And("Find product: {}, {}, {}, {} and click delete button")
    public void findAndClickDeleteButton(String title, String description, String category, String price) throws InterruptedException {
        Optional<WebElement> element = findLineByProductInfo(title, description, category, price);
        if (!element.isPresent()) {
            fail("No product");
            return;
        }
        element.get()
                .findElement(By.name("btnDelete"))
                .click();
        Thread.sleep(2000);
    }

    private Optional<WebElement> findLineByProductInfo(String title, String description, String category, String price) {
        List<WebElement> lines = getWebDriver().findElements(By.cssSelector("tr"));
        for (WebElement line : lines) {
            List<WebElement> categoryElements = line.findElements(By.cssSelector("td:nth-of-type(1)"));
            List<WebElement> titleElements = line.findElements(By.cssSelector("td:nth-of-type(2)"));
            List<WebElement> descElements = line.findElements(By.cssSelector("td:nth-of-type(3)"));
            List<WebElement> priceElements = line.findElements(By.cssSelector("td:nth-of-type(4)"));
            if (!titleElements.isEmpty() && titleElements.get(0).getText().equals(title)
                    && !descElements.isEmpty() && descElements.get(0).getText().equals(description)
                    && !categoryElements.isEmpty() && categoryElements.get(0).getText().equals(category)
                    && !priceElements.isEmpty() &&
                    NumberUtils.createBigDecimal(priceElements.get(0).getText().replace(",", ".")).compareTo(NumberUtils.createBigDecimal(price)) == 0) {
                return Optional.of(line);
            }
        }
        return Optional.empty();
    }

    @And("Check there are no created product: {}, {}, {}, {}")
    public void checkEmptyProductTable(String title, String description, String category, String price) {
        if (findLineByProductInfo(title, description, category, price).isPresent()) {
            fail("Product exists");
        }
    }
}
