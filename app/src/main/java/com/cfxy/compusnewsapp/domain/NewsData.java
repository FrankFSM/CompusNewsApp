package com.cfxy.compusnewsapp.domain;

import java.util.ArrayList;

/**
 * 新闻列表数据
 * 
 * @author Kevin
 * @date 2015-8-12
 */
public class NewsData {

	public int retcode;
	public NewsTab data;

	public class NewsTab {
		public ArrayList<TopNews> topnews;
		public ArrayList<News> news;
		public String title;
		public String more;

		@Override
		public String toString() {
			return "NewsTab [topnews=" + topnews + ", news=" + news
					+ ", title=" + title + "]";
		}

	}

	public class TopNews {
		public String id;
		public String pubdate;
		public String title;
		public String topimage;
		public String url;

		@Override
		public String toString() {
			return "TopNews [title=" + title + "]";
		}

	}

	public class News {
		public String id;
		public String pubdate;
		public String title;
		public String listimage;
		public String url;

		@Override
		public String toString() {
			return "News [title=" + title + "]";
		}

	}

	@Override
	public String toString() {
		return "NewsData [data=" + data + "]";
	}

}
