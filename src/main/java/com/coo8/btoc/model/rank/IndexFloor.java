/**
 * 
 */
package com.coo8.btoc.model.rank;

import java.util.Date;

/**
 * @author wangfufu
 *
 */
public class IndexFloor {

	private Integer id;
	private String category_name;
	private String category_id;
	private Integer priority;
	private Boolean is_valid;
	private Date create_time;
	private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(Boolean is_valid) {
		this.is_valid = is_valid;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
