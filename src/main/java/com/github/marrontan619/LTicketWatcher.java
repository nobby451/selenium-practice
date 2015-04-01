package com.github.marrontan619;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LTicketWatcher {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
//        WebDriver driver = new HtmlUnitDriver();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(canBuyTicket(driver));
        driver.close();

    }

    private static boolean canBuyTicket(WebDriver driver) {
        // トップページ
        driver.get("http://l-tike.com/");
        driver.findElement(By.id("searchText")).sendKeys("ラブライブ");
        driver.findElement(By.id("searchButton")).click();

        // 公演の検索結果から、詳細へ
        driver.findElement(By.className("formInput")).findElement(By.tagName("img")).click();

        // カレンダーから、特定日時の公演へ
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        WebElement calenderTable = tables.get(3);
        List<WebElement> links = calenderTable.findElements(By.tagName("li"));
        links.get(1).click();

        // 予定枚数終了になっているか
        if (driver.findElements(By.cssSelector(".send")).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

}
