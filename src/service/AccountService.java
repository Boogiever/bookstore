/**
 * 
 */
package service;

import dao.AccountDAO;
import domain.Account;
import impl.AccountDAOIml;

/**
 * @author lenovo
 * @description:业务操作，事务
 * @author:xinye
 * @date:2019年11月29日 下午9:23:11
 */
public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOIml();
	
	public Account getAccount(int accountId){
		return accountDAO.get(accountId);
	}
	
}

