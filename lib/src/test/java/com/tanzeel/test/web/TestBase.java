package com.tanzeel.test.web;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tss.util.IPage;
import com.tss.util.NextPageObject;
import com.tss.util.SessionStorage;

/**
 * 
 *
 * @author Fairoz Aman Sharif
 */
public class TestBase  {

	private static ApplicationContext context = null;
	
	static {
		try {
			context = new ClassPathXmlApplicationContext( "applicationContext.xml" );
			SessionStorage.get();
		} catch (Exception e) {

		}
	}
	
	public ApplicationContext getApplicationContext() {
		return context;
	}
	
	public void beginTest(	WebDriver driver , String url , String beanName , int seq ) {

		driver. get ( url );
		
		IPage page = (IPage) getApplicationContext().getBean( beanName );
		NextPageObject nextPageObject = null;
		System.out.println(" Begining The Loop "+ beanName );
		int id = seq;
		do {
			System.out.println(" Invoking test "+ beanName + " \t seq : "+ seq);
			nextPageObject = page.testPage( id , driver );
			
			if( nextPageObject == null || nextPageObject.getNextPage() == null || nextPageObject.getNextPage().trim().equals("") )
				break;
			if( nextPageObject.getNextPageUrl() != null && !nextPageObject.getNextPageUrl().trim().equals("")) {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				boolean bool = wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(nextPageObject.getNextPageUrl())));
				assertTrue( " Expected Url was not met. " + nextPageObject.getNextPageUrl() , bool );
			}

			page = (IPage) getApplicationContext().getBean( nextPageObject.getNextPage() );
			id = nextPageObject.getId();
			
		} while ( true );

	}

	public void beginTest( String url , String beanName , int[] seq ) {
		WebDriver driver = new FirefoxDriver ();
		for (int i : seq) {
			System.out.println(" Begining test "+ beanName + " \t seq : "+ i);
			beginTest( driver, url , beanName , i );
		}
		driver.close();
	}
	
}
