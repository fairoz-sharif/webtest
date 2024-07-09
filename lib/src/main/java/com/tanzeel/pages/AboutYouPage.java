package com.tanzeel.pages;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import com.tss.util.IPage;

@Component("AboutYouPage")
public class AboutYouPage extends IPage implements  DisposableBean{

	WebDriver driver;
	
	public AboutYouPage() {
		
	}

	public void testPage(int id ) {

		return ;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
