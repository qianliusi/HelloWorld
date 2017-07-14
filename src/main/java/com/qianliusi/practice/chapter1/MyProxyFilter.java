package com.qianliusi.practice.chapter1;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by qianliusi on 2017/4/5.
 */
public class MyProxyFilter implements CallbackFilter {
	@Override
	public int accept(Method arg0) {
		if(!"query".equalsIgnoreCase(arg0.getName()))
			return 0;
		return 1;
	}
}
