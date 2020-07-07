package yeong.spring.store.dao;

import yeong.spring.store.vo.Item;

public interface ItemDAO {
	Item findById(Integer itemId);
}
