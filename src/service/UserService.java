/**
 * 
 */
package service;

import dao.UserDAO;
import domain.User;
import impl.UserDAOImpl;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年12月2日 上午8:15:55
 */
public class UserService {
	private UserDAO userDAO=new UserDAOImpl();
	public User getUserByUserName(String username) {
		return userDAO.getUser(username);
	}
}
