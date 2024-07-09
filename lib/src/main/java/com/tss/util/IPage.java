package com.tss.util;

import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class IPage {
	
	private WebDriver driver = null;
	
	private Row row = null;
	
	
	
	public Row getRow() {
		return row;
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}
	public abstract void testPage( int id );
	
	 protected Sheet getWorkSheet() {
		 System.out.println(" SessionStorage.get().getWorkbook() == null : "+ (SessionStorage.get().getWorkbook()==null) );
		Sheet sheet  = SessionStorage.get().getWorkbook().getSheet( this.getClass().getSimpleName() );
		return sheet;
	}
	
	java.util.Map<String , Integer> columns = null;
	 
	public java.util.Map<String , Integer> getColumnIndexes(){
		//System.out.println(" getting columns ");
		if( columns == null ) {
			Row row = getWorkSheet().getRow(0);
			columns = new java.util.TreeMap<String , Integer>( String.CASE_INSENSITIVE_ORDER );
			int count = 0;
			do {
				//System.out.println(" getting columns .1");
				Cell cell = row.getCell( count );
				//System.out.println(" getting columns .2");
				if( cell != null && cell.getStringCellValue() != null &&  !cell.getStringCellValue().trim().equals("")) {
					//System.out.println(" getting columns .3 : = "+ cell.getStringCellValue().toUpperCase());
					columns.put( cell.getStringCellValue().toUpperCase() , Integer.valueOf( count ) );
				}else {
					//System.out.println(" getting columns .4");
					break;
				}
				//System.out.println(" getting columns .5");
				count++;
			} while ( true );	
		}
		System.out.println( " columns : "+ columns );
		return columns;
	}

	protected Cell getCell( String name ) {
		Cell cell = this.row.getCell( getColumnIndexes().get( name.toUpperCase() ) , MissingCellPolicy.CREATE_NULL_AS_BLANK );
		return cell;
	}

	protected String getStrValue( String name ) {
		try {
			
		Cell cell = getCell( name );
		return cell.getStringCellValue();
		
		} catch (Exception e) {
			return "";
		}
	}
	
	protected int getIntValue( String name ) {
		Cell cell = getCell( name );
		return Double.valueOf( cell.getNumericCellValue() ).intValue();
	}
	
	protected double getDoubleValue( String name ) {
		Cell cell = getCell( name );
		return Double.valueOf( cell.getNumericCellValue() ).doubleValue();
	}
	
	public NextPageObject testPage(int id , WebDriver driver) {
		this.driver = driver;
		Sheet sheet = getWorkSheet();
		NextPageObject nextPage = null;
		
		
		try {
				int count = 1 ;
				do {
			
					Row row = sheet.getRow( count );
					this.row = row;
					

					int seq = getIntValue( "SEQ" );

					if( id > 0 && id != seq )
						continue;

					String url = getStrValue( "URL" );

					if(driver.getCurrentUrl().equals( url )) {
						
					}else 
					{
						driver.navigate().to( url );
					}

					String xpath = getStrValue( "XPATH" );

					
					if( xpath != null && !xpath.trim().equals("") ) {
						WebDriverWait w = new WebDriverWait(driver, 10 );
						w.until( ExpectedConditions.presenceOfElementLocated( By.xpath( xpath )));
						PageFactory.initElements(driver, this );
					}else {
						PageFactory.initElements(driver, this );				
					}

					testPage( id );
					nextPage = NextPageObject.get( getStrValue( "NEXTPAGE" ), getIntValue( "NEXTSEQ" )  , getStrValue( "NEXTPAGEURL" ) );
					count++;
					
					if( nextPage == null || nextPage.getId() == 0 )
						continue;
					else  {
						break;
					}
				} while ( true );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return nextPage;
		
	}
	

}
