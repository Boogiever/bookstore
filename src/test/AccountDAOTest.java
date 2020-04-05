/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dao.AccountDAO;
import domain.Account;
import impl.AccountDAOIml;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年12月1日 下午9:16:10
 */
class AccountDAOTest {
	AccountDAO accountDAO=new AccountDAOIml();
	@Test
	void testGet() {
		Account account=accountDAO.get(1);
		System.out.println(account.getBalance());
	}
	@Test
	void testUpdateBalance() {
		accountDAO.updateBalance(1, 50);
	
	}

}
