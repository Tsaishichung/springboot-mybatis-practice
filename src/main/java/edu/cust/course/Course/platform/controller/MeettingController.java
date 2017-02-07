package edu.cust.course.Course.platform.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.cust.course.Course.platform.service.MeettingService;

@RestController
@RequestMapping("/sys/platform")
public class MeettingController {
		@Autowired
		private MeettingService meettingService;
		/**主页载入会议信息*/
		@ResponseBody
		@RequestMapping(value="/getMeettingList",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
		public String getMeettingList(@RequestBody String limit){
			System.out.println(limit);
			return meettingService.load(limit);
		}
}
