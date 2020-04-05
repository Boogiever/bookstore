/**
 * 
 */
package test;

import org.junit.jupiter.api.Test;

import dao.UserDAO;
import domain.User;
import impl.UserDAOImpl;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年12月1日 下午9:26:16
 */
class UserDAOTest {

	private UserDAO userDAO=new UserDAOImpl();
	@Test
	void testGetUser() {
		User user=userDAO.getUser("AAA");
		System.out.println(user);
		
	}

}
