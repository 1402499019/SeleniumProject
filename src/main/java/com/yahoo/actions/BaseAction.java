package com.yahoo.actions;

import com.yahoo.utils.ScreenshotListener;
import org.openqa.selenium.Alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


/**
 * 用于封装：打开url、查找元素、关闭浏览器
 *继承LoadableComponent类，测试程序可以判断浏览器是否加载了正确的页面
 */
@Listeners({ScreenshotListener.class})
public class BaseAction {
   private static WebDriver driver;
   private static ResourceBundle bundle = ResourceBundle.getBundle("config");
   private static String title = "禅道";

    /**
     * 构造代码块，执行顺序：静态代码块，main（），构造代码块，构造方法
     * 构造代码块：对象初始化
     * 静态代码块：类初始化
     *
     * 初始化配置文件
     */
    public BaseAction() {
        PageFactory.initElements(driver,this);
        System.setProperty("webdriver.chrome.driver","E:\\Tools\\Driver\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    /**
     *重写load,打开浏览器
     */

    public static void load() {
        driver.get(bundle.getString("url"));
        driver.manage().window().maximize();
    }


    public static  void isLoaded() throws Error {
        //断言访问后的页面是否包含关键字
        Assert.assertTrue(driver.getTitle().contains(title));
    }

    public static WebDriver getDriver() {
        return driver;
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
     * 封装隐私等待，10s
     * 一旦寻找元素，找不到时，就一直找，直到找到或超时为止
     * 全局定义，只需要定义一次即可，一般放在BeforeClass()中；
     */
    public void implicitilyWait10Second(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * 关闭浏览器
     */
    public static void quit(){
        driver.quit();
    }
}
