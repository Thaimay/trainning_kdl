package com.world.storedevelopment.utils;

import org.springframework.stereotype.Component;

@Component
public interface HankakuConverter {
	public static String toHankaku(String s) {
		if(s == null) {
			return "";
		}
		return HankakuUtils.toHankaku(s);
	}

}
