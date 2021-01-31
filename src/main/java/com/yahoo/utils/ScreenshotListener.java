package com.yahoo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener extends TestListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotListener.class);
   
    @Override
    public void onTestFailure(ITestResult tr) {
       // super.onTestFailure(tr);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String dir_day_name = sdf.format(date);
        String file_time_name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date) + ".png";

        String path = "./screenshot/";
        //截图文件夹
        File dir = new File(path + dir_day_name + "/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        //截图--有弹框，不能完全截图
     //   File screenshot = ((TakesScreenshot)BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
        //使用Robot,可以截取全屏
        try {
            Thread.sleep(1000);
            BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(screenCapture, "png", new File(path + dir_day_name + "/" + file_time_name));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        //copy
       // File screenshot_copy = new File(path + dir_day_name + "/" + file_time_name);
//        try {
//            FileUtils.copyFile(screenshot,screenshot_copy);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
