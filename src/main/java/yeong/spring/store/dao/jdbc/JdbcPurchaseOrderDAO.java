package yeong.spring.store.dao.jdbc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import yeong.spring.store.dao.PurchaseOrderDAO;
import yeong.spring.store.vo.PurchaseOrder;

public class JdbcPurchaseOrderDAO implements PurchaseOrderDAO {

	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate template;
	
	public JdbcPurchaseOrderDAO(SimpleJdbcInsert insert) {
		this.insert = insert;
		insert.withTableName("purchase_order").usingColumns("purchase_order_id", "item_id", "payment_info_id", "address");
	}
	
	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	
	public int nextVal() {
		Map<String, Object> emptyMap = Collections.emptyMap();
		return template.queryForObject("SELECT purchase_seq.nextval FROM dual", emptyMap, Integer.class);
	}
	
	@Override
	public void insert(PurchaseOrder order) {
		Map<String, Object> args = new HashMap<>();
		order.setId(nextVal());
		args.put("PURCHASE_ORDER_ID", order.getId());
		args.put("ITEM_ID", order.getItemId());
		args.put("PAYMENT_INFO_ID", order.getPaymentInfoId());
		args.put("ADDRESS", order.getAddress());
		insert.execute(args);
	}

}
