/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.exceltest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Praveen
 */
public class Tag<T> {
    
    private String tagName;
    private String title;
    private List<T> lines=new ArrayList<>();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getLines() {
        return lines;
    }

    public void addLine(T line) {
        lines.add(line);
    }
    
    @Override
    public String toString() {
        return lines.toString(); 
    }
   
}
