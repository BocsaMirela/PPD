//package utils;
//import com.sun.media.sound.InvalidFormatException;
//
//import java.awt.*;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class ExcelWriter {
//
//    private static String[] columns = {"Lines", "Columns", "Matrice A", "Matrice B", "Matrice C", "Threads", "Timp","OBS"};
//    private FileInputStream inputStream;
//    private Workbook workbook;
//    private Sheet sheet ;
//    int rowCount;
//    String fileName;
//
//    public  ExcelWriter(String fileName) throws FileNotFoundException {
//        this.fileName=fileName;
//        inputStream =new FileInputStream(fileName);
//        if(Files.exists(Paths.get(fileName))) {
//            createHeader();
//        }
//        workbook = WorkbookFactory.create(inputStream);
//
//        sheet = workbook.getSheetAt(0);
//        rowCount = sheet.getLastRowNum();
//    }
//    public void createHeader(){
//        // Create a Workbook
//        // new HSSFWorkbook() for generating `.xls` file
//
//        /* CreationHelper helps us create instances of various things like DataFormat,
//           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
//        CreationHelper createHelper = workbook.getCreationHelper();
//
//        // Create a Sheet
//        Sheet sheet = workbook.createSheet("Test");
//
//        // Create a Font for styling header cells
//        Font headerFont = workbook.createFont();
//        headerFont.setBold(true);
//        headerFont.setFontHeightInPoints((short) 14);
//        headerFont.setColor(IndexedColors.RED.getIndex());
//
//        // Create a CellStyle with the font
//        CellStyle headerCellStyle = workbook.createCellStyle();
//        headerCellStyle.setFont(headerFont);
//
//        // Create a Row
//        Row headerRow = sheet.createRow(0);
//
//        // Create cells
//        for(int i = 0; i < columns.length; i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columns[i]);
//            cell.setCellStyle(headerCellStyle);
//        }
//    }
//
//    public void writeToExcel(final int lines, final int cols, final String values, final int threads, final int time, final String obs) throws IOException, InvalidFormatException {
//
//
//        Row row = sheet.createRow(++rowCount);
//
//        int columnCount = 0;
//
//        Cell cell = row.createCell(columnCount);
//        cell.setCellValue(rowCount);
//        columnCount++;
//        writeValue(row,lines,columnCount);
//        columnCount++;
//        writeValue(row,cols,columnCount);
//        columnCount++;
//        writeValue(row,values,columnCount);
//        columnCount++;
//        writeValue(row,threads,columnCount);
//        columnCount++;
//        writeValue(row,time,columnCount);
//        columnCount++;
//        writeValue(row,obs,columnCount);
//
//        // Resize all columns to fit the content size
//        for(int i = 0; i < columns.length; i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        inputStream.close();
//
//        FileOutputStream outputStream = new FileOutputStream(fileName);
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.close();
//
//
//
//
//    }
//    private void writeValue(Row row,Object field, final int columnCount){
//        Cell cell = row.createCell(columnCount);
//        if (field instanceof String) {
//            cell.setCellValue((String) field);
//        } else if (field instanceof Integer) {
//            cell.setCellValue((Integer) field);
//        }
//    }
//}