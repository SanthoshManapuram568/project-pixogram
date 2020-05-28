package com.pixogram.mediaplumbingservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.mediaplumbingservice.model.MediaData;
import com.pixogram.mediaplumbingservice.model.MediaDataModel;
import com.pixogram.mediaplumbingservice.model.MediaModel;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;


@FeignClient(name ="api-gateway",url="http://localhost:8765/")//,configuration = {MediaServiceProxy.MultipartSupportConfig.class})
@RibbonClient(name ="media-service")
public interface MediaServiceProxy {
	
	@PostMapping(value = "/media-service/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public boolean save(MultipartFile file);
	
	@PostMapping(value = "/media-service/mediadata")
	public boolean saveData(@RequestBody MediaDataModel  media);
	
	@GetMapping("/media-service/mediabyid/{userId}")
	public ResponseEntity<MediaModel> getall(@PathVariable Integer userId);
	
	@GetMapping("/media-service/mediadetails/{mediaId}")
	public ResponseEntity<MediaData> getDetailsBymediaId(@PathVariable Integer mediaId);
	
	
}
