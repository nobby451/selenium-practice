package com.github.marrontan619;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RedirectTester {

    public static void main(String[] args) {
        BufferedReader bufferedReader;
        HtmlUnitDriver driver = new HtmlUnitDriver();
        String line;
        try {
            // Read file like httpd.conf
            bufferedReader = new BufferedReader(new FileReader("./src/main/resources/sample.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                check(driver, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void check(HtmlUnitDriver driver, String line) {
        String[] configs = line.split(",");
        driver.get(configs[0]);
        System.out.print("From: " + configs[0] + " To: " + configs[1]);
        if (driver.getCurrentUrl().matches(configs[1])) {
            System.out.println(" OK");
        } else {
            System.err.println(" NG Current is " + driver.getCurrentUrl());
        }
    }

}
