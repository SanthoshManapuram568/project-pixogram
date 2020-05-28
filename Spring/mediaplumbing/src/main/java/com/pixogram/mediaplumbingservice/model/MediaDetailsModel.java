package com.pixogram.mediaplumbingservice.model;

import java.util.List;

import com.pixogram.mediaplumbingservice.response.GalleryDisplayResponse;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaDetailsModel {
	private List<GalleryDisplayResponse> filelist;
	
}
