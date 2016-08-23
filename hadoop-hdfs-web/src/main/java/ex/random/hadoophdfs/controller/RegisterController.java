package ex.random.hadoophdfs.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import ex.random.hadoophdfs.common.utils.Constants;
import ex.random.hadoophdfs.common.utils.EncryptUtils;
import ex.random.hadoophdfs.domain.UserInfo;
import ex.random.hadoophdfs.service.user.UserInfoService;

@Controller
public class RegisterController {
	private final static Logger logger = LoggerFactory
			.getLogger(RegisterController.class);
	@Resource
	private UserInfoService userInfoService;
	@RequestMapping(value = "/register", method = {RequestMethod.POST})
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		try {
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			
			if (null == userName || null == passWord) {
				logger.error("[***用户名或者密码为空***]");
				return mv.addObject("msg", "[***用户名或者密码为空***]");
			}
			//AES算法加密password
			String encodePassWord = EncryptUtils.encryptByAES(passWord, Constants.SECURITY_KEY);
			UserInfo user = new UserInfo();
			user.setLevel(1).setUserName(userName).setPassWord(encodePassWord)
			.setCreatedTime(new Date()).setModifiedTime(new Date());
			//添加用户到用户表
			userInfoService.save(user);
			//注册成功后，转到登录页，进行登录
			mv.setViewName("/login");
				
		} catch (Exception e) {
			logger.error("[***注册失败***]" + e.getMessage());
		}
		return mv;
	}
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

}
