package com.coo8.btoc.model.user;

import java.sql.Timestamp;

import com.coo8.btoc.model.BaseModel;
import com.coo8.common.utils.StringUtil;
/**
 * 
 * �û�ģ��
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
	 * 0 , Ϊû�м��1Ϊ����
	 */
	private Integer isActive ; 
	private Double account ;
	private Long jiFenAccount;
	/*
	 * 0 ���Ǻ�������1Ϊ������
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
	 * ʡ������
	 */
	private String provinceString;
	/**
	 * �е�����
	 */
	private String cityString;
	/**
	 * ����������
	 */
	private String townString;
	/**
	 * �绰����
	 */
	private String qeHao;
	/**
	 * �绰����
	 */
	private String num;
	/**
	 * �ֻ���
	 */
	private String fenJiHao;
	
	private String sendDate;
	private Integer invoiceType ;
	private Integer invoiceHeaderType ;
	private String invoiceHeader;
	private Integer invoiceContent  ;
	
	
	
	/**
	 * @return ʡ
	 */
	public Integer getSheng() {
		return sheng;
	}
	/**
	 * ����ʡ
	 * @param sheng 
	 */
	public void setSheng(Integer sheng) {
		this.sheng = sheng;
	}
	/**
	 * 
	 * @return ��
	 */
	public Integer getShi() {
		return shi;
	}
	/**
	 * ������
	 * @param shi
	 */
	public void setShi(Integer shi) {
		this.shi = shi;
	}
	/**
	 * @return ������ϸ��ַ
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * ������ϸ��ַ
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return �����ʱ�
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * �����ʱ�
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * 
	 * @return �����ֻ���
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * �����ֻ���
	 * @param phoneNum
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return ���ع̶��绰(*����**-***�绰*****-*�ֻ�**)
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
	 * ���÷��ع̶��绰
	 * @param homeNum (*����**-***�绰*****-*�ֻ�**)
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
	 * @return ����
	 */
	public Long getId() {
		return id;
	}
	/**
	 * ��������
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return ��������
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * �����û���
	 * @param pin
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * 
	 * @return �û�����
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * �����û�����
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return �����û�email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * �����û�email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 0 , Ϊû�м��1Ϊ����
	 * @return �����Ƿ񼤻�
	 */
	public Integer getIsActive() {
		return isActive;
	}
	/**
	 * ���������Ƿ񼤻�  0 , Ϊû�м��1Ϊ����
	 * @param isActive
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	/**
	 * 
	 * @return ���������ܶ�
	 */
	public Double getAccount() {
		return account;
	}
	/**
	 * ���������ܶ�
	 * @param account
	 */
	public void setAccount(Double account) {
		this.account = account;
	}
	/**
	 * 
	 * @return ���ػ���
	 */
	public Long getJiFenAccount() {
		return jiFenAccount;
	}
	/**
	 * ���û���
	 * @param jiFenAccount
	 */
	public void setJiFenAccount(Long jiFenAccount) {
		this.jiFenAccount = jiFenAccount;
	}
	/**
	 *
	 * @return �����Ƿ��Ǻ�����
	 */
	public Integer getIsBlackList() {
		return isBlackList;
	}
	/**
	 * ���ú�����
	 * @param isBlackList
	 */
	public void setIsBlackList(Integer isBlackList) {
		this.isBlackList = isBlackList;
	}
	/**
	 * 
	 * @return �����û�Υ�����
	 */
	public Integer getMistakeAccount() {
		return mistakeAccount;
	}
	/**
	 * �����û�Υ�����
	 * @param mistakeAccount
	 */
	public void setMistakeAccount(Integer mistakeAccount) {
		this.mistakeAccount = mistakeAccount;
	}
	/**
	 * 
	 * @return �������һ�ε�¼ʱ��
	 */
	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}
	/**
	 *�������һ�ε�¼ʱ�� 
	 * @param lastLoginDate
	 */
	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * 
	 * @return �������һ�ε�¼ip
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * �������һ�ε�¼ip
	 * @param lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * 
	 * @return ���ش���ʱ��
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	/**
	 * ���ô���ʱ��
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
	 * @return �����ͻ�ʱ��
	 */
	public String getSendDate() {
		return sendDate;
	}
	/**
	 * �����ͻ�ʱ��
	 * @param sendDate
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	/**
	 * 
	 * @return ���ط�Ʊ����
	 */
	public Integer getInvoiceType() {
		return invoiceType == null ? -1 :invoiceType;
	}
	/**
	 * ���÷�������
	 * @param invoiceType
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 
	 * @return ���ط�Ʊ̧ͷ����
	 */
	public Integer getInvoiceHeaderType() {
		return invoiceHeaderType == null ? -1 :invoiceHeaderType;
	}
	/**
	 * ���÷�Ʊ̧ͷ������Ϣ
	 * @param invoiceHeaderType
	 */
	public void setInvoiceHeaderType(Integer invoiceHeaderType) {
		this.invoiceHeaderType = invoiceHeaderType;
	}
	/**
	 * 
	 * @return ���ط�Ʊ̧ͷ
	 */
	public String getInvoiceHeader() {
		return invoiceHeader;
	}
	/**
	 * ���÷�Ʊ̧ͷ
	 * @param invoiceHeader
	 */
	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	/**
	 * 
	 * @return ���÷�Ʊ����
	 */
	public Integer getInvoiceContent() {
		return invoiceContent == null ? -1 :invoiceContent;
	}
	/**
	 * ���÷�Ʊ����
	 * @param invoiceContent 
	 */
	public void setInvoiceContent(Integer invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	/**
	 * 
	 * @return �� ����
	 */
	public Integer getXian() {
		return xian;
	}
	/**
	 *  ������ ����
	 * @param xian �� ����
	 */
	public void setXian(Integer xian) {
		this.xian = xian;
	}
	/**
	 * 
	 * @return ����ʡ���ַ���
	 */
	public String getProvinceString() {
		return provinceString;
	}
	/**
	 * ����ʡ���ַ���
	 * @param provinceString �ַ���
	 */
	public void setProvinceString(String provinceString) {
		this.provinceString = provinceString;
	}
	/**
	 * 
	 * @return �����е��ַ���
	 */
	public String getCityString() {
		return cityString;
	}
	/**
	 * �����е��ַ���
	 * @param cityString �е��ַ���
	 */
	public void setCityString(String cityString) {
		this.cityString = cityString;
	}
	/**
	 * 
	 * @return �����������ַ���
	 */
	public String getTownString() {
		return townString;
	}
	/**
	 * �����������ַ���
	 * @param townString �������ַ���
	 */
	public void setTownString(String townString) {
		this.townString = townString;
	}
	/**
	 * 
	 * @return �̶��绰����
	 */
	public String getQeHao() {
		return qeHao;
	}
	/**
	 * ����  �̶��绰����
	 * @param qeHao �̶��绰����
	 */
	public void setQeHao(String qeHao) {
		this.qeHao = qeHao;
	}
	/**
	 * 
	 * @return �̶��绰����
	 */
	public String getNum() {
		return this.num;
	}
	/**
	 * ���ù̶��绰����
	 * @param num  �̶��绰����
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * 
	 * @return ���ع̶��绰�ֻ���
	 */
	public String getFenJiHao() {
		return fenJiHao;
	}
	/**
	 * ���ù̶��绰�ֻ���
	 * @param fenJiHao �̶��绰�ֻ���
	 */
	public void setFenJiHao(String fenJiHao) {
		this.fenJiHao = fenJiHao;
	}
	
	
}
