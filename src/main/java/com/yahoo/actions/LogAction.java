package com.yahoo.actions;

import com.yahoo.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Listeners({ScreenshotListener.class})
public class LogAction extends BaseAction{

    private static LoginPage lp;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAction.class);
    public static void login(String username, String password){
        lp  = new LoginPage();
        LOGGER.info("输入账户、密码");
        lp.username.sendKeys(username);
        lp.password.sendKeys(password);
        LOGGER.info("点击确定");
        lp.submit.click();
    }

    /**
     * 退出登录
     */
    public static void logout(){
        lp.data_toggle.click();
        lp.logout.click();
        LOGGER.info("清空用户名、密码");
        lp.username.clear();
        lp.password.clear();
    }
}

