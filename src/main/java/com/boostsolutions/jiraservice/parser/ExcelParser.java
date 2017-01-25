package com.boostsolutions.jiraservice.parser;

import com.boostsolutions.jiraservice.model.DataModel;
import com.boostsolutions.jiraservice.model.PreparedContent;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author viholovko on 11/7/2016.
 */
public class ExcelParser {

    private static HSSFWorkbook workBook = null;

    public static PreparedContent parse(String fileName) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workBook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        String aray = "id/";
        List<DataModel> dataModels = new ArrayList<DataModel>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            DataModel dataModel = new DataModel();

            String dataContent = "";

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
            System.out.println();
            if (i != 0) {
                dataModel.setData(dataContent.split("/"));
                dataModels.add(dataModel);
            }
        }
        System.out.println("Start to prepare list of users");
        System.out.println("Parse operation completed");


        return new PreparedContent(aray.split("/"), dataModels);
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