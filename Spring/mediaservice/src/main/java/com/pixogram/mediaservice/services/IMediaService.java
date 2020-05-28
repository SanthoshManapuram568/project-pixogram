package com.pixogram.mediaservice.services;

import java.util.List;
import java.util.Optional;

import com.pixogram.mediaservice.entity.Media;
import com.pixogram.mediaservice.model.MediaData;

public interface IMediaService {
	public List<MediaData> getall(Integer userId);
	public void save(MediaData action);
	public Optional<Media> getWithId(Integer id);
	public void updateuser(MediaData action);
	}
