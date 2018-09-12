package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.feign.EurekaServiceFeign;
import cn.jt.feign.SidecarServiceFeign;

@RestController
public class HellerController {

	//注入一个feign的接口x
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	@Autowired
	private SidecarServiceFeign SidecarServiceFeign;
	
	@GetMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="fallbackHello") //断路器
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}

	//写一个预案方法：业务访问失败时调用方法，要求：参数和返回值要求用的一值(Eureka的接口)
	public String fallbackHello(String name){
		return "tony";
	}
	
	//实现sidecar对node.js封装，返回欢迎页面
	@RequestMapping("/node")
	public String node(){
		return SidecarServiceFeign.node();
	}
	
}
