package automation.framework.utils;

import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	    @DataProvider(name = "loginData")
	    public static Object[][] getLoginData() {

	    	String excelPath = System.getProperty("user.dir")
	                + "/src/test/resources/testdata/LoginTestData (1).xlsx";

	        return ExcelUtils.getTestData(excelPath, "LoginData");
	    }
	    
	    @DataProvider(name = "registrationData")
	    public static Object[][] getRegistrationData() {

	    	String excelPath = System.getProperty("user.dir") +
    	            "/src/test/resources/testdata/RegisterD.xlsx";
	    	
	    	List<RegistrationDataPOJO> list =
	    	        ExcelUtils.getDataAsPojo(excelPath,"RegisterData",
		    	            RegistrationDataPOJO.class);

	    	    Object[][] data = new Object[list.size()][1];

	    	    for (int i = 0; i < list.size(); i++) {
	    	        data[i][0] = list.get(i);
	    	    }
	    	    return data;

	    }
	}