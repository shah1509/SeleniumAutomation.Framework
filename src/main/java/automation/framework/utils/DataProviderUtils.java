package automation.framework.utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	    @DataProvider(name = "loginData")
	    public static Object[][] getLoginData() {

	    	String excelPath = System.getProperty("user.dir")
	                + "/src/test/resources/testdata/LoginTestData.xlsx";

	        return ExcelUtils.getTestData(excelPath, "LoginData");
	    }
	}
