package com.example.Test3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3Test {

    private static WebDriver driver;
    @BeforeAll
     static void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    void startUp(){
        driver.get ("https://www.svtplay.se");

        driver.manage().window().maximize();

        WebElement cookieSettings = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div/div[2]/button[3]"));
        cookieSettings.click();
    }

    @Test
    void checkwebtitle() {

        String websiteTitle = driver.getTitle();

        assertEquals("SVT Play", websiteTitle, "Title do not match");
    }

    @Test
    void CheckSvtLogoVisable() {

        Boolean Display = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/a")).isDisplayed();

        assertTrue(Display);
    }

    @Test
    void CheckMenuStart() {

        String menuStart = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[1]")).getText();

        assertEquals("START", menuStart, "Menu item is incorrect");
    }

    @Test
    void CheckMenuProgram() {

        String menuProgram = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]")).getText();

        assertEquals("PROGRAM", menuProgram, "Menu item is incorrect");
    }

    @Test
    void CheckMenuKanaler() {

        String menuKanaler = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]")).getText();

        assertEquals("KANALER", menuKanaler, "Menu item is incorrect");
    }

    @Test
    void checkProgramCategories(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement programsLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a"));

        programsLink.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-3b830fc0-0")));

        List<WebElement> menuItems = driver.findElements(By.className("sc-a9073dc0-0"));

        assertEquals(17, menuItems.size(), "antalet länkar är inte korrekt");

    }

    @Test
    void checkAccessibilityText(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement accessibility = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a/span[2]"));
        String accessibilityTitle = accessibility.getText();


        assertEquals("Tillgänglighet i SVT Play", accessibilityTitle, "titeln stämmer inte");
    }
    @Test
    void checkAccessibilityLinkAndTitle(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement accessibilityLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/footer/div/div[5]/div[2]/p[1]/a"));

        accessibilityLink.click();

        WebElement accessibilityTitle = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/h1"));
        String accessibilityText = accessibilityTitle.getText();


        assertEquals("Så arbetar SVT med tillgänglighet", accessibilityText, "titeln stämmer inte");
    }

    @Test
    void tableauObjects() {

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement channelsLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]/a"));
        channelsLink.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-b3218c19-1")));

        List<WebElement> menuItems = driver.findElements(By.className("sc-b3218c19-1"));

        assertEquals(5, menuItems.size(), "antalet länkar är inte korrekt");;
    }

    @Test
    void checkTableauNames() {

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement channelsLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[3]/a"));
        channelsLink.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-b3218c19-1")));

        String tableauSvt1 = driver.findElement(By.xpath("//*[@id=\"panel-0\"]/div/section[1]/div/h2")).getText();
        String tableauSvt2 = driver.findElement(By.xpath("//*[@id=\"panel-0\"]/div/section[2]/div/h2")).getText();
        String tableauSvtBarn = driver.findElement(By.xpath("//*[@id=\"panel-0\"]/div/section[3]/div/h2")).getText();
        String tableaKunskapskanalen = driver.findElement(By.xpath("//*[@id=\"panel-0\"]/div/section[4]/div/h2")).getText();
        String tableauSvt24 = driver.findElement(By.xpath("//*[@id=\"panel-0\"]/div/section[5]/div/h2")).getText();


        assertEquals("Tablå för SVT 1", tableauSvt1, "incorrect channel");
        assertEquals("Tablå för SVT 2", tableauSvt2, "incorrect channel");
        assertEquals("Tablå för SVT Barn", tableauSvtBarn, "incorrect channel");
        assertEquals("Tablå för Kunskapskanalen", tableaKunskapskanalen, "incorrect channel");
        assertEquals("Tablå för SVT 24", tableauSvt24, "incorrect channel");
    }

    @Test
    void checkSignLanguageTitle(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement programsLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a"));
        programsLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/ul/li[2]/a")));

        String signLanguageText = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/ul/li[2]/a")).getText();

        assertEquals("Teckenspråk", signLanguageText, "Text wrong or missing");
    }

    @Test
    void ClickAndCheckSignLanguageLink(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement programsLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[2]/a"));

        programsLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/ul/li[2]/a")));

        WebElement signLanguageLink = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/div/section[1]/ul/li[2]/a"));
        signLanguageLink.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"play_main-content\"]/section")));
        String websiteTitle = driver.getTitle();

        assertEquals("Teckenspråkstolkade program och program på teckenspråk | SVT Play", websiteTitle, "Title do not match");

    }

    @Test
    void searchNewsCategory(){

        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]")));

        WebElement searchBar = driver.findElement(By.cssSelector("input[name='q']"));
        searchBar.click();

        driver.findElement(By.cssSelector("input[name='q']")).sendKeys("Nyheter");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/header/div[2]/div/div/nav/ul/li[5]/form/button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a")));

        driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/section/div/ul/li[1]/article/a")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("sc-6b1c5b48-1")));
        String ActualTitle = driver.findElement(By.xpath("//*[@id=\"play_main-content\"]/h1/span[2]")).getText();

        assertEquals("NYHETER", ActualTitle, "Text wrong or missing");
    }

    @AfterAll
    static void closedownchrome() {
        driver.quit();
    }
}
