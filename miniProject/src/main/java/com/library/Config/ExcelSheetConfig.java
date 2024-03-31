package com.library.Config;

import com.library.entities.Book;
import com.library.entities.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Service
public class ExcelSheetConfig {
    public static boolean CheckFormat(MultipartFile file)
    {
        System.out.println("hello moto");
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


public static List<Book> importDataOfBookFromExcel(MultipartFile multipartFile) throws IOException {
    List<Book> list=new ArrayList<>();
    XSSFWorkbook workbook = null;
    try {
        File tempFile = File.createTempFile("temp", null);
        multipartFile.transferTo(tempFile);
        FileInputStream inputStream = new FileInputStream(tempFile);
        workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        Iterator<Cell> headerCellIterator = headerRow.cellIterator();
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
            if ("CopyNo".equalsIgnoreCase(headerValue)) {
                column1Index = headerCell.getColumnIndex();
            } else if ("BookName".equalsIgnoreCase(headerValue)) {
                column2Index = headerCell.getColumnIndex();
            } else if ("AuthorName".equalsIgnoreCase(headerValue)) {
                column3Index = headerCell.getColumnIndex();
            } else if ("Publisher".equalsIgnoreCase(headerValue)) {
                column4Index = headerCell.getColumnIndex();
            } else if ("Branch".equalsIgnoreCase(headerValue)) {
                column5Index = headerCell.getColumnIndex();
            } else if ("PublishDate".equalsIgnoreCase(headerValue)) {
                column6Index = headerCell.getColumnIndex();
            } else if ("Version".equalsIgnoreCase(headerValue)) {
                column7Index = headerCell.getColumnIndex();
            } else if ("BookStatus".equalsIgnoreCase(headerValue)) {
                column8Index = headerCell.getColumnIndex();
            }
            // Add more if needed
        }

        // Iterate over rows skipping the header row
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            Book book1 = new Book();
//
//            if (column1Index != -1) {
//                Cell cell = row.getCell(column1Index);
//                book1.setBookId((int) cell.getNumericCellValue());
//            }
            if (column1Index != -1) {
                Cell cell = row.getCell(column1Index);
                int i=cell.getColumnIndex();
                System.out.println("column is +"+i);
                book1.setCopyId((int)cell.getNumericCellValue()) ;
            }
            if (column2Index != -1) {
                Cell cell = row.getCell(column2Index);
                book1.setBookName((cell.getStringCellValue()));
            }
            if (column3Index != -1) {
                Cell cell = row.getCell(column3Index);
                book1.setAuthorName(cell.getStringCellValue());
            }
            if (column4Index != -1) {
                Cell cell = row.getCell(column4Index);
                book1.setPublisher(cell.getStringCellValue());
            }
            if (column5Index != -1) {
                Cell cell = row.getCell(column5Index);
                book1.setBranch(cell.getStringCellValue());
            }
            if (column6Index != -1) {
                Cell cell = row.getCell(column6Index);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    // Assuming the date is in the format "MM-DD-YYYY"
                    String dateString = cell.getStringCellValue();
                    String[] parts = dateString.split("-");
                    int month = Integer.parseInt(parts[0]);
                    int day = Integer.parseInt(parts[1]);
                    int year = Integer.parseInt(parts[2]);

                    // Create LocalDate object
                    LocalDate date = LocalDate.of(year, month, day);
                book1.setPublishDate(date);
                }
//                    book1.setPublishDate(cell.getDateCellValue().toInstant()
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDate());
            }
            if (column7Index != -1) {
                Cell cell = row.getCell(column7Index);
                book1.setVersion(cell.getStringCellValue());
            }
            if (column8Index != -1) {
                Cell cell = row.getCell(column8Index);
                book1.setBookStatus((int) cell.getNumericCellValue());
            }
            // Add more if needed
            System.out.println(book1);
            list.add(book1);

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
        System.out.println("workbook closed");
    }
    return list;
}


    public static List<User> importDataOfUserFromExcel(MultipartFile multipartFile) throws IOException {
        List<User> listUser=new ArrayList<>();
        XSSFWorkbook workbookUser = null;
        try {
            File tempFile = File.createTempFile("temp", null);
            multipartFile.transferTo(tempFile);
            FileInputStream inputStream = new FileInputStream(tempFile);
            workbookUser = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbookUser.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Iterator<Cell> headerCellIterator = headerRow.cellIterator();
            int column1Index = -1;
            int column2Index = -1;
            int column3Index = -1;
            int column4Index = -1;
            int column5Index = -1;
            int column6Index = -1;
            int column7Index = -1;
//            int column8Index = -1;
//            int column9Index = -1;
//            int column10Index=-1;
            // Add more if needed

            while (headerCellIterator.hasNext()) {
                Cell headerCell = headerCellIterator.next();
                String headerValue = headerCell.getStringCellValue();

                // Map column indexes based on column headers
                if ("userid".equalsIgnoreCase(headerValue)) {
                    column1Index = headerCell.getColumnIndex();
                } else if ("firstname".equalsIgnoreCase(headerValue)) {
                    column2Index = headerCell.getColumnIndex();
                } else if ("lastname".equalsIgnoreCase(headerValue)) {
                    column3Index = headerCell.getColumnIndex();
                } else if ("branch".equalsIgnoreCase(headerValue)) {
                    column4Index = headerCell.getColumnIndex();
                } else if ("address".equalsIgnoreCase(headerValue)) {
                    column5Index = headerCell.getColumnIndex();
                } else if ("mobilenumber".equalsIgnoreCase(headerValue)) {
                    column6Index = headerCell.getColumnIndex();
                } else if ("email".equalsIgnoreCase(headerValue)) {
                    column7Index = headerCell.getColumnIndex();
                }
//                else if ("role".equalsIgnoreCase(headerValue)) {
//                    column8Index = headerCell.getColumnIndex();
//                } else if ("password".equalsIgnoreCase(headerValue)) {
//                    column9Index = headerCell.getColumnIndex();
//                } else if ("issuedBook".equalsIgnoreCase(headerValue)) {
//                    column10Index=headerCell.getColumnIndex();
//                }
                // Add more if needed
            }

            // Iterate over rows skipping the header row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
             User user=new User();
                if (column1Index != -1) {
                    Cell cell = row.getCell(column1Index);
                    user.setUserName(String.valueOf(cell.getNumericCellValue()));
                }
                if (column2Index != -1) {
                    Cell cell = row.getCell(column2Index);
                   user.setFirstName(cell.getStringCellValue());
                }
                if (column3Index != -1) {
                    Cell cell = row.getCell(column3Index);
                   user.setLastName(cell.getStringCellValue());
                }
                if (column4Index != -1) {
                    Cell cell = row.getCell(column4Index);
                   user.setBranch(cell.getStringCellValue());
                }
                if (column5Index != -1) {
                    Cell cell = row.getCell(column5Index);
                   user.setAddress(cell.getStringCellValue());
                }
                if (column6Index != -1) {
                    Cell cell = row.getCell(column6Index);
                    String mobileno= Long.toString((long) cell.getNumericCellValue());
                    user.setMobileNumber(mobileno);
                }
                if (column7Index != -1) {
                    Cell cell = row.getCell(column7Index);
                    user.setEmail(cell.getStringCellValue());
                }
//                if (column8Index != -1) {
//                    Cell cell = row.getCell(column8Index);
//                    user.setRole(cell.getStringCellValue());
//                }
//                if (column9Index != -1) {
//                    Cell cell = row.getCell(column9Index);
//                        user.setPassword(cell.getStringCellValue());
//                }
//                if(column10Index!=-1)
//                {
//                    Cell cell=row.getCell(column10Index);
//                    user.setIssuedBook((int)cell.getNumericCellValue());
//                }
                // Add more if needed
                System.out.println(user);
                listUser.add(user);

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

            workbookUser.close();
            //workbook=null;
            System.out.println("workbook closed");
        }



        return listUser;
    }

}
