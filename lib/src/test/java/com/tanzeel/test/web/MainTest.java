package com.tanzeel.test.web;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tss.util.IPage;
import com.tss.util.NextPageObject;


public class MainTest extends TestBase {
	

	@Test
	public void testSignupFlow() {
		beginTest( "https://testing.tanzeel.org/tanzeeltrial/" , "StudentRegisterPage" , new int[] {1} );
	}
	
	@Test
	public void testLoginFlow() {
		//beginTest( "https://testing.tanzeel.org/tanzeeltrial/" , "StudentLoginPage" , new int[] {1} );
	}
	
}
