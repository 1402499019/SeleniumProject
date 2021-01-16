package com.huawei.test;

import com.huawei.pages.BasePage;
import com.huawei.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginCase {
    static LoginPage lp;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCase.class);
    @BeforeClass
    public void BeforeClass(){
        lp  = new LoginPage();
        //隐式等待:一直找这个元素，直到找打或超时为止，全局
        lp.implicitilyWait30Second();
    }

    @AfterClass
    public void AfterClass(){
        //lp.quit();
    }
    @Test(dataProvider = "LoginData",priority = 1)
    public static void loginTest(String username,String password){
        LOGGER.info("-->输入网址<--");
        lp.open();
        LOGGER.info("-->开始登录<--");
        lp.username.sendKeys(username);
        lp.password.sendKeys(password);
        lp.submit.click();
        lp.waitUtil30S().until(ExpectedConditions.titleContains("我的地盘"));
        Assert.assertTrue(lp.getDriver().getTitle().contains("我的地盘"),"登录失败，请检查账户或密码！");
        LOGGER.info("-->结束登录<--");
    }
    @Test
    public void logoutTest(){
        //退出
        lp.data_toggle.click();
        lp.logout.click();

        //清空
        lp.username.clear();
        lp.password.clear();
    }

    @DataProvider
    public Object[][] LoginData(){
        return new Object[][]{
                {"zhangsan","zhang.1595159"}//,{"lisi","zhang.1595159"},{"wangwu","zhang.1595159"}
        };
    }

}

