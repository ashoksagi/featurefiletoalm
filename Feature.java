package com.test.exceltest;

public class Feature extends Tag<String>{

    @Override
    public String toString() {
        String content="";
        for (String line : getLines()) {
            content+=line+"\n";
        }
        return content;
    }    
}
