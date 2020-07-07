package yeong.spring.store.dao.jdbc;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import yeong.spring.store.dao.ItemDAO;
import yeong.spring.store.vo.Item;

public class JdbcItemDAO implements ItemDAO {

	private NamedParameterJdbcTemplate template;
	
	public JdbcItemDAO(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public Item findById(Integer itemId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemId", itemId);
		return template.queryForObject("SELECT * FROM ITEM WHERE item_id = :itemId", paramMap, (ResultSet rs, int row) -> {
			Item item = new Item();
			item.setId(rs.getInt("item_id"));
			item.setName(rs.getString("item_name"));
			item.setPrice(rs.getInt("price"));
			return item;
		});
	}

}
