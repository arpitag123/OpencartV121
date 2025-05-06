package testBase;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; // log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups={"sanity","Regression", "Master"})
	@Parameters({"os", "browser"})
	public void setUp(String os, String br) throws IOException 
	
	{
		
		//LOADING config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());  //log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilties = new DesiredCapabilities();
			
			
		//OS
		if(os.equalsIgnoreCase("windows")) 
		{
			capabilties.setPlatform(Platform.WIN10);
			
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			capabilties.setPlatform(Platform.MAC);

		}
		else 
		{
			System.out.println("no matching os");
			return;
			
		}
		
		//browser  
		switch(br.toLowerCase())
		{
		case "chrome": capabilties.setBrowserName("chrome"); break;
		case "edge": capabilties.setBrowserName("MicrosoftEdge"); break;
		default : System.out.println("no matching browser"); return;
		}
		driver = new RemoteWebDriver (new URL("http://192.168.1.4:4444/wd/hub"),capabilties);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case  "chrome" : driver = new ChromeDriver(); break;
				case  "edge" : driver = new EdgeDriver(); break;
				case  "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("invalid browser name"); return; 

			}
				
		}
			
	
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://demo.opencart.com");
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL")); // reading url from config properties file 
		driver.manage().window().maximize();
	}
	 
	@AfterClass(groups={"sanity","Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomString() 
	{
	@SuppressWarnings("deprecation")
	String generatedString = RandomStringUtils.randomAlphabetic(5);	
	return generatedString;
    }
	
	public String randomNumber() 
	{
	
		@SuppressWarnings("deprecation")                                                                                          
		String generatedNumber = RandomStringUtils.randomAlphanumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		@SuppressWarnings("deprecation")
		String generatedString = RandomStringUtils.randomAlphabetic(3);	
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+ "@" + generatednumber ) ;
	}
	
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String tagetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(tagetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return tagetFilePath;
		
	}

}
