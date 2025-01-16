package com.test.exceltest;

import com.test.exceltest.Scenario.Line;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DocumentBuilder {

    public static List<String> tags;

    static {
        tags = new ArrayList<>();
        tags.add("Feature");
        tags.add("Background");
        tags.add("Scenario");
        tags.add("Examples");
    }

    Document doc = new Document();

    public DocumentBuilder(File file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        Tag tag = null;
        while ((line = br.readLine()) != null) {
            line=line.trim();
            if(line.equalsIgnoreCase("")){
              continue;
            }
            int idx = line.indexOf(":");
            if (idx > 0 && tags.contains(line.substring(0, idx))) {
                tag = createTag(doc, line.substring(0, idx), line);
            } else {
                tag.addLine(line);
            }
        }
    }

    private Tag createTag(Document doc, String tagName, String line) {
        Tag tag = null;
        switch (tagName) {
            case "Feature":
                tag = new Feature();
                doc.feature = (Feature) tag;
                break;
            case "Background":
                tag = new Background();
                doc.background = (Background) tag;
                break;
            case "Scenario":
                tag = new Scenario();
                doc.scenarios.add((Scenario) tag);
                break;
            default:
                tag = new Tag();
                break;
        }
        tag.setTagName(tagName);
        tag.setTitle(line.replace(tagName+":", "").trim());
        return tag;
    }

    public Document getDoc() {
        return doc;
    }
    
    public void saveToExcel(File file) throws IOException, InvalidFormatException{
       Workbook workbook = WorkbookFactory.create(file);
       Sheet sheet = workbook.getSheetAt(0);
       int rowIndex=sheet.getPhysicalNumberOfRows();
        System.out.println("Existing Rows:"+rowIndex);
        for (Scenario scenario : doc.scenarios) {
            for (Object lineObj : scenario.getLines()) {
                Scenario.Line line=(Scenario.Line)lineObj;
                Row row = sheet.createRow(++rowIndex);
                row.createCell(0).setCellValue(doc.feature.getTitle());
                row.createCell(1).setCellValue("File Name");
                row.createCell(2).setCellValue(doc.feature.toString());
                row.createCell(3).setCellValue(doc.background.toString());
                row.createCell(5).setCellValue("step");
                row.createCell(6).setCellValue(line.isGiven()? "step" : "vp");
                row.createCell(7).setCellValue(line.toString());
            }
        }
       File temp=File.createTempFile("myexcel",".xlsx");
       FileOutputStream fos =new FileOutputStream(temp);
       workbook.write(fos);
       fos.close();
       workbook.close();
       temp.renameTo(file);
    }

}
