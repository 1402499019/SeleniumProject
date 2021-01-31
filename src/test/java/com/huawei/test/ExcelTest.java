package com.huawei.test;

import com.huawei.utils.Contant;
import com.huawei.utils.ExcelUtil;
import org.testng.annotations.Test;
import java.io.IOException;

public class ExcelTest {
    @Test
    public void test(){
        ExcelUtil.getSheet(Contant.TEST_EXCEL_FILE_PATH,"Sheet1");
        //System.out.println(ExcelUtil.getCellByIndex(0,2));
        try {
            //ExcelUtil.setCellData(0,5,"学校");
           // ExcelUtil.setColumnData(7,"haha");
            ExcelUtil.setRowData(12,"heihei");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
