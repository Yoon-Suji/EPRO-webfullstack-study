package kr.or.connect.guestbook.argumentresolver;

import java.util.HashMap;
import java.util.Map;

public class HeaderInfo {
	private Map<String, String> map;
	
	public HeaderInfo() {
		map = new HashMap<>();
	}
	
	//값을 넣고 꺼낼 수 있는 put, get 메소드 정의
	public void put(String name, String value) {
		map.put(name,  value);
	}
	
	public String get(String name) {
		return map.get(name);
	}

}
