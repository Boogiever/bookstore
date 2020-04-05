/**
 * 
 */
package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import dao.AccountDAO;
import dao.BookDAO;
import dao.TradeDAO;
import dao.TradeItemDAO;
import dao.UserDAO;
import domain.Book;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import domain.Trade;
import domain.TradeItem;
import impl.AccountDAOIml;
import impl.BookDAOImpl;
import impl.TradeDAOImpl;
import impl.TradeItemDAOImpl;
import impl.UserDAOImpl;
import web.CriteriaBook;
import web.Page;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年11月29日 下午9:23:49
 */
public class BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook criteriaBook){
		return bookDAO.getPage(criteriaBook);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart sc) {
		Book book = bookDAO.getBook(id);
		
		if(book != null){
			sc.addBook(book);
			return true;
		}
		
		return false;
	}

	public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
		sc.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}
	
	private AccountDAO accountDAO = new AccountDAOIml();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();

	//业务方法. 
	public void cash(ShoppingCart shoppingCart, String username,String accountId) {
		
		//1. 更新 book 数据表相关记录的 salesamount 和 storenumber
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
		//int i = 10 / 0;
		
		//2. 更新 account 数据表的 balance
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		
		//3. 向 trade 数据表插入一条记录
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		
		//4. 向 tradeitem 数据表插入 n 条记录
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci: shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			
			tradeItem.setBookId(sci.getBook().getId());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//5. 清空购物车
		shoppingCart.clear();
	}
	
}
