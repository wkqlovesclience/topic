package com.coo8.hotlink.model;

import java.util.Date;

public class HotLink implements java.io.Serializable{

		/**
		 * @author zhanghan-ds3
	     * �μ�������������
	     *
		 */
		private static final long serialVersionUID = -6072947363719953402L;
	
		//���
		private  Integer id;
	    //������������
		private String hotName;
		//��������PC��url
		private String pcUrl;
		//��������WAP��url
		private String wapUrl;
		//����
		private Integer sort;
		//ģ������
		private Integer moduleType;
		//ģ��Id
		private Integer moduleId;
		//����ʱ��
		private Date createDate;
		//������
		private String createUser;
		//�޸���
		private String updateUser;
		//�޸�ʱ��
		private Date updateDate;
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/**
		 * @return the hotName
		 */
		public String getHotName() {
			return hotName;
		}
		/**
		 * @param hotName the hotName to set
		 */
		public void setHotName(String hotName) {
			this.hotName = hotName;
		}
		/**
		 * @return the pcUrl
		 */
		public String getPcUrl() {
			return pcUrl;
		}
		/**
		 * @param pcUrl the pcUrl to set
		 */
		public void setPcUrl(String pcUrl) {
			this.pcUrl = pcUrl;
		}
		/**
		 * @return the wapUrl
		 */
		public String getWapUrl() {
			return wapUrl;
		}
		/**
		 * @param wapUrl the wapUrl to set
		 */
		public void setWapUrl(String wapUrl) {
			this.wapUrl = wapUrl;
		}
		/**
		 * @return the sort
		 */
		public Integer getSort() {
			return sort;
		}
		/**
		 * @param sort the sort to set
		 */
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		/**
		 * @return the moduleType
		 */
		public Integer getModuleType() {
			return moduleType;
		}
		/**
		 * @param moduleType the moduleType to set
		 */
		public void setModuleType(Integer moduleType) {
			this.moduleType = moduleType;
		}
		/**
		 * @return the moduleId
		 */
		public Integer getModuleId() {
			return moduleId;
		}
		/**
		 * @param moduleId the moduleId to set
		 */
		public void setModuleId(Integer moduleId) {
			this.moduleId = moduleId;
		}
		/**
		 * @return the createDate
		 */
		public Date getCreateDate() {
			return createDate;
		}
		/**
		 * @param createDate the createDate to set
		 */
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		/**
		 * @return the createUser
		 */
		public String getCreateUser() {
			return createUser;
		}
		/**
		 * @param createUser the createUser to set
		 */
		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}
		/**
		 * @return the updateUser
		 */
		public String getUpdateUser() {
			return updateUser;
		}
		/**
		 * @param updateUser the updateUser to set
		 */
		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}
		/**
		 * @return the updateDate
		 */
		public Date getUpdateDate() {
			return updateDate;
		}
		/**
		 * @param updateDate the updateDate to set
		 */
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
}
