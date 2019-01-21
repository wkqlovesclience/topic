package com.coo8.btoc.model.items;

import java.util.Date;
import java.util.List;

import com.coo8.btoc.model.BaseModel;
import com.coo8.btoc.model.itemsfitting.ItemsFitting;
import com.coo8.btoc.model.present.BtocPresent;
import com.coo8.btoc.model.shoplist.StockInfo;
import com.coo8.btoc.model.taocan.BtocTaocanPrice;



public class BtoCItems extends BaseModel{
	private static final long serialVersionUID = 1L;

	private String bigcatalogname;          // ��Ʒ���������
	private int bigcatalogid;               // ��Ʒ�����id

	public static final int CENTER_MAIN_PUSH = 1;
	public static final int SUB_MAIN_PUSH = 2;
	

	private int id;					
	private String   itemid;                // ��Ʒ���
	private String   name;					// �ͺ�
	private String   productname;			// Ʒ��+����
	private String   byname  ;              // ����
	private String   othername;        		// ��������
	private String   mainimg1;     		    // ͼƬ���ߴ�
	private String   mainimg2;         		// ͼƬ330*330
	private String   mainimg3;       		// ͼƬ120*120
	private String   mainimg4;        		// ͼƬ170*170
	private String   mainimg5;        		// ͼƬ100*100
	private String   mainimg6;              // ͼƬ50*50
	private int   definitionid;    			// ����id
	private String   definitionname;   		// ��������
	private int   brandid;     				// Ʒ��id
	private String   brandname;     		// Ʒ����������  
	private String   brandenglishname;  	// Ʒ������Ӣ��
	private int   seriesid;      			// ϵ��id
	private String   seriesname;        	// ϵ������
	private int   catalogid;      			// ����id
	private String   catalogname;      		// ��������	
	private double   recommendprice;   		// �г���					-
	private double   originalprice;     	// ԭ��Ʒ�۸�(�����ۼ۸�)		-
	private double   price;       			// ����Ʒ�۸񣨼����֣�		-
	private double   memberprice;       	// ��Ա�۸�					-
	private String   gift;         			// �������� 					-
	private double   moneyback;         	// ����						-
	private String   desc;      		    // ��Ʒ����
	private int   salescount;       		// ������ 
	private int   usergradecount;     		// �û�������
	private float   usergrade;      		// �û�����
	private int   priority;       			// �Ƽ������ֶ�				-
	private int   pageviw;         			// �����
	private String   maininfo;         		// ��������
	private int   baseproductid;     		// ��Ʒ��id
	private float   weight;        			// ����
	private float   length;          		// �ߴ�
	private int   status;        			// ״̬(0ͣ�á�1���á�8�ϼܡ�4�¼ܡ�2���� ��10�ϼܴ�����6�¼ܴ�����16�Ծɻ���)	-
	private String   comments;       		// ͣ������					-
	private Date   time;          			// ����ʱ��
	private Date  inputdate;      			// ¼��ʱ��					-
	private int   inputuserid;     			// ¼����id					-
	private String   inputer;       		// ¼����  					-
	private Date   updatetime;       		// ����ʱ��					-
	private String   updater;				// ������					-
	private String   productarea;           // ����
	private String  detail;                 // ��װ�嵥
	private String  volume;                 // ���
	private String  saleservice;            // �����ۺ�
	private String   color;                 // ��ɫ
	private int      points;                // ����
	private String  saleinfo;               // ������Ϣ
	
	private String  province;               // ʡ����					-
	private String  city;               	// ������					-
	private int  provinceid;               	// ʡID						-
	private int  cityid;               		// ��ID						-
	private String  properties;             // ���ԣ���Ʒ���ƺ�����ϵ�	-
	private int  specialStatus;             // ����״̬��8(�Ƿ�һƷ���)��4(�Ƿ���ʾ)��2(�Ƿ���������)��1(�Ƿ���ʾ:�б�ҳ)	-
	private int showstatus;					// ��ʾ״̬��1��ʾ��0����ʾ���ö�ۺ��������������жϣ�							-
	private int count;                      // ��Ʒ������Ϣͳ���Ƿ���һƷ���
	
