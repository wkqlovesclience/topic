package com.coo8.topic.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NewsAssess implements java.io.Serializable{

	private static final long serialVersionUID = 1092344112962785204L;

	//alias
		public static final String TABLE_ALIAS = "NewsAssess";
		public static final String ALIAS_ID = "id";
		public static final String ALIAS_NEWS_ID = "news_id";
		public static final String ALIAS_GOOD = "good";
		public static final String ALIAS_BAD = "bad";
		public static final String ALIAS_CREATE_TIME = "insert_time";
		public static final String ALIAS_UPDATE_TIME = "update_time";
		 
		//columns START
		private java.lang.Integer id;
		private java.lang.Integer news_id;
		private java.lang.Integer good;
		private java.lang.Integer bad;
		private java.util.Date insert_time;
		private java.util.Date update_time;
		//columns END
		
		public NewsAssess() {
		}
		
		public NewsAssess(java.lang.Integer newsId, java.lang.Integer good, java.lang.Integer bad){
			this.news_id = newsId;
			this.good = good;
			this.bad = bad;
		}

		public java.lang.Integer getId() {
			return id;
		}

		public void setId(java.lang.Integer id) {
			this.id = id;
		}

		public java.lang.Integer getNews_id() {
			return news_id;
		}

		public void setNews_id(java.lang.Integer news_id) {
			this.news_id = news_id;
		}

		public java.lang.Integer getGood() {
			return good;
		}

		public void setGood(java.lang.Integer good) {
			this.good = good;
		}

		public java.lang.Integer getBad() {
			return bad;
		}

		public void setBad(java.lang.Integer bad) {
			this.bad = bad;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("NewsId",getNews_id())
				.append("Good",getGood())
				.append("Bad",getBad())
				.append("CreateTime",getInsert_time())
				.append("UpdateTime",getUpdate_time())
				.toString();
		}
		@Override
		public int hashCode() {
			return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof NewsAssess == false) return false;
			if(this == obj) return true;
			NewsAssess other = (NewsAssess)obj;
			return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
		}

		public java.util.Date getInsert_time() {
			return insert_time;
		}

		public void setInsert_time(java.util.Date insert_time) {
			this.insert_time = insert_time;
		}

		public java.util.Date getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(java.util.Date update_time) {
			this.update_time = update_time;
		}
}
