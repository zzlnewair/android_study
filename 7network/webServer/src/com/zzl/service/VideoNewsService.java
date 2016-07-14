package com.zzl.service;

import java.util.List;

import com.zzl.domain.News;

public interface VideoNewsService {
	/**
	 * 获取最新的视频资讯
	 * @return
	 */
	public List<News> getLastNews();

}