	private int limitcount;                 // �޹�����(0�������ƹ���������1����ͬһ��id���ֻ����绰ֻ�ܹ���һ�����Դ�����)
	private int paystatus;					// ������ڸ�����ܿ�����ֵ��Ʊ
											//3(������ڸ�����ܿ�����ֵ��Ʊ)��2(������ڸ���ܿ�����ֵ��Ʊ)��1(��������ڸ�����ܿ�����ֵ��Ʊ)��0(��������ڸ���ܿ�����ֵ��Ʊ)
	//������λ
	private String  unit;                   // ������λ
	private String  size;                   // ��� 
	private String  listpagesize;           // �б�����ҳ��ʾ��� 
	private int defectstatus;               // �Ƿ�覴�Ʒ��0������Ʒ��1��覴�Ʒ-δʹ�ù� 3��覴�Ʒ-ά�޹���
	private double subtotalscore;              // �ۺ�����
	private int showpic;                	// ��ʾͼ��(0:��ͼ��,1:������,2:����,3:�ؼ�,4:���,5:����,6:��Ʒ,7:����,8:�Ź�,9:��ɱ)
	private int isnew;                      // �Ƿ���Ʒ(0:��,1:��)
	private int mainpush;                   // ������Ʒ�ͱ���(0:������,1:�ܲ�����(������),2:�ֹ�˾����(������),5:�ܲ����Ʊ���,6:�ֹ�˾���Ʊ���)
	private String branchpush;              // ���Ʒֹ�˾��ID���ö���(,)����
	private String[] specialAttrs;			// ��������

	private String stockcompanyid;          // �Ƿ񱸻��Ĺ�˾��id,�ö��ţ���������
	private double qiangprice;              // ���������ʹ���ǰ�ļ۸�

	private List<BtocPresent> presentList;
	private List<BtocTaocanPrice> taocanList;
	private List<ItemsFitting> peijianList;

	
	private int validPresentNum;
	private int validTaocanNum;
	private int validPeijianNum;
	
private List<StockInfo> liststock;//������
	
	public String getBigcatalogname() {
		return bigcatalogname;
	}
	public List<StockInfo> getListstock() {
		return liststock;
	}
	public void setListstock(List<StockInfo> liststock) {
		this.liststock = liststock;
	}
	public void setBigcatalogname(String bigcatalogname) {
		this.bigcatalogname = bigcatalogname;
	}
	public int getBigcatalogid() {
		return bigcatalogid;
	}
	public void setBigcatalogid(int bigcatalogid) {
		this.bigcatalogid = bigcatalogid;
	}

	private int fitstatus;                  // ���״̬

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getByname() {
		return byname;
	}
	public void setByname(String byname) {
		this.byname = byname;
	}
	public String getOthername() {
		return othername;
	}
	public void setOthername(String othername) {
		this.othername = othername;
	}
	public String getMainimg1() {
		return mainimg1;
	}
	public void setMainimg1(String mainimg1) {
		this.mainimg1 = mainimg1;
	}
	public String getMainimg2() {
		return mainimg2;
	}
	public void setMainimg2(String mainimg2) {
		this.mainimg2 = mainimg2;
	}
	public String getMainimg3() {
		return mainimg3;
	}
	public void setMainimg3(String mainimg3) {
		this.mainimg3 = mainimg3;
	}
	public String getMainimg4() {
		return mainimg4;
	}
	public void setMainimg4(String mainimg4) {
		this.mainimg4 = mainimg4;
	}
	public String getMainimg5() {
		return mainimg5;
	}
	public void setMainimg5(String mainimg5) {
		this.mainimg5 = mainimg5;
	}
	
