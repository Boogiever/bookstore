/**
 * 
 */
package impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.BookDAO;
import domain.Book;
import domain.ShoppingCartItem;
import web.CriteriaBook;
import web.Page;
/**
 * 
 * @author lenovo
 * @description:使用BaseDAO中的方法实现BookDAO
 * @author:xinye
 * @date:2019年12月3日 下午3:45:29
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT id, author, title, price, publishingDate, " +
				"salesAmount, storeNumber, remark FROM book WHERE id = ?";
		return query(sql, id);
	}

	//3. 
	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//校验 pageNo 的合法性
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		
		return page;
	}

	//1. 
	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT count(id) FROM book WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice()); 
	}

	//2. 
	/**
	 * MySQL 分页使用 LIMIT, 其中 fromIndex 从 0 开始。 
	 */
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT id, author, title, price, publishingDate, " +
				"salesAmount, storeNumber, remark FROM book " +
				"WHERE price >= ? AND price <= ? " +
				"LIMIT ?, ?";
		
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), 
				(cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "SELECT storeNumber FROM book WHERE id = ?";
		return getSingleVal(sql, id);
	}
	/**
	 * 批量更新StoreNumber和SalesAMOUNT
	 */
	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		String sql = "UPDATE book SET salesAmount = salesAmount + ?, " +
				"storeNumber = storeNumber - ? " +
				"WHERE id = ?";
		Object [][] params = null;
		params = new Object[items.size()][3];
		List<ShoppingCartItem> scis = new ArrayList<>(items);
		for(int i = 0; i < items.size(); i++){
			params[i][0] = scis.get(i).getQuantity();
			params[i][1] = scis.get(i).getQuantity();
			params[i][2] = scis.get(i).getBook().getId();
		}
		batch(sql, params);
	}

}

