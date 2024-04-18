package ru.relex.Test_3;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            ExcelToJsonConverter converter = new ExcelToJsonConverter();
            String json = converter.convertExcelToJson("students.xlsx");
            System.out.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
