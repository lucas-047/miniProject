package com.library.Config;


import com.library.dao.BookRepository;
import com.library.entities.Book;
import com.library.entities.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelSheetService {
    @Autowired
    private BookRepository bookRepository;
//    public void save(MultipartFile file)
//    {
//        try {
//            List<Book> book = ExcelSheetConfig.convertExceltoList(file.getInputStream());
//            this.bookRepository.saveAll(book);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public List<Book> getALLBook()
//    {
//    return this.bookRepository.findAll();
//
//    }
//
//    public void saveUser(MultipartFile file){
//        try{
//            List<User> user = ExcelSheetConfig.ConvertExceltoListofUser(file.getInputStream());
//        }catch (IOException error){
//            System.out.println(error);
//        }
//    }
 // Your Spring Data JPA repository

    public void importDataFromExcel(MultipartFile multipartFile) throws IOException {
       // FileInputStream fileInputStream = new FileInputStream((File) excelFile);
    //Object object=multipartFile.getInputStream();
        XSSFWorkbook workbook = null;
        try {


            File tempFile = File.createTempFile("temp", null);
            //file.transferTo(tempFile);
            multipartFile.transferTo(tempFile);

            FileInputStream inputStream = new FileInputStream(tempFile);
            workbook = new XSSFWorkbook(inputStream);
            int i = workbook.getActiveSheetIndex();
            System.out.println("index is " + i);
            int j = workbook.getNumberOfSheets();
            System.out.println("number of sheet is " + j);
            XSSFSheet sheet = workbook.getSheetAt(0);
            // Workbook workbook = new XSSFWorkbook(fileInputStream);

            //  Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            //Sheet sheet= workbook.getSheet("BookDataset-extra");
            //Sheet
            // Get headers from the first row
            Row headerRow = sheet.getRow(0);
            Iterator<Cell> headerCellIterator = headerRow.cellIterator();

            // Store column index mappings based on column headers
            int column1Index = -1;
            int column2Index = -1;
            int column3Index = -1;
            int column4Index = -1;
            int column5Index = -1;
            int column6Index = -1;
            int column7Index = -1;
            int column8Index = -1;
            int column9Index = -1;
            // Add more if needed

            while (headerCellIterator.hasNext()) {
                Cell headerCell = headerCellIterator.next();
                String headerValue = headerCell.getStringCellValue();

                // Map column indexes based on column headers
                if ("BookId".equalsIgnoreCase(headerValue)) {
                    column1Index = headerCell.getColumnIndex();
                } else if ("CopyNo".equalsIgnoreCase(headerValue)) {
                    column2Index = headerCell.getColumnIndex();
                } else if ("BookName".equalsIgnoreCase(headerValue)) {
                    column3Index = headerCell.getColumnIndex();
                } else if ("AuthorName".equalsIgnoreCase(headerValue)) {
                    column4Index = headerCell.getColumnIndex();
                } else if ("Publisher".equalsIgnoreCase(headerValue)) {
                    column5Index = headerCell.getColumnIndex();
                } else if ("Branch".equalsIgnoreCase(headerValue)) {
                    column6Index = headerCell.getColumnIndex();
                } else if ("PublishDate".equalsIgnoreCase(headerValue)) {
                    column7Index = headerCell.getColumnIndex();
                } else if ("Version".equalsIgnoreCase(headerValue)) {
                    column8Index = headerCell.getColumnIndex();
                } else if ("BookStatus".equalsIgnoreCase(headerValue)) {
                    column9Index = headerCell.getColumnIndex();
                }
                // Add more if needed
            }

            // Iterate over rows skipping the header row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Book book1 = new Book();
                if (column1Index != -1) {
                    Cell cell = row.getCell(column1Index);
                    book1.setBookId((int) cell.getNumericCellValue());
                }
                if (column2Index != -1) {
                    Cell cell = row.getCell(column2Index);
                    book1.setCopyId((int) cell.getNumericCellValue());
                }
                if (column3Index != -1) {
                    Cell cell = row.getCell(column3Index);
                    book1.setBookName((cell.getStringCellValue()));
                }
                if (column4Index != -1) {
                    Cell cell = row.getCell(column4Index);
                    book1.setAuthorName(cell.getStringCellValue());
                }
                if (column5Index != -1) {
                    Cell cell = row.getCell(column5Index);
                    book1.setPublisher(cell.getStringCellValue());
                }
                if (column6Index != -1) {
                    Cell cell = row.getCell(column6Index);
                    book1.setBranch(cell.getStringCellValue());
                }
                if (column7Index != -1) {
                    Cell cell = row.getCell(column7Index);
                    book1.setPublishDate(cell.getDateCellValue());
                }
                if (column8Index != -1) {
                    Cell cell = row.getCell(column8Index);
                    book1.setVersion(cell.getStringCellValue());
                }
                if (column9Index != -1) {
                    Cell cell = row.getCell(column9Index);
                    book1.setBookStatus((int) cell.getNumericCellValue());
                }
                // Add more if needed
                System.out.println(book1);
                bookRepository.saveAndFlush(book1); // Save entity to the database

            }
            if (tempFile.exists()) {
                //Files.delete(tempFile.toPath());
                tempFile.delete();
            }
            inputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {

            workbook.close();
            //workbook=null;
        }
//            if ( workbook!= null) {
//                try {
//                    workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    workbook = null; // Set workbook to null
//                }
//            }

        System.out.println("workbook closed");


//        fileInputStream.close();

    }

}


