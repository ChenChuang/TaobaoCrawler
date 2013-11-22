package com.cc.tubuy;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Crawler crawler = new Crawler();
//		crawler.mItemcatsGetter.getAllChilds(0L, 1);
//		System.out.println("catnum: " + crawler.mItemcatsGetter.catnum);
		crawler.mItempropsGetter.getAllChilds(50011899L, -1L, 0);
	}

}
