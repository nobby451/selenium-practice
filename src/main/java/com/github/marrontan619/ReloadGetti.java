package com.github.marrontan619;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReloadGetti {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.e-get.jp/bwt-fc/pt/");
        WebElement idElement = driver.findElement(By.name("id"));
        idElement.sendKeys("id");
        WebElement passElement = driver.findElement(By.name("pass"));
        passElement.sendKeys("pass");
        WebElement btnElement = driver.findElement(By.cssSelector("#btn_login > a"));
        btnElement.click();

        while (true) {
            Thread.sleep(5000);
            driver.navigate().refresh();
            driver.switchTo().alert().accept();
        }
    }
}
