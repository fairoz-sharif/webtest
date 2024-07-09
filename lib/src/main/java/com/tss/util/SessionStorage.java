package com.tss.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SessionStorage {
	
	private Workbook wb;

	private SessionStorage() {
	}
	
	private static SessionStorage  session = null;
	java.util.Map<String , Object> values = new java.util.HashMap<String , Object>();
	public static SessionStorage get() {
		if( session == null ) {
			session = new SessionStorage();
			System.out.println(" GET-====> loadTestData() : ");
			session.loadTestData();
		}
		
		return session;
	}
	
	
	private void loadTestData() {
		try {
			java.io.File file = new java.io.File("testdata.xlsx");
			System.out.println( file.exists()+ " loadTestData() : "+ file.getAbsolutePath());
			
			this.wb = WorkbookFactory.create( file );
			System.out.println(" loaded TestData() : " + (this.wb == null ));
		} catch (Exception e) {
			System.out.println( " exception occured while loading xlsx : "+ e.getMessage());
			e.printStackTrace();
			throw new RuntimeException( e );
		}
	}


	public Workbook getWorkbook() {
		return wb;
	}
	
	public void setValue( String name , String value) {
		values.put(name , value );
	}
	
	public void removeValue( String name ) {
		values.remove( name );
	}
	
	public void getValue( String name ) {
		values.get( name );
	}
	
	public void removeAllValues() {
		values.clear();
	}
}
