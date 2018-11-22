import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;


public class Smile {

    public static void main(String[] args) {
      HSSFWorkbook wb = readWorkbook("src/main/smile.xls");
      HSSFSheet sheet = wb.getSheetAt(1);
      double str = 0;
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            HSSFRow row = (HSSFRow) rowIter.next();
            Iterator cellIter = row.cellIterator();
            while (cellIter.hasNext()) {
                HSSFCell cell = (HSSFCell) cellIter.next();
                str = cell.getNumericCellValue();
                System.out.println(str);
            }
        }

    }

    public static HSSFWorkbook readWorkbook(String filename) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            return wb;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeWorkbook(HSSFWorkbook wb, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e) {
            //Обработка ошибки
        }
    }
}
