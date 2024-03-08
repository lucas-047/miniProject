package com.library.Config;

import com.library.dao.BookRepository;
import com.library.entities.Book;
import com.library.entities.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class ExcelSheetConfig {
 @Autowired
 private static BookRepository bookRepository;

    public static boolean CheckFormat(MultipartFile file)
    {
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
    public static List<Book> convertExceltoList(InputStream is)
    {
        List<Book> list=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow=sheet.getRow(0);
//            XSSFSheet sheet = workbook.getSheet("BookDataset");
            int rowNumber=0;
            Iterator<Row> iterator=sheet.iterator();
            Iterator<Cell> headerCellIterator = headerRow.cellIterator();
            int column1Index=-1;
            int column2Index=-1;
            int column3Index=-1;
            int column4Index=-1;
            int column5Index=-1;
            int column6Index=-1;
            int column7Index=-1;
            int column8Index=-1;
            int column9Index=-1;


            while(iterator.hasNext()) {
                Cell headerCell = headerCellIterator.next();
                String headerValue = headerCell.getStringCellValue();
              //  Row row = iterator.next();
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
                }
                else if ("Version".equalsIgnoreCase(headerValue)) {
                    column8Index = headerCell.getColumnIndex();
                }
                else if ("BookStatus".equalsIgnoreCase(headerValue)) {
                    column9Index = headerCell.getColumnIndex();
                }
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                Book book1=new Book();

                    if (column1Index != -1) {
                        Cell cell = row.getCell(column1Index);
                       book1.setBookId((int)cell.getNumericCellValue());
                    }
                    if (column2Index != -1) {
                        Cell cell = row.getCell(column2Index);
                       book1.setCopyId((int)cell.getNumericCellValue());
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
                        book1.setBookStatus((int)cell.getNumericCellValue());
                    }
                    System.out.println(book1);
                //   bookRepository.save(book1);
                    list.add(book1);


//                    if (rowNumber == 0) {
//                        rowNumber++;
//                        continue;
//                    }
//                Iterator<Cell> cells=row.iterator();
//                int cid=0;
//                Book book=new Book();
//                while(cells.hasNext())
//                {
//                    Cell cell=cells.next();
//                    switch (cid)
//                    {
//                        case 1:
//                            book.setCopyId((int)cell.getNumericCellValue());
//                            break;
//                        case 0:
//                            book.setBookId((int)cell.getNumericCellValue());
//                            break;
//                        case 2:
//                            book.setBookName(cell.getStringCellValue());
//                            break;
//                        case 3:
//                            book.setAuthorName(cell.getStringCellValue());
//                            break;
//                        case 4:
//                            book.setPublisher(cell.getStringCellValue());
//                            break;
//                        case 5:
//                            book.setBranch(cell.getStringCellValue());
//                            break;
//                        case 6:
//                            book.setPublishDate((Date)cell.getDateCellValue() );
//                            break;
//                        case 7:
//                            book.setVersion(cell.getStringCellValue());
//                            break;
//                        case 8:
//                            book.setBookStatus((int)cell.getNumericCellValue());
//                            break;
//                        default:
//                            break;
//                    }
//                    cid++;
//
//                }
//                list.add(book);
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        bookRepository.saveAll(list);
        return list;
    }

    public static List<User> ConvertExceltoListofUser(InputStream is)
    {
        List<User> list=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
//            XSSFSheet sheet = workbook.getSheet("BookDataset");
            int rowNumber=0;
            Iterator<Row> iterator=sheet.iterator();
            while(iterator.hasNext())
            {
                Row row=iterator.next();
                if(rowNumber==0)
                {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells=row.iterator();
                int cid=0;
                User user=new User();
                while(cells.hasNext())
                {
                    Cell cell=cells.next();
                    switch (cid)
                    {
                        case 0:
                            user.setUserName(cell.getStringCellValue());

                            break;
                        case 1:
                            user.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            user.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            user.setBranch(cell.getStringCellValue());

                            break;
                        case 4:
                            user.setAddress(cell.getStringCellValue());

                            break;
                        case 5:
                            user.setMobileNumber(cell.getStringCellValue());

                            break;
                        case 6:
                            user.setEmail(cell.getStringCellValue());

                            break;
                        default:
                            break;
                    }
                    cid++;

                }
                list.add(user);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
