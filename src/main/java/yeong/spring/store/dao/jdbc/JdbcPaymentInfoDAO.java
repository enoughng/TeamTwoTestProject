package yeong.spring.store.dao.jdbc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import yeong.spring.store.dao.PaymentInfoDAO;
import yeong.spring.store.vo.PaymentInfo;

public class JdbcPaymentInfoDAO implements PaymentInfoDAO {

	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public JdbcPaymentInfoDAO(SimpleJdbcInsert insert) {
		this.insert = insert;
		insert.withTableName("Payment_Info").usingColumns("PAYMENT_INFO_ID", "PRICE");
	}
	
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}
	
	public int nextVal() {
		return namedJdbcTemplate.queryForObject("SELECT payment_seq.nextval FROM dual", Collections.emptyMap(), Integer.class);
	}
	
	@Override
	public void insert(PaymentInfo paymentInfo) {
		Map<String, Object> paramMap = new HashMap<>();
		paymentInfo.setId(nextVal());
		paramMap.put("PAYMENT_INFO_ID", paymentInfo.getId());
		paramMap.put("PRICE", paymentInfo.getPrice());
		insert.execute(paramMap);
	}

}