	public String getMainimg6() {
		return mainimg6;
	}
	public void setMainimg6(String mainimg6) {
		this.mainimg6 = mainimg6;
	}
	public int getDefinitionid() {
		return definitionid;
	}
	public void setDefinitionid(int definitionid) {
		this.definitionid = definitionid;
	}
	public String getDefinitionname() {
		return definitionname;
	}
	public void setDefinitionname(String definitionname) {
		this.definitionname = definitionname;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getBrandenglishname() {
		return brandenglishname;
	}
	public void setBrandenglishname(String brandenglishname) {
		this.brandenglishname = brandenglishname;
	}
	public int getSeriesid() {
		return seriesid;
	}
	public void setSeriesid(int seriesid) {
		this.seriesid = seriesid;
	}
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public String getCatalogname() {
		return catalogname;
	}
	public void setCatalogname(String catalogname) {
		this.catalogname = catalogname;
	}
	public double getRecommendprice() {
		return recommendprice;
	}
	public void setRecommendprice(double recommendprice) {
		this.recommendprice = recommendprice;
	}
	public double getOriginalprice() {
		return originalprice;
	}
	public void setOriginalprice(double originalprice) {
		this.originalprice = originalprice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMemberprice() {
		return memberprice;
	}
	public void setMemberprice(double memberprice) {
		this.memberprice = memberprice;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	public double getMoneyback() {
		return moneyback;
	}
	public void setMoneyback(double moneyback) {
		this.moneyback = moneyback;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getSalescount() {
		return salescount;
	}
	public void setSalescount(int salescount) {
		this.salescount = salescount;
	}
	public int getUsergradecount() {
		return usergradecount;
	}
	public void setUsergradecount(int usergradecount) {
		this.usergradecount = usergradecount;
	}
	public float getUsergrade() {
		return usergrade;
	}
	public void setUsergrade(float usergrade) {
		this.usergrade = usergrade;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getPageviw() {
		return pageviw;
	}
	public void setPageviw(int pageviw) {
		this.pageviw = pageviw;
	}
	public String getMaininfo() {
		return maininfo;
	}
	public void setMaininfo(String maininfo) {
		this.maininfo = maininfo;
	}
	public int getBaseproductid() {
		return baseproductid;
	}
	public void setBaseproductid(int baseproductid) {
		this.baseproductid = baseproductid;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public int getInputuserid() {
		return inputuserid;
	}
	public void setInputuserid(int inputuserid) {
		this.inputuserid = inputuserid;
	}
	public String getInputer() {
		return inputer;
	}
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getProductarea() {
		return productarea;
	}
	public void setProductarea(String productarea) {
		this.productarea = productarea;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getSaleservice() {
		return saleservice;
	}
	public void setSaleservice(String saleservice) {
		this.saleservice = saleservice;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getSaleinfo() {
		return saleinfo;
	}
	public void setSaleinfo(String saleinfo) {
		this.saleinfo = saleinfo;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public int getSpecialStatus() {
		return specialStatus;
	}
	public void setSpecialStatus(int specialStatus) {
		this.specialStatus = specialStatus;
	}
	public int getShowstatus() {
		return showstatus;
	}
	public void setShowstatus(int showstatus) {
		this.showstatus = showstatus;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLimitcount() {
		return limitcount;
	}
	public void setLimitcount(int limitcount) {
		this.limitcount = limitcount;
	}
	public int getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(int paystatus) {
		this.paystatus = paystatus;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String[] getSpecialAttrs() {
		return specialAttrs;
	}
	public void setSpecialAttrs(String[] specialAttrs) {
		this.specialAttrs = specialAttrs;
	}
	public String getListpagesize() {
		return listpagesize;
	}
	public void setListpagesize(String listpagesize) {
		this.listpagesize = listpagesize;
	}
	public int getDefectstatus() {
		return defectstatus;
	}
	public void setDefectstatus(int defectstatus) {
		this.defectstatus = defectstatus;
	}
	public double getSubtotalscore() {
		return subtotalscore;
	}
	public void setSubtotalscore(double subtotalscore) {
		this.subtotalscore = subtotalscore;
	}
	public int getShowpic() {
		return showpic;
	}
	public void setShowpic(int showpic) {
		this.showpic = showpic;
	}
	public int getIsnew() {
		return isnew;
	}
	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}
	public int getMainpush() {
		return mainpush;
	}
	public void setMainpush(int mainpush) {
		this.mainpush = mainpush;
	}
	public String getBranchpush() {
		return branchpush;
	}
	public void setBranchpush(String branchpush) {
		this.branchpush = branchpush;
	}
	public int getFitstatus() {
		return fitstatus;
	}
	public void setFitstatus(int fitstatus) {
		this.fitstatus = fitstatus;
	}

	public String getStockcompanyid() {
		return stockcompanyid;
	}
	public void setStockcompanyid(String stockcompanyid) {
		this.stockcompanyid = stockcompanyid;
	}

	public List<BtocPresent> getPresentList() {
		return presentList;
	}
	public void setPresentList(List<BtocPresent> presentList) {
		this.presentList = presentList;
	}
	public List<BtocTaocanPrice> getTaocanList() {
		return taocanList;
	}
	public void setTaocanList(List<BtocTaocanPrice> taocanList) {
		this.taocanList = taocanList;
	}
	public List<ItemsFitting> getPeijianList() {
		return peijianList;
	}
	public void setPeijianList(List<ItemsFitting> peijianList) {
		this.peijianList = peijianList;
	}
	public int getValidPresentNum() {
		return validPresentNum;
	}
	public void setValidPresentNum(int validPresentNum) {
		this.validPresentNum = validPresentNum;
	}
	public int getValidTaocanNum() {
		return validTaocanNum;
	}
	public void setValidTaocanNum(int validTaocanNum) {
		this.validTaocanNum = validTaocanNum;
	}
	public int getValidPeijianNum() {
		return validPeijianNum;
	}
	public void setValidPeijianNum(int validPeijianNum) {
		this.validPeijianNum = validPeijianNum;
	}
	public double getQiangprice() {
		return qiangprice;
	}
	public void setQiangprice(double qiangprice) {
		this.qiangprice = qiangprice;
	}

	
}
