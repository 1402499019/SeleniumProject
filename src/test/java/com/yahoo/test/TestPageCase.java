package com.yahoo.test;

import com.yahoo.pages.TestPage;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPageCase {
    TestPage tp;
    @BeforeClass
    public void BeforeClass(){
        tp = new TestPage();
        tp.implicitilyWait10Second();

    }
    /**
     * 添加区块
     */
    @Test(enabled = false)
    public void addBlock(){
        tp.testButton.click();
        tp.add_block.click();
        new Select(tp.block_select).selectByValue("测试统计");
        tp.block_name.sendKeys("111");
        new Select(tp.block_locate).selectByValue("右侧");
        new Select(tp.block_type).selectByValue("全部");
        new Select(tp.block_orderby).selectByValue("状态正序");
        tp.block_save.click();
    }
}
