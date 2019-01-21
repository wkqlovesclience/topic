package com.coo8.btoc.model.user;

import java.sql.Timestamp;

import com.coo8.btoc.model.BaseModel;
import com.coo8.common.utils.StringUtil;
/**
 * 
 * 用户模型
 *
 * @author <a href="mailto:liqiang@staff.chinabyte.com">liqiang</a>
 *
 * @version $Revision$
 *
 * @since Apr 21, 2010
 */
public class User  extends BaseModel{

	private static final long serialVersionUID = 3932965867838631513L;
	
	private Long id;
	private String pin;
	private String password;
	private String email;
	/*
	 * 0 , 为没有激活，1为激活
	 */
	private Integer isActive ; 
	private Double account ;
	private Long jiFenAccount;
	/*
	 * 0 不是黑名单，1为黑名单
	 */
	private Integer isBlackList ;
	private Integer mistakeAccount ;
	private Timestamp lastLoginDate;
	private String  lastLoginIp;
	private Timestamp dateCreated;
	
	private Integer sheng ;
	private Integer shi ;
	private String address;
	private String zip;
	private Integer xian ;
	private String phoneNum;
	private String homeNum;
	
	/**
	 * 省的中文
	 */
	private String provinceString;
	/**
	 * 市的中文
	 */
	private String cityString;
	/**
	 * 县区的中文
	 */
	private String townString;
	/**
	 * 电话区号
	 */
	private String qeHao;
	/**
	 * 电话号码
	 */
	private String num;
	/**
	 * 分机号
	 */
	private String fenJiHao;
	
	private String sendDate;
	private Integer invoiceType ;
	private Integer invoiceHeaderType ;
	private String invoiceHeader;
	private Integer invoiceContent  ;
	
	
	
