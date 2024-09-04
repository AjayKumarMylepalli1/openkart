package Utilites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Baseclass.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;// report Name
	  public void onStart(ITestContext testContext) {
		   
			/*
			 * SimpleDateFormat df =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			 *  Date dt=new Date(); String currentdatetimestamp =df.format(dt);
			 */
	String timestamp = new SimpleDateFormat(" yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		repName="Test-Report- "+ timestamp +".html"; 
		sparkReporter = new ExtentSparkReporter(".\\Reports\\"+ repName);//Specify location of the
		
		sparkReporter.config().setDocumentTitle("opencart Autmation Report"); // Title of the Report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("sub Module","Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment","QA");
		
	String os=testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System",os);
	
	String broswer =testContext.getCurrentXmlTest().getParameter("broswer");
	extent.setSystemInfo("Broswer", broswer);
	
	List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups",includedGroups.toString());
			}
  }
	 public void onTestStart(ITestResult result) {
	 }

		  public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups()); // to display groups in report
			test.log(Status.PASS,result.getName()+"got successfully Executed");
		  }
		

		 
		   public void onTestFailure(ITestResult result) {
		   test=extent.createTest(result.getTestClass().getName());
		   test.assignCategory(result.getMethod().getGroups());
		   
		   test.log(Status.FAIL,result.getName()+"got failed");
		   test.log(Status.INFO,result.getThrowable().getMessage());
		   try {
			 String imgpath=  new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
		   }catch(Exception e) {
			 e.printStackTrace();   
		   }
		  }

		 
		   public void onTestSkipped(ITestResult result) {
			   test=extent.createTest(result.getTestClass().getName());
			   test.assignCategory(result.getMethod().getGroups());
			   
			   test.log(Status.SKIP,result.getName()+"got Skipped");
			   test.log(Status.INFO,result.getThrowable().getMessage());
		  }

		  
		 
		   public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }


		  
		   public void onFinish(ITestContext testContext) {
		    extent.flush();
			String pathofExtentReport= System.getProperty("user.dir")+"\\Reports\\"+repName;
			File extentReport = new File(pathofExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			
			}catch(IOException e1) {
				e1.printStackTrace();
			}
			
		/*	URL url;
			try {
				url = new URL("file:///"+System.getProperty("user.dir")+"\\Reports\\"+repName);
			
			//Create thee email Message
			ImageHtmlEmail email =new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator( new DefaultAuthenticator("mylepalliajaykumar@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("mylepalliajaykumar@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("please find Attached Report ....");
			email.addTo("Ajaykumar.ajaykumarmylepalli366@gmail.com"); // recevier
			email.attach(url,"extent report","Please check report...");
			email.send(); // send the email
			
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}*/
		   }
		   
}
