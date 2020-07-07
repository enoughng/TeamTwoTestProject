package yeong.spring.store.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import yeong.spring.store.service.PlaceOrderService;
import yeong.spring.store.vo.PurchaseOrderRequest;
import yeong.spring.store.vo.PurchaseOrderResult;

public class OrderServiceTestThree {
	private PlaceOrderService placeOrderService;
	private AbstractApplicationContext context;
	public OrderServiceTestThree() {
		String[] configLocations = new String[] { "transactionThree.xml"};
		context = new ClassPathXmlApplicationContext(configLocations);
		placeOrderService = context.getBean("placeOrderService", PlaceOrderService.class);
	}
	
	public void order() {
		PurchaseOrderRequest orderRequest = new PurchaseOrderRequest();
		orderRequest.setItemId(1);
		orderRequest.setAddress("서울시 종로구");
		PurchaseOrderResult orderResult = placeOrderService.order(orderRequest);
		System.out.println("주문상태 정보");
		System.out.println("아이템 : " + orderResult.getItem().getId());
		System.out.println("가격 : " + orderResult.getPaymentInfo().getPrice());
	}
	
	public void close() {
		context.close();
	}
	
	public static void main(String[] args) {
		OrderServiceTestThree test = new OrderServiceTestThree();
		test.order();
		test.close();
	}
}