	/**
	 * @return 省
	 */
	public Integer getSheng() {
		return sheng;
	}
	/**
	 * 设置省
	 * @param sheng 
	 */
	public void setSheng(Integer sheng) {
		this.sheng = sheng;
	}
	/**
	 * 
	 * @return 市
	 */
	public Integer getShi() {
		return shi;
	}
	/**
	 * 设置市
	 * @param shi
	 */
	public void setShi(Integer shi) {
		this.shi = shi;
	}
	/**
	 * @return 返回详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置详细地址
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return 返回邮编
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * 设置邮编
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * 
	 * @return 返回手机号
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * 设置手机号
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return 返回固定电话(*区号**-***电话*****-*分机**)
	 */
	public String getHomeNum() {
		if(StringUtil.isNullorBlank(this.homeNum)){
			StringBuilder sb=new StringBuilder();
			if(this.getNum()==null){
				this.homeNum=null;
				return homeNum;
			}
			sb.append(this.getQeHao()==null?"":this.getQeHao());
			sb.append("-");
			sb.append(this.getNum()==null?"":this.getNum());
			sb.append("-");
			sb.append(this.getFenJiHao()==null?"":this.getFenJiHao());
			homeNum=sb.toString();
		}
		return homeNum;
	}
	/**
	 * 设置返回固定电话
	 * @param homeNum (*区号**-***电话*****-*分机**)
	 */
	public void setHomeNum(String homeNum) {
		if(homeNum!=null && !"null-null-null".equals(homeNum) && !"--".equals(homeNum)){
			this.homeNum = homeNum;
			String[] nums=homeNum.split("-",3);
			String temp="";
			this.setFenJiHao((temp=nums.length==3?nums[2]:null)=="null"?null:temp);
			this.setQeHao((temp=nums.length>1?nums[0]:null)=="null"?null:temp);
			this.setNum((temp=nums.length>1?nums[1]:nums[0])=="null"?null:temp);
		}
	}
	/**
	 * 
	 * @return 主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置主键
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 返回用名
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * 设置用户名
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * 
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置用户密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return 返回用户email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置用户email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 0 , 为没有激活，1为激活
	 * @return 邮箱是否激活
	 */
	public Integer getIsActive() {
		return isActive;
	}
	/**
	 * 设置邮箱是否激活  0 , 为没有激活，1为激活
	 * @param isActive
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	/**
	 * 
	 * @return 返回消费总额
	 */
	public Double getAccount() {
		return account;
	}
	/**
	 * 设置消费总额
	 * @param account
	 */
	public void setAccount(Double account) {
		this.account = account;
	}
	/**
	 * 
	 * @return 返回积分
	 */
	public Long getJiFenAccount() {
		return jiFenAccount;
	}
	/**
	 * 设置积分
	 * @param jiFenAccount
	 */
	public void setJiFenAccount(Long jiFenAccount) {
		this.jiFenAccount = jiFenAccount;
	}
	/**
	 *
	 * @return 返回是否是黑名单
	 */
	public Integer getIsBlackList() {
		return isBlackList;
	}
	/**
	 * 设置黑名单
	 * @param isBlackList
	 */
	public void setIsBlackList(Integer isBlackList) {
		this.isBlackList = isBlackList;
	}
	/**
	 * 
	 * @return 返回用户违规次数
	 */
	public Integer getMistakeAccount() {
		return mistakeAccount;
	}
	/**
	 * 设置用户违规次数
	 * @param mistakeAccount
	 */
	public void setMistakeAccount(Integer mistakeAccount) {
		this.mistakeAccount = mistakeAccount;
	}
	/**
	 * 
	 * @return 返回最后一次登录时间
	 */
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 *设置最后一次登录时间 
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * 
	 * @return 返回最后一次登录ip
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * 设置最后一次登录ip
	 * @param lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * 
	 * @return 返回创建时间
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	/**
	 * 设置创建时间
	 * @param dateCreated 
	 */
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public User() {
		super();
	}
	/**
	 * 
	 * @return 返回送货时间
	 */
	public String getSendDate() {
		return sendDate;
	}
	/**
	 * 设置送货时间
	 * @param sendDate
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	/**
	 * 
	 * @return 返回发票类型
	 */
	public Integer getInvoiceType() {
		return invoiceType == null ? -1 :invoiceType;
	}
	/**
	 * 设置发表类型
	 * @param invoiceType
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 
	 * @return 返回发票抬头类型
	 */
	public Integer getInvoiceHeaderType() {
		return invoiceHeaderType == null ? -1 :invoiceHeaderType;
	}
	/**
	 * 设置发票抬头类型信息
	 * @param invoiceHeaderType
	 */
	public void setInvoiceHeaderType(Integer invoiceHeaderType) {
		this.invoiceHeaderType = invoiceHeaderType;
	}
	/**
	 * 
	 * @return 返回发票抬头
	 */
	public String getInvoiceHeader() {
		return invoiceHeader;
	}
	/**
	 * 设置发票抬头
	 * @param invoiceHeader
	 */
	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	/**
	 * 
	 * @return 设置发票内容
	 */
	public Integer getInvoiceContent() {
		return invoiceContent == null ? -1 :invoiceContent;
	}
	/**
	 * 设置发票内容
	 * @param invoiceContent 
	 */
	public void setInvoiceContent(Integer invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	/**
	 * 
	 * @return 县 地区
	 */
	public Integer getXian() {
		return xian;
	}
	/**
	 *  设置县 地区
	 * @param xian 县 地区
	 */
	public void setXian(Integer xian) {
		this.xian = xian;
	}
	/**
	 * 
	 * @return 返回省的字符串
	 */
	public String getProvinceString() {
		return provinceString;
	}
	/**
	 * 设置省的字符串
	 * @param provinceString 字符串
	 */
	public void setProvinceString(String provinceString) {
		this.provinceString = provinceString;
	}
	/**
	 * 
	 * @return 返回市的字符串
	 */
	public String getCityString() {
		return cityString;
	}
	/**
	 * 设置市的字符串
	 * @param cityString 市的字符串
	 */
	public void setCityString(String cityString) {
		this.cityString = cityString;
	}
	/**
	 * 
	 * @return 返回县区的字符串
	 */
	public String getTownString() {
		return townString;
	}
	/**
	 * 设置县区的字符串
	 * @param townString 县区的字符串
	 */
	public void setTownString(String townString) {
		this.townString = townString;
	}
	/**
	 * 
	 * @return 固定电话区号
	 */
	public String getQeHao() {
		return qeHao;
	}
	/**
	 * 设置  固定电话区号
	 * @param qeHao 固定电话区号
	 */
	public void setQeHao(String qeHao) {
		this.qeHao = qeHao;
	}
	/**
	 * 
	 * @return 固定电话号码
	 */
	public String getNum() {
		return this.num;
	}
	/**
	 * 设置固定电话号码
	 * @param num  固定电话号码
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * 
	 * @return 返回固定电话分机号
	 */
	public String getFenJiHao() {
		return fenJiHao;
	}
	/**
	 * 设置固定电话分机号
	 * @param fenJiHao 固定电话分机号
	 */
	public void setFenJiHao(String fenJiHao) {
		this.fenJiHao = fenJiHao;
	}
	
	
}
