package cn.jt.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="sidecar")
public interface SidecarServiceFeign {
	
	//访问sidecar对node.js封装，返回欢迎页面
	@RequestMapping("/node")
	public String node();
}
