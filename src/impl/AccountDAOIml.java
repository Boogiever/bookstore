/**
 * 
 */
package impl;

import dao.AccountDAO;
import domain.Account;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年11月29日 下午9:41:00
 */
public class AccountDAOIml extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		String sql = "SELECT accountId, balance FROM account WHERE accountId = ?";
		return query(sql, accountId); 
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "UPDATE account SET balance = balance - ? WHERE accountId = ?";
		update(sql, amount, accountId); 
	}

}
