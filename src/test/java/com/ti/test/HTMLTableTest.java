package com.ti.test;

import static com.ti.htmltable.ReadHtmlTable.performActionCell;
import static com.ti.htmltable.ReadHtmlTable.readTable;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HTMLTableTest {
  WebDriver driver;
  String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=sch-dashboard";
  String userName = "admin";
  String password = "G3-ySzY%";

  @BeforeTest
  void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get(baseURL);
    driver.manage().window().maximize();
  }

  @AfterTest
  void closeBrowser() {
    driver.quit();
  }

  @Test
  void readHTMLTableInfo() throws InterruptedException {
    WebElement txtLoginUserName = driver.findElement(By.id("user_login"));
    txtLoginUserName.clear();
    txtLoginUserName.sendKeys(userName);

    WebElement txtPassword = driver.findElement(By.name("pwd"));
    txtPassword.clear();
    txtPassword.sendKeys(password);

    WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log')]"));
    btnLogin.click();

    WebElement mnStudents = driver.findElement(
        By.xpath("(//span[contains(text(),'Students')])[1]"));
    mnStudents.click();

    WebElement tblStudents = driver.findElement(By.id("student_table"));

    readTable(tblStudents);

    //comparar un texto
    //compareTableText("Paul Smith Hawks","7229532243");

    //Realizar una acción
    //performActionCell("Action", "Roll No.", "2", "View");
    //Puedes especificar el index:
    //performActionCell("0", "Roll No.", "3", null);
    performActionCell("Action","Roll No.","2","Delete");
    Thread.sleep(5000);
  }

}
