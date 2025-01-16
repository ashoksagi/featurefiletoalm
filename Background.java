package com.test.exceltest;

import java.util.List;

public class Background extends Tag<String> {
	public String given;
	public List<String> and;

    @Override
    public String toString() {
        String content="";
        for (String line : getLines()) {
            content+=line+"\n";
        }
        return content;
    }
        
}
