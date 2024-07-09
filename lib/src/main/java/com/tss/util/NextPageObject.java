package com.tss.util;

public class NextPageObject {

	public NextPageObject() {
		// TODO Auto-generated constructor stub
	}

	private NextPageObject(String nextPage, int id, String nextPageUrl) {
		super();
		this.nextPage = nextPage;
		this.id = id;
		this.nextPageUrl = nextPageUrl;
	}



	private String nextPage = null;
	
	private int id ;
	
	private String nextPageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	
	public String getNextPageUrl() {
		return nextPageUrl;
	}



	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}



	public static NextPageObject get(String nextPageName, int seq, String nextPageUrl) {
		if( nextPageName != null &&  !nextPageName.trim().equals("") )
			return new NextPageObject(nextPageName, seq , nextPageUrl );
		else
			return null;
	} 
	
}
