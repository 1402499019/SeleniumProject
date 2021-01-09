package com.huawei.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


/**
 * 用于封装：打开url、查找元素、关闭浏览器
 *
 */
public class BasePage {
   private static WebDriver driver;
   private static String url ;
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

    /**
     * 构造方法
     */
    public BasePage() {

    }

    public WebDriver getDriver() {
        return driver;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    static{
        System.setProperty("webdriver.chrome.driver","E:\\Tools\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        url = bundle.getString("url");
    }
    /**
     * 构造代码块，执行顺序：静态代码块，main（），构造代码块，构造方法
     * 构造代码块：对象初始化
     * 静态代码块：类初始化
     *
     * 初始化配置文件
     */


    /**
     *打开浏览器
     */
    public void open(){
        driver.get(url);
        driver.manage().window().maximize();
    }

    /**
     * 警告框
     * @return 返回alert
     */
    public Alert switchToAlert(){
       return driver.switchTo().alert();
    }
    /**
     *
     */
    public void switchTo(){
        driver.switchTo();
    }
    /**
     * 封装显示等待,统一30s
     */
    public WebDriverWait waitUtil30S(){
        return new WebDriverWait(driver,30);
    }
    /**
     * 封装隐私等待，30s
     * 一旦寻找元素，找不到时，就一直找，直到找到或超时为止
     * 全局定义，只需要定义一次即可，一般放在BeforeClass()中；
     */
    public void implicitilyWait30Second(){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * 关闭浏览器
     */
    public void quit(){
        driver.quit();
    }
}
