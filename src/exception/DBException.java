/**
 * 
 */
package exception;

/**
 * @author lenovo
 * @description:
 * @author:xinye
 * @date:2019年11月29日 下午9:22:20
 */

public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException() {
	}
	
	public DBException(String msg) {
		super(msg);
	}
	
	public DBException(String msg, Exception ex) {
		super(msg, ex);
	}
}
