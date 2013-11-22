package com.cc.tubuy;

import java.util.Iterator;
import java.util.List;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemProp;
import com.taobao.api.request.ItempropsGetRequest;
import com.taobao.api.response.ItempropsGetResponse;

public class ItempropsGetter {

	TaobaoClient mTaobaoClient;
	
	public ItempropsGetter(){
		this.mTaobaoClient = new DefaultTaobaoClient(Crawler.url, Crawler.appkey, Crawler.secret);
	}
	
	protected void getAllChilds(Long cid, Long pid, int h){
		ItempropsGetRequest req = new ItempropsGetRequest();
    	req.setFields("pid,name,must,multi,prop_values");
    	req.setCid(cid);
    	if(pid > 0){
    		req.setParentPid(pid);
    	}
    	try {
			ItempropsGetResponse response = this.mTaobaoClient.execute(req);
			if(response.isSuccess()){
				List<ItemProp> itemprops = response.getItemProps();
				if(itemprops != null){
					Iterator<ItemProp> it = itemprops.iterator();
					ItemProp itemprop;
					while( it.hasNext() ){
						int hh = h;
						while(hh > 0){
							System.out.print("-");
							hh--;
						}
						itemprop = it.next();
						System.out.print( itemprop.getCid() + "," + itemprop.getPid() + "," + itemprop.getName() );
						System.out.print("\r\n");
						this.getAllChilds(itemprop.getCid(), itemprop.getPid(), h+1);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error: " + cid);
		}
	}
}
