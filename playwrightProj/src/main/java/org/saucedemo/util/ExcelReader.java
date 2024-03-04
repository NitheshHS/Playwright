package org.saucedemo.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExcelReader {
    private String path;
    FileInputStream fileInputStream;
    XSSFWorkbook xssfWorkbook;
    public ExcelReader(String path) {
        this.path = path;
        try {
            fileInputStream=new FileInputStream(this.path);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, CellReference> getAllCellReference(String sheetName, List<String> columnName){
        Map<String, CellReference> cellRef=new HashMap<>();
        XSSFSheet sheet = xssfWorkbook.getSheet(sheetName);
        int totalRows = sheet.getLastRowNum();
        for(int i=0;i<totalRows;i++){
            int totalCell = sheet.getRow(i).getLastCellNum();
            for(int j=0;j<totalCell;j++){
                if(Objects.nonNull(sheet.getRow(i).getCell(j)) && sheet.getRow(i).getCell(j).getCellType() == CellType.STRING ){
                    if(sheet.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(columnName.get(i))){
                        cellRef.put(columnName.get(i), new CellReference(sheet.getRow(i).getCell(j)));
                    }
                }
            }
        }
        return cellRef;
    }

    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader("./src/test/resources/Inchcape Galaxy Calculator-copy.xlsx");
        Map<String, CellReference> map = excelReader.getAllCellReference("Inchcape", List.of("Old Selling Price inc. VAT", "Cost Price inc. VAT", "Capped or Financed?"));
        System.out.println(map);
    }
}
