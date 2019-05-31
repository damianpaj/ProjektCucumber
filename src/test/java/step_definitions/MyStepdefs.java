package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.Da;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class MyStepdefs {

    private WebDriver driver;
    public String nipBox;

       @Given("there is browser opened with page {string}")
    public void thereIsBrowserOpenedWithPage(String url) {
           System.setProperty("webdriver.chrome.driver", "/home/damianpajonk/chromedriver");
           driver = new ChromeDriver();
           driver.manage().window().maximize();
           driver.get(url);
    }

    @When("user click on registration button")
    public void userClickOnRegistrationButton() {
        driver.findElement(By.cssSelector(".link.main-page-top__email-input-sent")).click();
    }

    @Then("user is on registration page")
    public void userIsOnRegistrationPage() {
        String assertText = driver.findElement(By.className("registration__header")).getText();
        assertEquals(assertText, "Zarejestruj się");
    }

        @When("user fills all mandatory fields with values")
    public void userFillsAllMandatoryFieldsWithValues(DataTable dataTable) {
           driver.findElement(By.id("person")).click();
        List<List<String>> lists = dataTable.asLists();

        for (List<String> list : lists){
            driver.findElement(By.id(list.get(0))).sendKeys(list.get(1));
        }
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.id("register-submit-btn")).click();
    }


    @And("user is loggedIn")
    public void userIsLoggedIn() {
        String text = driver.findElement(By.xpath("//a [@href='/reservations']")).getText();
        System.out.println(text);
        assertEquals("Moja rezerwacje", text);
    }

    @Then("close the page")
    public void closeThePage() {
           driver.quit();
    }

    @Then("user get NIP from page")
    public void userGetNIPFromPage() {

        driver.findElement(By.xpath("//a[@onclick='setGeneratedNip()']")).click();
        nipBox = driver.findElement(By.id("nipBox")).getText();

    }

    @When("user fills all company mandatory fields with values")
    public void userFillsAllCompanyMandatoryFieldsWithValues(DataTable dataTable) {
        driver.findElement(By.id("company")).click();
        List<List<String>> lists = dataTable.asLists();

        for (List<String> list : lists){
            driver.findElement(By.id(list.get(0))).sendKeys(list.get(1));
        }

        driver.findElement(By.id("fos_user_registration_form_nip")).sendKeys(nipBox);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        driver.findElement(By.id("register-submit-btn")).click();
    }
}