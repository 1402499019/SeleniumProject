package com.huawei.test;

import com.huawei.actions.BaseAction;
import com.huawei.actions.LogAction;
import com.huawei.utils.Contant;
import com.huawei.utils.ExcelUtil;
import com.huawei.utils.ScreenshotListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({ScreenshotListener.class})
public class LoginTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    LogAction la = new LogAction();
    @BeforeClass
    public void setUp(){
        LOGGER.info("输入网址");
        //加载页面
        BaseAction.load();
    }
    @AfterClass
    public void tearDown(){
        BaseAction.quit();
    }
    @Test(dataProvider = "data")
    public void login(String name,String passwd){
        LOGGER.info("登录：账户名{},\t密码{}",name,passwd);
        try{
            LogAction.login(name,passwd);
            Thread.sleep(1000);
            Assert.assertTrue(BaseAction.getDriver().getTitle().contains("我的地盘"));
        }catch (AssertionError a){
            LOGGER.error("登录失败！");
            Assert.fail("登录失败！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("退出登录");
        LogAction.logout();
    }
    @DataProvider(name = "data")
    public Object[][] testData(){
        ExcelUtil.getSheet(Contant.TEST_EXCEL_FILE_PATH,"测试数据");
        return ExcelUtil.getTestData();
    }
}
