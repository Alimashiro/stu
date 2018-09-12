package cn.jt.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//标识是一个Feign客户端程序，去调用服务提供者，通过application.name
@FeignClient(value="provider-user")
public interface EurekaServiceFeign {

	/*
	 * Feign中没有原生的@GetMapping/@PostMapping/@DeleteMapping/@PutMapping，要指定需要用method进行
	 */
	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)
	public String hello(@PathVariable("name") String name);
	
	
}

