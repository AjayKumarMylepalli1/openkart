  package Baseclass;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	 public Logger logger;
	 public Properties p;
	  
	  @BeforeClass (groups={"Sanity","Regression","Master"})
	  @Parameters({"os","broswer"})
	  public void setup(String os,String br) throws IOException { 
		  //Loading config.properties file
		  FileReader file = new FileReader("./src//test//resources//config.properties");
		  p = new Properties();
		  p.load(file);
		  
		  logger =  (Logger) LogManager.getLogger(this.getClass());
		  
		/*  if(p.getProperty("execution_env").equalsIgnoreCase("remote"));
		  DesiredCapabilities Capabilities = new DesiredCapabilities();
		  {
			  //os
			  if(os.equalsIgnoreCase("windows")) {
				  Capabilities.setPlatform(Platform.WIN11);
			  }
			  else if(os.equalsIgnoreCase("linux")) {
				  Capabilities.setPlatform(Platform.LINUX);
			  }
			  else if(os.equalsIgnoreCase("mac")) {
					  Capabilities.setPlatform(Platform.MAC);  
				  }
				  else
				  {
					  System.out.println("No Matching Os");
				  }
			 // Capabilities.setPlatform(Platform.WIN11);
			  //Capabilities.setBrowserName("chrome");
			  //broswer
			  switch(br.toLowerCase()){
			  case"chrome": Capabilities.setBrowserName("chrome");break;
			  case "edge": Capabilities.setBrowserName("MicrosoftEdge");break;
			  case "firefox": Capabilities.setBrowserName("firefox");break;
			  default :System.out.println("No matching broswer");return;
			  }
			  driver =new RemoteWebDriver(new URL("http://localhost:4444"),Capabilities);
		  }
		  if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			  switch(br.toLowerCase())
			  {
			  case "chrome" : driver = new ChromeDriver();break;
			  case "edge" : driver = new EdgeDriver();break;
			  case "firefox" : driver = new FirefoxDriver();break;
			  default : System.out.println("Invaild broswer Name ..");return;
			  }  
		  }*/
		  switch(br.toLowerCase())
		  {
		  case "chrome" : driver = new ChromeDriver();break;
		  case "edge" : driver = new EdgeDriver();break;
		  case "firefox" : driver = new FirefoxDriver();break;
		  default : System.out.println("Invaild broswer Name ..");return;
		  }
		  

		 driver =new ChromeDriver();
	  driver.manage().deleteAllCookies();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 
	  //driver.get("https://tutorialsninja.com/demo/");
	  driver.get(p.getProperty("appURL2"));   // reading url from  properties file
	  driver.manage().window().maximize();
	  }
	  
	  @AfterClass 
	  public void tearDown() {
		  driver.quit();
			  } 
	  public String RandomString() { 
		  
		  String genaratedname=RandomStringUtils.randomAlphabetic(5);
		  return genaratedname; 
	  } 
	  public String RandomNumber() { 
		  String randomnumber=RandomStringUtils.randomNumeric(10); 
		  return randomnumber; 
		  }
	  public String RandomAlphaNumber() {
		  String genaratednumber=RandomStringUtils.randomAlphanumeric(5);
		  String genaratedString=RandomStringUtils.randomNumeric(4);
		  return (genaratednumber+"@"+genaratedString); 
	  }
	  
	  public String captureScreen(String tname) throws IOException {
		  String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File sourceFile =takesScreenshot.getScreenshotAs(OutputType.FILE);
		  
		String targetFilepath=  System.getProperty("user.dir"+"\\screenshots"+tname+"_"+timestamp+".png");
		  File targetFile = new File(targetFilepath);
		  
		  sourceFile.renameTo(targetFile);
		  
		  return targetFilepath;
	  } 
}
