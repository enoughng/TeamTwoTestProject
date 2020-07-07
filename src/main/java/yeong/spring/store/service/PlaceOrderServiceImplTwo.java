package yeong.spring.store.service;

import org.springframework.transaction.support.TransactionTemplate;

import yeong.spring.store.dao.ItemDAO;
import yeong.spring.store.dao.PaymentInfoDAO;
import yeong.spring.store.dao.PurchaseOrderDAO;
import yeong.spring.store.exception.ItemNotFoundException;
import yeong.spring.store.vo.Item;
import yeong.spring.store.vo.PaymentInfo;
import yeong.spring.store.vo.PurchaseOrder;
import yeong.spring.store.vo.PurchaseOrderRequest;
import yeong.spring.store.vo.PurchaseOrderResult;

public class PlaceOrderServiceImplTwo implements PlaceOrderService {

	private ItemDAO itemDAO;
	private PaymentInfoDAO paymentInfoDAO;
	private PurchaseOrderDAO purchaseOrderDAO;
	private TransactionTemplate transactionTemplate;
	
	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public void setPaymentInfoDAO(PaymentInfoDAO paymentInfoDAO) {
		this.paymentInfoDAO = paymentInfoDAO;
	}
	
	public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
		this.purchaseOrderDAO = purchaseOrderDAO;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	@Override
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest) throws ItemNotFoundException {
		return transactionTemplate.execute( (status) -> {
			Item item = itemDAO.findById(orderRequest.getItemId());
			if(item == null) throw new ItemNotFoundException(orderRequest.getItemId());
			PaymentInfo paymentInfo = new PaymentInfo(item.getPrice());
			paymentInfoDAO.insert(paymentInfo);
			PurchaseOrder order = new PurchaseOrder(item.getId(), orderRequest.getAddress(), paymentInfo.getPrice());
			purchaseOrderDAO.insert(order);
			return new PurchaseOrderResult(item, paymentInfo, order);
		});
	}

}
