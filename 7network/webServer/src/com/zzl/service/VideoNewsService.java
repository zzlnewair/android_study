package com.zzl.service;

import java.util.List;

import com.zzl.domain.News;

public interface VideoNewsService {
	/**
	 * ��ȡ���µ���Ƶ��Ѷ
	 * @return
	 */
	public List<News> getLastNews();

}