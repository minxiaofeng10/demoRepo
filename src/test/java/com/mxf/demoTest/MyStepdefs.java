package com.mxf.demoTest;

import cucumber.api.java.zh_cn.并且;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

/**
 * Created by pengchuan on 2019/2/24.
 */
public class MyStepdefs {

    protected WebDriver driver;
    @当("^我打开百度页面$")
    public void 我打开百度页面(){
        System.setProperty("webdriver.chrome.driver",demo01.class.getResource("/").getPath()+"/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.baidu.com");
    }

    @并且("^我输入\"([^\"]*)\"$")
    public void 我输入(String arg0){
        driver.findElement(By.id("kw")).sendKeys(arg0);
    }

    @当("^我点击百度一下$")
    public void 我点击百度一下() {
        driver.findElement(By.id("su")).click();
    }

    @那么("^我搜索\"([^\"]*)\"成功$")
    public void 我搜索成功(String arg0) throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(driver.getTitle(),arg0+"_百度搜索");
    }

    @那么("^我关闭页面$")
    public void 我关闭页面() throws InterruptedException {
    driver.quit();
    }
}
