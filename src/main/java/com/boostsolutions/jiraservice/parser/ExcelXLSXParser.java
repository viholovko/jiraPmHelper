package com.boostsolutions.jiraservice.parser;

import com.boostsolutions.jiraservice.model.DataModel;
import com.boostsolutions.jiraservice.model.PreparedContent;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author viholovko on 11/7/2016.
 */
public class ExcelXLSXParser {

    public static PreparedContent parse(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = mySheet.iterator();

            String aray = "id/";
            List<DataModel> dataModels = new ArrayList<DataModel>();
            for (int i = 0; i < mySheet.getLastRowNum(); i++) {
                DataModel dataModel = new DataModel();
                String dataContent = "";
                if (rowIterator.hasNext()) {
                    try {
                        Row row = rowIterator.next();
                        Iterator<Cell> cellIterator = row.cellIterator();

                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            if (cell != null) {
                                if (i == 0 && !cell.getStringCellValue().contains("id")) {
                                    aray += cell.getStringCellValue() + "/";
                                } else if (i != 0) {
                                    dataContent += getAutoConvertCellData(cell) + "/";
                                }
                            }
                        }
                    } catch (NoSuchElementException ex){
                        ex.printStackTrace();
                    }
                    System.out.println();
                    if (i != 0 && dataContent!=null && dataContent.length()>2) {
                        dataModel.setData(dataContent.split("/"));
                        dataModels.add(dataModel);
                    }
                }
            }
            System.out.println("Start to prepare list of users");
            System.out.println("Parse operation completed");

            return new PreparedContent(aray.split("/"), dataModels);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new PreparedContent();
        } catch (IOException e) {
            e.printStackTrace();
            return new PreparedContent();
        }
    }


    private static String getAutoConvertCellData(Cell cell) {
        String value = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                System.out.print(cell.getBooleanCellValue() + "\t\t");
                value = Boolean.toString(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                System.out.print(cell.getNumericCellValue() + "\t\t");
                value = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                System.out.print(cell.getStringCellValue() + "\t\t");
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getNumericCellValue());
                int position = value.indexOf(".");
                if (value.length() > position + 3) {
                    value = value.substring(0, position + 3);
                }
                System.out.print(value);
                break;
        }
        return value;
    }
}