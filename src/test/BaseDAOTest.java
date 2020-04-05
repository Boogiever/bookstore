/**
 * 
 */
package test;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Book;
import impl.BookDAOImpl;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年11月29日 下午9:36:36
 */
public class BaseDAOTest {

	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	
	@Test
	public void testInsert() {
		String sql = "INSERT INTO trade (userid, tradetime) VALUES(? ,?)";
		long id = bookDAOImpl.insert(sql, 1, new Date(new java.util.Date().getTime()));
	
		System.out.println(id); 
	}

	@Test
	public void testUpdate() {
		String sql = "UPDATE book SET salesamount = ? WHERE id = ?";
		bookDAOImpl.update(sql, 100, 4);
	}

	@Test
	public void testQuery() {
		String sql = "SELECT id, author, title, price, publishingDate, " +
				"salesAmount, storeNumber, remark FROM book WHERE id = ?";
		
		Book book = bookDAOImpl.query(sql, 4);
		System.out.println(book);
	}

	@Test
	public void testQueryForList() {
		String sql = "SELECT id, author, title, price, publishingDate, " +
				"salesAmount, storeNumber, remark FROM book WHERE id < ?";
		
		List<Book> books = bookDAOImpl.queryForList(sql, 4);
		System.out.println(books);
	}

	@Test
	public void testGetSingleVal() {
		String sql = "SELECT count(id) FROM book";
		
		long count = bookDAOImpl.getSingleVal(sql);
		System.out.println(count); 
	}

	@Test
	public void testBatch() {
		String sql = "UPDATE book SET salesAmount = ?, storeNumber = ? " +
				"WHERE id = ?";
		
		bookDAOImpl.batch(sql, new Object[]{1, 1, 1}, new Object[]{2, 2, 2}, new Object[]{3, 3, 3}); 
	}

}
