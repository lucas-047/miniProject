package com.library.Config;

import com.library.entities.Book;
import com.library.entities.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelSheetConfig {
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
                Book book=new Book();
                while(cells.hasNext())
                {
                    Cell cell=cells.next();
                    switch (cid)
                    {
                        case 1:
                            book.setCopyId((int)cell.getNumericCellValue());
                            break;
                        case 0:
                            book.setBookId((int)cell.getNumericCellValue());
                            break;
                        case 2:
                            book.setBookName(cell.getStringCellValue());
                            break;
                        case 3:
                            book.setAuthorName(cell.getStringCellValue());
                            break;
                        case 4:
                            book.setPublisher(cell.getStringCellValue());
                            break;
                        case 5:
                            book.setBranch(cell.getStringCellValue());
                            break;
                        case 6:
                            book.setPublishDate((Date)cell.getDateCellValue() );
                            break;
                        case 7:
                            book.setVersion(cell.getStringCellValue());
                            break;
                        case 8:
                            book.setBookStatus((int)cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }
                list.add(book);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
