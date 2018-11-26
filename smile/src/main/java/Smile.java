import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Smile {

    private static final double ALFA = 0.174533;
    private static final double EARTH = 6371;

    public static void main(String[] args) {
        double hight;
        double tetta;
        double C1;
        double C2;
        double min_hight;
        double temp_S;
        double temp_P;
      HSSFWorkbook wb = readWorkbook("src/main/smile.xls");
      HSSFSheet sheet = wb.getSheetAt(0);
        Iterator rowIter = sheet.rowIterator();
        rowIter.next();
        while (rowIter.hasNext()) {
            HSSFRow row = (HSSFRow) rowIter.next();
            HSSFCell P = row.getCell(1);
            HSSFCell S = row.getCell(2);
            temp_S = S.getNumericCellValue();
            temp_P = P.getNumericCellValue();
           // System.out.println(temp + " " + temp_P);
            min_hight = 10;
            for (int i = 10; i <=20000; i+=10){
                hight = EARTH+i;
                tetta = Math.acos(EARTH/hight);
            //    System.out.println(Math.cos(tetta));
            //    System.out.println(Math.cos(temp_mod * Math.PI));
           //     System.out.println(Math.cos(tetta)+Math.cos(temp_mod * Math.PI));
           //     System.out.println(2*Math.cos(1/2*(tetta + 2* Math.PI))*Math.cos(1/2*(tetta -  Math.PI)));
           //     System.out.println();
           //     System.out.println(i + " " + tetta*180/Math.PI);
                C1 = Math.acos(Math.cos(tetta)/Math.cos(Math.PI / temp_S));
                C2 = Math.acos(Math.cos(tetta)/Math.cos(2*Math.PI));
                if((temp_P*(C1+C2))>(2*Math.PI)){
                  //  System.out.println(tetta + " " + C2 + " " + hight);
                    min_hight = i;
                    break;
                }
            }

         tetta = Math.acos(EARTH/(EARTH + min_hight));
         C2 = Math.acos(Math.cos(tetta) / Math.cos(2 * temp_S / Math.PI));
         row.createCell(3).setCellValue(Math.toDegrees(tetta));
         row.createCell(4).setCellValue(C2);
         row.createCell(5).setCellValue(min_hight);

        }
        writeWorkbook(wb,"src/main/smile_final.xls");

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
            System.out.println("Не удалось записать файл"); //Обработка ошибки
        }
    }
}
