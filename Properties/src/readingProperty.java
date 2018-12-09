import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readingProperty {

	public static void main(String[] args) throws IOException {
		
		// Create an instance of class
		Properties prop = new Properties();
		
		//Provide the path of the file to constructor of the class
		FileInputStream ip = new FileInputStream("/Users/esrakartal/Selenium/testing-maven/properties/src/employee.properties");
		
		prop.load(ip);
		
		// retrieve those properties
		String name = prop.getProperty("name");
		System.out.println(name);                      // Mike
		
		System.out.println(prop.getProperty("age"));   // 30

	}

}
