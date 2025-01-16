package com.test.exceltest;

enum LineType {
    GIVEN("Given",true), WHEN("When",true), THEN("Then",true), AND("And",true);
    private String type;
    private boolean replace;

    private LineType(String type,boolean replace) {
        this.type = type;
        this.replace=replace;
    }

    public String getType() {
        return type;
    }
    
    public boolean isReplace(){
       return replace;
    }
    
    public static LineType getType(String type){
        for (LineType ltype:LineType.values()) {
           if(ltype.type.equals(type))
               return ltype;
        }
        return null;
    }

}

public class Scenario extends Tag {

    public class Line {

        private final LineType type;
        private final String content;

        public Line(LineType type, String content) {
            this.type = type;
            this.content = content;
        }

        public boolean isGiven() {
            return type == LineType.GIVEN;
        }

        public boolean isWhen() {
            return type == LineType.WHEN;
        }

        public boolean isThen() {
            return type == LineType.THEN;
        }

        @Override
        public String toString() {
            return content;
        }
                
    }
    
    @Override
    public void addLine(Object obj) {
        String line = String.valueOf(obj);
        String firstWord = line.substring(0, line.indexOf(' ')).trim();
        LineType type = LineType.getType(firstWord);
        if(type.isReplace()) {
            line = line.replace(firstWord, "");
        }
        super.addLine(new Line(type, line));
    }
}
