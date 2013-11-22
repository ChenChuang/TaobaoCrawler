package com.cc.tubuy;

import java.util.Iterator;
import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;

public class ItemcatsGetter {
	
	TaobaoClient mTaobaoClient;
	int catnum = 0;
	
	public ItemcatsGetter(){
		this.mTaobaoClient = new DefaultTaobaoClient(Crawler.url, Crawler.appkey, Crawler.secret);
	}
	
	protected void getAllChilds(Long cid, int h){
		ItemcatsGetRequest req = new ItemcatsGetRequest();
    	req.setFields("cid,name,is_parent");
    	req.setParentCid(cid);
    	try {
			ItemcatsGetResponse response = this.mTaobaoClient.execute(req);
			if(response.isSuccess()){
				List<ItemCat> itemcats = response.getItemCats();
				if(itemcats != null){
					Iterator<ItemCat> it = itemcats.iterator();
					ItemCat itemcat;
					while( it.hasNext() ){
						int hh = h;
						while(hh > 0){
							System.out.print("-");
							hh--;
						}
						itemcat = it.next();
						System.out.print( itemcat.getCid() + "," + itemcat.getIsParent() + "," + itemcat.getName() );
						this.catnum ++;
						System.out.print("\r\n");
						if(itemcat.getIsParent()){
							this.getAllChilds(itemcat.getCid(), h+1);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("error: " + cid);
		}
	}
	
	protected void getChilds(Long id){
		ItemcatsGetRequest req = new ItemcatsGetRequest();
    	req.setFields("cid,name,is_parent");
    	req.setParentCid(id);
    	try {
			ItemcatsGetResponse response = this.mTaobaoClient.execute(req);
			if(response.isSuccess()){
				System.out.println(response.getMsg());
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("error: " + id);
		}
	}
	
}
