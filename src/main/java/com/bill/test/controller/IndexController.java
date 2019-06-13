
package com.bill.test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.bill.test.bean.User;

/**
 * 
 * @author sara
 *
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping
	public String index() {
		return "hello world";
	}

	// @RequestParam 简单类型的绑定，可以出来get和post
	@RequestMapping(value = "/get")
	public User get(@RequestParam(value="name") String name) {
		User user=new User();
		user.setDate(new Date());
		user.setName(name);
		user.setId(UUID.randomUUID().version());
		return user;
	}

	// @PathVariable 获得请求url中的动态参数
	@RequestMapping(value = "/get/{id}/{name}")
	public User getUser(@PathVariable int id, @PathVariable String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setDate(new Date());
		return user;
	}

}
