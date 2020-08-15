package ru.geekbrains.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static ru.geekbrains.steps.InitSteps.getWebDriver;

public class CategorySteps {

    @Then("Go to Category page")
    public void goToCategoryPage() {
        getWebDriver().get(DriverInitializer.getProperty("url.admin.categories"));
    }

    @And("Fill new category name {}")
    public void fillNewCategoryName(String category) {
        getWebDriver().findElement(By.id("newCategory"))
                .sendKeys(category);
    }

    @And("Click add category button")
    public void clickAddCategoryButton() throws InterruptedException {
        getWebDriver().findElement(By.id("btnAddCategory"))
                .click();
        Thread.sleep(2000);
    }

    @And("Check {} exists")
    public void checkNewCategoryExists(String category) {
        if (!findLineByCategoryName(category).isPresent()) {
            fail("No category");
        }
    }

    @And("Find and delete created {}")
    public void findAndDeleteCreatedCategory(String category) throws InterruptedException {
        Optional<WebElement> element = findLineByCategoryName(category);
        if (!element.isPresent()) {
            fail("No category");
            return;
        }
        element.get()
                .findElement(By.name("btnDelete"))
                .click();
        Thread.sleep(2000);
    }

    private Optional<WebElement> findLineByCategoryName(String name) {
        List<WebElement> lines = getWebDriver().findElements(By.cssSelector("tr"));
        for (WebElement line : lines) {
            List<WebElement> elements = line.findElements(By.cssSelector("td:nth-of-type(1) span"));
            if (!elements.isEmpty() && elements.get(0).getText().equals(name)) {
                return Optional.of(line);
            }
        }
        return Optional.empty();
    }

    @And("Check there are no created category: {}")
    public void checkThereAreNoCreatedCategory(String category) {
        if (findLineByCategoryName(category).isPresent()) {
            fail("Category exists");
        }
    }
}
