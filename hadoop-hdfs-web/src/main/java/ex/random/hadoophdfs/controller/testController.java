package ex.random.hadoophdfs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @ClassName: testController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月18日 下午5:36:27 
 *
 */

@Controller
@RequestMapping(value = "/", method={RequestMethod.GET,RequestMethod.POST})
public class testController {
	
//	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView test() {
//		ModelAndView mv = new ModelAndView("test");
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("username", "japing");
//		model.put("password", "password");
//		//ModelMap modelMap
//		return mv.addAllObjects(model);
//	}
	
	@RequestMapping(value = "demo", method = {RequestMethod.GET,RequestMethod.POST})
	public String test2(ModelMap modelMap) {
		modelMap.put("username", "japing");
		modelMap.put("password", "password");
		//ModelMap modelMap
		return "/demo/test";
	}
}

