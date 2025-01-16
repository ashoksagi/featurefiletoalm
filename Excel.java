/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import com.test.exceltest.DocumentBuilder;
import java.io.File;

/**
 *
 * @author Praveen
 */
public class Excel {
    public static void main(String[] args) {
        try {
            File input=new File("D:/doc.txt");
            DocumentBuilder doc=new DocumentBuilder(input);
            System.out.println("Document Prepared:");
            System.out.println(doc.getDoc().feature);
            doc.saveToExcel(new File("D:/praveen.xlsx"));
            System.out.println("Wrote to excel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
