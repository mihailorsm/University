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
    private static final double FI = Math.toRadians(30);

    public static void main(String[] args) {
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
           // System.out.println(temp_S + " " + temp_P);
          //  min_hight = 3000;
            for (double hight = 0; hight <=50000; hight+=10){
                tetta =Math.acos(Math.cos(ALFA)/(1+(hight/EARTH))) - ALFA;
            //    System.out.println(Math.cos(tetta));
            //    System.out.println(Math.cos(temp_mod * Math.PI));
           //     System.out.println(Math.cos(tetta)+Math.cos(temp_mod * Math.PI));
           //     System.out.println(2*Math.cos(1/2*(tetta + 2* Math.PI))*Math.cos(1/2*(tetta -  Math.PI)));
           //     System.out.println();
           //     System.out.println(i + " " + tetta*180/Math.PI);
                C1 = Math.acos(Math.cos(tetta)/Math.cos(Math.PI/temp_S));
                C2 = Math.acos(Math.cos(tetta)/Math.cos(2*Math.PI));
               // if((temp_P*(C1+C2))>=(2*Math.PI)){
                if (temp_P*(Math.asin(C1/Math.cos(FI))+Math.asin(C2/Math.cos(FI)))>=(2*Math.PI)){
                  //  System.out.println(tetta + " " + C2 + " " + hight);
                    row.createCell(3).setCellValue(Math.toDegrees(tetta));
                    row.createCell(4).setCellValue(C2);
                    row.createCell(5).setCellValue(hight);

                   // min_hight = hight;
                    break;
                }
            }

       //  tetta = Math.acos(EARTH/(EARTH + min_hight));
      //   C2 = Math.acos(Math.cos(tetta) / Math.cos(2 * temp_S / Math.PI));
      //   row.createCell(3).setCellValue(Math.toDegrees(tetta));
     //    row.createCell(4).setCellValue(C2);
     //    row.createCell(5).setCellValue(min_hight);

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
