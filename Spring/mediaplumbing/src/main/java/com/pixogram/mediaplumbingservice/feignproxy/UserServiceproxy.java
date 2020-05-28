package com.pixogram.mediaplumbingservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pixogram.mediaplumbingservice.model.ProfileData;

@FeignClient(name ="api-gateway")//,configuration = {MediaServiceProxy.MultipartSupportConfig.class})
@RibbonClient(name ="user-service")
public interface UserServiceproxy {
	@GetMapping("/user-service/profile/{userId}")
	public ResponseEntity<ProfileData> getProfileById(@PathVariable Integer userId);
}
