package ex.random.hadoophdfs.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ex.random.hadoophdfs.domain.UserInfo;
import ex.random.hadoophdfs.service.user.UserInfoService;

@Controller
public class LoginController {
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "userName", required = true)String userName, 
							  @RequestParam(value = "passWord", required = true)String passWord) {
		ModelAndView mv = new ModelAndView();
		try {
			UserInfo query = new UserInfo();
			query.setUserName(userName);
			UserInfo user = userInfoService.selectOne(query);
			//密码应该是被加密的，取出user的passWord,进行解密
			
			String decodePassWord = "解密后的密码";
			if (passWord.equals(decodePassWord)) {
				//用户、密码校验成功，跳转到home页面，跳转前，以用户名+passWord+当前时间 加密生成token，并将此token更新到用户表中
				
				mv.setViewName("/home");
				mv.addObject("user", user);
			}
			
			mv.setViewName("/login");
		} catch (Exception e) {
			logger.error("[***login执行异常***]", e.getMessage());
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
