package com.huawei.pages;

import com.huawei.actions.BaseAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * 用来封装页面元素
 *
 */
public class LoginPage{

    @FindBy(xpath = "//input[@name='account']")
    public  WebElement username;

    @FindBy(xpath = "//input[@name = 'password']")
    public  WebElement password;

    @FindBy(xpath = "//button[@id = 'submit']")
    public  WebElement submit;

    //切换语言
    @FindBy(xpath = "//div[@id = 'langs']")
    public  WebElement language;

    //保持登录
    @FindBy(xpath = "//input[@name = 'keepLogin[]']")
    public  WebElement keepLogin;

    //忘记密码
    @FindBy(xpath = "//a[text() = '忘记密码']")
    public  WebElement forgetPasswd;

    //Logout
    @FindBy(xpath = "//a[@data-toggle = 'dropdown' and @class = 'dropdown-toggle']")
    public WebElement data_toggle;

    @FindBy(xpath = "//a[text() = '退出']")
    public WebElement logout;
    //使用findBy时，需要对页面进行初始化，如果不初始化，会空指针异常
    public LoginPage(){
      PageFactory.initElements(BaseAction.getDriver(),this);
    }

}
