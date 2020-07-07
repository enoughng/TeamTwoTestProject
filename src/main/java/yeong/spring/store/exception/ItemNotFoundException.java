package yeong.spring.store.exception;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer itemId;
	public ItemNotFoundException(Integer itemId) {
		super("Not found item : id = " + itemId);
		this.itemId = itemId;
	}
	
	public Integer getItemId() {
		return itemId;
	}
	
}
