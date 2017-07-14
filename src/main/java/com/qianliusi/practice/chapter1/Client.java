package com.qianliusi.practice.chapter1;

/**
 * Created by qianliusi on 2017/4/5.
 */
public class Client {
	public static void main(String[] args) {
		/*BookServiceBean service = BookServiceFactory.getInstance();
		doMethod(service);*/
		BookServiceBean service = BookServiceFactory.getAuthInstanceByFilter(new MyCglibProxy("john"));
		service.create();
		BookServiceBean service2 = BookServiceFactory.getAuthInstanceByFilter(new MyCglibProxy("john"));
		service2.query();
	}

	public static void doMethod(BookServiceBean service) {
		service.create();
		service.update();
		service.query();
		service.delete();
	}
}
