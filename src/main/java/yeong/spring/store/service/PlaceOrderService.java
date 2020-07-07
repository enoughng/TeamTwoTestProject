package yeong.spring.store.service;

import yeong.spring.store.exception.ItemNotFoundException;
import yeong.spring.store.vo.PurchaseOrderRequest;
import yeong.spring.store.vo.PurchaseOrderResult;

public interface PlaceOrderService {
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest) throws ItemNotFoundException;
}
