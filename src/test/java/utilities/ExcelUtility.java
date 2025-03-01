package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    private String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new NullPointerException("Sheet '" + sheetName + "' not found.");
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new NullPointerException("Sheet '" + sheetName + "' not found.");
        XSSFRow row = sheet.getRow(rownum);
        if (row == null) return 0;  // Return 0 if row is empty
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new NullPointerException("Sheet '" + sheetName + "' not found.");
        XSSFRow row = sheet.getRow(rownum);
        if (row == null) return "";
        XSSFCell cell = row.getCell(colnum);
        if (cell == null) return "";
        
        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(cell);
        
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        XSSFWorkbook workbook;
        if (!xlfile.exists()) {
            workbook = new XSSFWorkbook();
            FileOutputStream fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        } else {
            FileInputStream fi = new FileInputStream(path);
            workbook = new XSSFWorkbook(fi);
            fi.close();
        }

        if (workbook.getSheetIndex(sheetName) == -1) workbook.createSheet(sheetName);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) sheet.createRow(rownum);
        XSSFRow row = sheet.getRow(rownum);

        XSSFCell cell = row.createCell(colnum);
        cell.setCellValue(data);

        FileOutputStream fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    public void fillCellColor(String sheetName, int rownum, int colnum, IndexedColors color) throws IOException {
        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new NullPointerException("Sheet '" + sheetName + "' not found.");

        XSSFRow row = sheet.getRow(rownum);
        if (row == null) row = sheet.createRow(rownum);

        XSSFCell cell = row.getCell(colnum);
        if (cell == null) cell = row.createCell(colnum);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fi.close();
        FileOutputStream fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fillCellColor(sheetName, rownum, colnum, IndexedColors.GREEN);
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fillCellColor(sheetName, rownum, colnum, IndexedColors.RED);
    }
}
