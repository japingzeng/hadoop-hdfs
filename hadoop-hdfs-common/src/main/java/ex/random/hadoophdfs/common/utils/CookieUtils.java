package ex.random.hadoophdfs.common.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @ClassName: CookieUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月19日 下午8:34:45 
 *
 */
public class CookieUtils {
	private final static Logger logger = LoggerFactory.getLogger(
					CookieUtils.class.getName());
	/**
	 * 校验登录的cookie名
	 */
	private String cookieName;
	
	
	public Cookie queryCookie(HttpServletRequest request, String key) {
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie item : cookies) {
				if (item.getName().equals(key)) {
					cookie = item;
					break;
				}
			}
		}
		return cookie;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param pin
	 * @return
	 * @throws Exception
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @author japing 
	 * @date 2016年8月19日 下午8:37:49
	 */
	public Cookie generatePinCookie(String pin) throws Exception {
		String encryptedPin = EncryptUtils.encryptByAES(String.valueOf(pin), Constants.SECURITY_KEY);
		Cookie pinCookie = new Cookie(Constants.PIN_COOKIE_KEY, encryptedPin);
		pinCookie.setPath("/");
		
		return pinCookie;
	}
	
	
}
