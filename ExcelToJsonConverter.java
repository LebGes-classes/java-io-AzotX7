package ru.relex.Test_3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelToJsonConverter {
    public String convertExcelToJson(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        List<Student> students = new ArrayList<>();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) { // skip header row
                continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            String studentName = "";
            List<Subject> studentSubjects = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        studentName = cell.getStringCellValue();
                        break;
                    case 1:
                        String subjectName = cell.getStringCellValue();
                        Cell teacherCell = cell.getRow().getCell(2);
                        String teacherName = "";
                        if (teacherCell != null && teacherCell.getCellType() != CellType.BLANK) {
                            if (teacherCell.getCellType() == CellType.STRING) {
                                teacherName = teacherCell.getRichStringCellValue().getString();
                            } else if (teacherCell.getCellType() == CellType.NUMERIC) {
                                teacherName = String.valueOf(teacherCell.getNumericCellValue());
                            }
                        }
                        Teacher teacher = new Teacher(teacherName, subjectName);
                        Subject subject = new Subject(subjectName, teacher);
                        studentSubjects.add(subject);
                        break;
                }
            }
            students.add(new Student(studentName, studentSubjects));
        }

        workbook.close();
        fis.close();

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        String json = writer.writeValueAsString(students);

        return json;
    }
}