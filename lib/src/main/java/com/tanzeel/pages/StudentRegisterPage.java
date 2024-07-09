package com.tanzeel.pages;

import org.apache.poi.ss.usermodel.Row;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tss.util.CommonUtil;
import com.tss.util.IPage;
import com.tss.util.SessionStorage;

@Component("StudentRegisterPage")
@Scope(scopeName = "prototype")
public class StudentRegisterPage extends IPage implements DisposableBean{

	
	@FindBy( xpath = "/html/body/my-app/div/boarding-home/div/div/register-form/div/div/form[1]/a" )
	WebElement registerButton;
	

	@FindBy( xpath = "//*[@id=\"email\"]" )
	WebElement emailField;
	
	@FindBy( xpath = "//*[@id=\"password\"]" )
	WebElement pwdField;
	
	public StudentRegisterPage() {
		
	}

	public void testPage( int id ) {
		if( this.registerButton != null )
			System.out.println(" register button is not null ");
		String email = CommonUtil.replaceExpr( getStrValue( "EMAIL") );
		String pswd = getStrValue( "PASSWORD");
		
		emailField.sendKeys( email );
		pwdField.sendKeys( pswd );
		
		Assert.assertTrue( "Register Button Not Displayed " , registerButton.isDisplayed() );
		
		System.out.println(" Registering a user with email "+ email );
		registerButton.click();
		
		String nextPageUrl = getStrValue("NEXTPAGEURL");
		System.out.println(" nextPageUrl : "+ nextPageUrl);
		if( nextPageUrl == null || nextPageUrl.trim().equals("")) {
			nextPageUrl = "https://testing.tanzeel.org/tanzeeltrial/#/student";
		}
		WebDriverWait wait = new WebDriverWait( this.getWebDriver()  , 10);
		boolean bool = wait.until(ExpectedConditions.and(ExpectedConditions.urlToBe(  nextPageUrl )));
		if( !bool ) {
			System.out.println(" Post Register URL not matched Test Failed for email "+ email );
			Assert.assertTrue( "Expected URL not met ", false );
		}
		SessionStorage.get().setValue("loginId", email);
		
		return ;
	}

	public void destroy() throws Exception {
	}
}
