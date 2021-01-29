package com.huawei.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private static Sheet sheet;


    /**
     * 加载excel文件，获取sheet页
     * @param filePath 路径
     * @param sheetName  sheet页名称
     */
    public static void loadExcel(String filePath,String sheetName){
        Workbook wb = null;
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(new File(filePath));
            String str = filePath.split("\\.x")[1];
            if ("ls".equals(str)) {
                wb = new HSSFWorkbook(fis);
            }else if ("lsx".equals(str)){

                wb = new XSSFWorkbook(fis);
            }else {
                LOGGER.error("输入格式错误！");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("空指针异常");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis !=null){
                try {
                    fis.close();
                } catch (IOException e1) {
                    LOGGER.error("输入流为空");
                    e1.printStackTrace();
                }
            }
        }
        if (wb != null)
            sheet = wb.getSheet(sheetName);
    }

    /**
     * 根据行、列得到单元格内容
     * @param row 行
     * @param column 列
     * @return 返回单元格内容
     */
    public static String getCellByIndex(int row,int column){
        return sheet.getRow(row).getCell(column).toString();
    }

    /**根据当前值获取同行的其他值
     *
     * @param value 当前值
     * @param currentColumn 当前列
     * @param targetColumn  目标列
     * @return 返回目标值
     */
    public static String getCellByValue(String value,int currentColumn,int targetColumn){
        int rows = sheet.getPhysicalNumberOfRows();
        String targetValue = null;
        for (int i = 0; i < rows; i++) {
            if (value.equals(sheet.getRow(i).getCell(currentColumn).toString())){
                targetValue = sheet.getRow(i).getCell(targetColumn).toString();
            }
        }
        return targetValue;
    }

}
