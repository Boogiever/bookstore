/**
 * 
 */
package impl;

import dao.UserDAO;
import domain.User;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年11月29日 下午9:43:54
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT userId, username, accountId " +
				"FROM user WHERE username = ?";
		return query(sql, username); 
	}

}

