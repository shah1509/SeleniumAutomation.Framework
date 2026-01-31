package automation.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getTestData(String excelPath, String sheetName) {
		Object[][] data = null;
		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			data = new Object[rowCount - 1][colCount];

			DataFormatter formatter = new DataFormatter();

			for (int i = 1; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < colCount; j++) {
					data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static <T> List<T> getDataAsPojo(String excelPath, String sheetName, Class<T> clazz) {

		List<T> dataList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			Row headerRow = sheet.getRow(0);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = headerRow.getLastCellNum();

			for (int i = 1; i < rowCount; i++) {
				Row currentRow = sheet.getRow(i);
				T obj = clazz.getDeclaredConstructor().newInstance();

				for (int j = 0; j < colCount; j++) {
					String fieldName = headerRow.getCell(j).getStringCellValue();
					String cellValue = currentRow.getCell(j).toString();

					Field field = clazz.getDeclaredField(fieldName);
					field.setAccessible(true);
					field.set(obj, cellValue);
				}
				dataList.add(obj);
			}
		}

		catch (Exception e) {
			throw new RuntimeException("Failed to map Excel to POJO", e);
		}

		return dataList;
	}
}