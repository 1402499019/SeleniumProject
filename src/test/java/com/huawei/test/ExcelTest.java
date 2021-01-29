package com.huawei.test;

import com.huawei.utils.ExcelUtil;
import org.testng.annotations.Test;

public class ExcelTest {
    @Test
    public void test(){
        ExcelUtil.loadExcel("C:\\Users\\zhang.sun\\Desktop\\关键字驱动.xls","Sheet1");
        System.out.println(ExcelUtil.getCellByIndex(0,2));
    }
}
