package com.mxf.demoTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by pengchuan on 2019/2/18.
 */
public class demo01 {
    protected WebDriver driver;
    /***
     * 初始划浏览器
     */
    @BeforeTest
    public void start(){
//        System.setProperty("webdriver.chrome.driver","D:\\Worplace\\Ideaworkplace\\IdeaProjects\\demo01\\src\\test\\resources\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",demo01.class.getResource("/").getPath()+"/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        //全局等待
    }

    /***
     * 截图
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void openChrome() throws InterruptedException, IOException {
        driver.get("https://www.baidu.com/");
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("D:/shotScreen.png"));

    }

    /***
     * 操作iframe
     */
    @Test
    public void iframeTest(){
        driver.get("D:\\Worplace\\Ideaworkplace\\testResource\\index.html");
        //转交控制权
        //方法一：根据iframe节点的name或者id转交控制权
        //driver.switchTo().frame("testIframe");
        //方法二，根据对象转交
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        //操作iframe对象
        driver.findElement(By.id("iframeText")).sendKeys("iframe内容");

        //回交控制权
        driver.switchTo().defaultContent();
        driver.findElement(By.id("alertBtn")).click();

    }

    /***
     * 处理选择框
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void selectTest() throws InterruptedException, IOException {
        driver.get("D:\\Worplace\\Ideaworkplace\\testResource\\index.html");
        WebElement sl = driver.findElement(By.id("nameSelect"));
        //通过web元素实例化一个Select对象
        Select s = new Select(sl);
        s.selectByIndex(1);
        Thread.sleep(2000);
        s.selectByValue("liq");
        Thread.sleep(2000);
        s.selectByVisibleText("李兵");

    }

    /***
     * 多窗口的处理
     * eg:打开index页面
     * 点击打开新页面
     * 点击去百度
     */
    @Test
    public void windowTest(){
        driver.get("D:\\Worplace\\Ideaworkplace\\testResource\\index.html");
        Set<String> oldHandles = driver.getWindowHandles();
        driver.findElement(By.id("openDemoIframe")).click();
        
        //获取新页面的窗口句柄
        //遍历所有窗口
        Set<String> handles = driver.getWindowHandles();
        boolean b = handles.removeAll(oldHandles);
            for (String h:handles) {
                driver.switchTo().window(h);
            }
        driver.findElement(By.id("goBaiduBtn")).click();
    }
    /***
     *
     * @throws InterruptedException
     */
    @AfterTest
    public void end() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    //注册163的case
    @Test
    public void oneSixThreeTest() throws InterruptedException {
        driver.get("https://mail.163.com/");
        //点击邮箱账号登录
        driver.findElement(By.id("lbNormal")).click();

        //等待页面显示完全
        new WebDriverWait(driver,30).
                until(ExpectedConditions.elementToBeClickable(By.cssSelector("#loginDiv > iframe")));
        //转交控制权
        WebElement iframe = driver.findElement(By.cssSelector("#loginDiv > iframe"));
        driver.switchTo().frame(iframe);
        //获取点击注册前的窗口句柄
        Set<String> handles1 = driver.getWindowHandles();
        //点击注册
        driver.findElement(By.id("changepage")).click();

        //获取所有窗口
        Set<String> handles2 = driver.getWindowHandles();
        boolean registerWindowHanle = handles2.removeAll(handles1);
        for (String h:handles2) {
            //转交控制权到新窗口
            driver.switchTo().window(h);
        }
        long currentTimeMillis = System.currentTimeMillis();//获取时间戳，用于随机数
        driver.findElement(By.id("nameIpt")).sendKeys("3397446861@qq.com");//邮箱
        driver.findElement(By.id("mainPwdIpt")).sendKeys("123456");//密码
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("123456");//确认密码
        driver.findElement(By.id("mainMobileIpt")).sendKeys("15281700081");//手机号
        driver.findElement(By.id("vcodeIpt")).sendKeys("1234");//验证码
        driver.findElement(By.id("mainAcodeIpt")).sendKeys("5878");//短信验证码
        driver.findElement(By.id("mainRegA")).click();
    }

    /***
     *
     * @throws InterruptedException
     */
    @Test
    public void oneSixThreeLoginTest() throws InterruptedException {
        driver.get("https://mail.163.com/");
        //点击邮箱账号登录
        driver.findElement(By.id("lbNormal")).click();

        //等待页面显示完全
        new WebDriverWait(driver,30).
                until(ExpectedConditions.elementToBeClickable(By.cssSelector("#loginDiv > iframe")));
        //转交控制权
        WebElement iframe = driver.findElement(By.cssSelector("#loginDiv > iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.name("email")).sendKeys("meyoungtester");
        driver.findElement(By.name("password")).sendKeys("meyoung123");
        driver.findElement(By.id("dologin")).click();

    }

}
