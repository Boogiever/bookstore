/**
 * 
 */
package dao;

import java.util.Collection;
import java.util.List;

import domain.Book;
import domain.ShoppingCartItem;
import web.CriteriaBook;
import web.Page;

/**
 * @author lenovo
 * @description:用于定义操作Book实体类的基本方法
 * @author:xinye
 * @date:2019年11月29日 下午9:16:34
 */
public interface BookDAO {

	/**
	 * 根据 id 获取指定 Book 对象
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);

	/**
	 * 根据传入的 CriteriaBook 对象返回对应的 Page 对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);

	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);

	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb,int pageSize);

	/**
	 * 返回指定 id 的 book 的 storeNumber 字段的值
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);

	/**
	 * 根据传入的 ShoppingCartItem 的集合, 
	 * 批量更新 books 数据表的 storenumber 和 salesnumber 字段的值
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}
