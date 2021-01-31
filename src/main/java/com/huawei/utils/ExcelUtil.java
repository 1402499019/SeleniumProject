package com.huawei.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ResourceBundle;

public class ExcelUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");
    private static Workbook wb = null;
    private static Sheet sheet;
    /**
     * 加载excel文件，获取sheet页
     * @param sheetName  sheet页名称
     */
    public static Sheet getSheet(String filePath,String sheetName){
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
        return sheet;
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

    /**
     * 获取的是物理行数，（实际数据行）不包括那些空行（隔行）的情况。
     * @return 行数
     */
    public static int getPhysicalNumberOfRows(){
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * @return 返回最后一行的行号
     */
    public static int getLastRowNum(){
        return sheet.getLastRowNum();
    }

    /**
     * getLastColumnNum返回的是实际列数，我们将其换算为0 ->（列数-1）
     * @return
     */
    public static int getLastColumnNum(){
        return sheet.getRow(0).getLastCellNum() -1;
    }

    /**
     * 通过列头值，获取该列号
     * @param value
     * @return
     */
    public static int getColumnByHeaderValue(String value){
        int column = 0;
        Cell cell;
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            cell = sheet.getRow(0).getCell(i);
            if (cell == null){
                cell = sheet.getRow(0).createCell(i);
            }
           if((cell.toString()).equals(value)) {
               column = i;
               break;
           }
        }
         return column;
    }
    /**
     * 根据当前值获取同行的其他值
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
    public static Object[][] getTestData(){

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        System.out.println(rowCount);
        int nameColumn = ExcelUtil.getColumnByHeaderValue("账户");
        int passwdColumn = ExcelUtil.getColumnByHeaderValue("密码");
   //     System.out.println(nameColumn + " = " + passwdColumn);
        Object[][] results = new Object[rowCount][2];
        /**
         * 因为第一行是列头，所以要从1开始遍历
         */
        for (int i = 1; i <= rowCount; i++) {
            Cell cell = sheet.getRow(i).getCell(nameColumn);
            Cell cell2 = sheet.getRow(i).getCell(passwdColumn);
            if (cell == null)
                cell = sheet.getRow(i).createCell(nameColumn);
            if (cell2 == null)
                cell2 = sheet.getRow(i).createCell(passwdColumn);
            results[i-1][0] = cell.toString();
            results[i-1][1] =  cell2.toString();
        }
        return results;

    }

    /**
     * 向单元格输入值
     * @param row 行
     * @param column 列
     * @param value 输入值
     */
    public static void setCellData(int row,int column,String value) throws IOException {
        Row row1 = sheet.getRow(row);
        if (row1 == null){
            row1 = sheet.createRow(row);
        }
        Cell cell= row1.getCell(column);
        //如果cell为空，无法直接调用setCellValue，因此需要创建单元格
        if (cell == null){
            cell = row1.createCell(column);
        }
        cell.setCellValue(value);
        //写入Excel
        FileOutputStream fos = new FileOutputStream(Contant.TEST_EXCEL_FILE_PATH);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    /**
     * 按照列头，为整行赋值
     * @param row 行
     * @param value 值
     */
    public static void setRowData(int row , String value) throws IOException {
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            setCellData(row,i,value);
        }
    }
    /**
     * 为整列赋值
     * @param column 列
     * @param value 值
     */
    public static void setColumnData(int column,String value) throws IOException {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                setCellData(i,column,value);
        }
    }

}
