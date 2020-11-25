package com.mayab.calidad.cross;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class PruebaCross {
	
	public static void main(String[] args) throws Exception{
		final String URL = "https://leonelsosav:181a6036-38b2-42db-b77a-077a379e5845@ondemand.us-west-1.saucelabs.com:443/wd/hub";
		  DesiredCapabilities caps = DesiredCapabilities.firefox();

		  caps.setCapability("platform", "Windows 10");
		  caps.setCapability("platform", "Linux");
		  caps.setCapability("platform", "macOS 10.13");
		  caps.setCapability("version", "latest");
		  caps.setCapability("name", "Firefox");
		  caps.setCapability("extendedDebugging", "true");
		  caps.setCapability("buildNumber", "3.0");
		  WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		  driver.get("https://mern-crud.herokuapp.com");
		  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
		    driver.findElement(By.name("name")).click();
		    driver.findElement(By.name("name")).clear();
		    driver.findElement(By.name("name")).sendKeys("leonel");
		    driver.findElement(By.name("email")).click();
		    driver.findElement(By.name("email")).clear();
		    driver.findElement(By.name("email")).sendKeys("leonel@gmail.com");
		    driver.findElement(By.xpath("//form/button")).click();
		  String expected = "Successfully added!";
		  WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p"));
		  String gotMessage = result.getText();
		  assertThat(expected, is(equalTo(gotMessage)));
		  boolean finalResult = false;
		  if(gotMessage.equals("Successfully added!")) {
			  finalResult = true;
		  }else {
			  finalResult = false;
		  }
		  if(finalResult) {
			  ((JavascriptExecutor)driver).executeScript("sauce:job-result=passed");
		  }else {
			  ((JavascriptExecutor)driver).executeScript("sauce:job-result=failed");
		  }driver.quit();
	}

}
