package com.github.marrontan619;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LTicketWatcher {

    public static final int[] STAGES_PER_CITY = {4, 4, 2, 2, 3, 3, 2, 2, 2, 2, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2};
    public static final int STAGES = 20,
                               BUTTONS_PER_PAGE = 10;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ResourceBundle.getBundle("config").getString(("webdriver.chrome.driver")));
        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new HtmlUnitDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        canBuyTicket(driver);
//        driver.close();

    }

    private static boolean canBuyTicket(WebDriver driver) {
        for (int stageIndex = 0; stageIndex < STAGES; stageIndex++) {
            int buttonIndex = stageIndex - BUTTONS_PER_PAGE < 0 ? stageIndex : stageIndex - BUTTONS_PER_PAGE;
            for (int scheduleIndex = 0; scheduleIndex < STAGES_PER_CITY[stageIndex]; scheduleIndex++) {
                // 検索結果
                driver.get("http://l-tike.com/lovelive-lt/");

                // 2ページ目に行く場合
                if (stageIndex >= BUTTONS_PER_PAGE) {
                    driver.findElement(By.xpath("//div[@class=\"carrier\"]//a")).click();
                }
                // 公演の検索結果から、詳細へ
                driver.findElements(By.xpath("//table[@class=\"formInput\"]//img[@src=\"http://img.l-tike.com/content/img/shared/button/white/orange/detail.gif\"]"))
                                    .get(buttonIndex)
                                    .click();
                // カレンダーから、特定日時の公演へ
                List<WebElement> links = driver.findElements(By.xpath("//div[@class=\"wrapper\"]//a"));
                links.get(scheduleIndex).click();

                // 予定枚数終了になっているか

                if (driver.findElements(By.className("disable")).size() == 0) {
                    List<WebElement> performanceInformation = driver.findElements(By.cssSelector(".performanceInformation, .plain"));
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("alert(\""
                                   + performanceInformation.get(0).getText() + " "
                                   + performanceInformation.get(1).getText() + " "
                                   + performanceInformation.get(2).getText() + " "
                                   + performanceInformation.get(3).getText() + " "
                                   + "復活！？"
                                   + "\")");
                    return true;
                } else {
                    List<WebElement> performanceInformation = driver.findElements(By.className("plain"));
                    System.out.println(
                            performanceInformation.get(0).getText() + " "
                          + performanceInformation.get(1).getText() + " "
                          + performanceInformation.get(2).getText() + " "
                          + "望みなし"
                            );
                }
            }
            if (stageIndex == 19) {
                stageIndex = 0;
            }
        }
        return false;

    }

}
