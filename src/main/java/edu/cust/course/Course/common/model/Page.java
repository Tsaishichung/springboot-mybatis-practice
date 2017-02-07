package edu.cust.course.Course.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caizc
 * @version 1.0
 * 分页工具类*/
public class Page {
		/**当前页数*/
		private Integer pageNow = 1;
		/**总页数*/
		private Integer total_count; 
		/**每页显示条目*/
		private Integer page_per_show = 8;
		/**关联查询索引*/
		private Map<String,Object> query = new HashMap<String,Object>();
		public Integer getPageNow() {
			return pageNow;
		}
		public void setPageNow(Integer pageNow) {
			this.pageNow = pageNow;
		}
		public Integer getTotal_count() {
			return total_count;
		}
		public void setTotal_count(Integer total_count) {
			this.total_count = total_count;
		}
		public Integer getPage_per_show() {
			return page_per_show;
		}
		public void setPage_per_show(Integer page_per_show) {
			this.page_per_show = page_per_show;
		}
		public Map<String, Object> getQuery() {
			return query;
		}
		public void setQuery(Map<String, Object> query) {
			this.query = query;
		}
		
		
}
