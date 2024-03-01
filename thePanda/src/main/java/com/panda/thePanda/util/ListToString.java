package com.panda.thePanda.util;

import java.util.ArrayList;
import java.util.List;

public class ListToString {
	public List<String> listToStringGenerator(List<String> items, int groupSize) {
		String item = "";
		List<String> newItems = new ArrayList<String>();
		int cnt = 0;
		for(String element : items) {
			if(item == "") {
				item = element;
				cnt++;
			}else if(item != "") {
				item += ","+element;
				cnt++;
			}
			if(cnt == groupSize) {
				newItems.add(item);
				item = "";
				cnt = 0;
			}
		}
			
		
		return newItems;
	}
}
