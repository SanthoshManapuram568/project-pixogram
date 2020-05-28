package com.pixogram.mediaplumbingservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pixogram.mediaplumbingservice.model.LikesAndDislikesModel;

@FeignClient(name ="api-gateway",url="http://localhost:8765/")
@RibbonClient(name ="action-service")
public interface ActionServiceProxy {
	
	@GetMapping("/action-service/getlikes/{id}")
	public ResponseEntity<LikesAndDislikesModel> getLikesAndDislikes(@PathVariable Integer id);

}
