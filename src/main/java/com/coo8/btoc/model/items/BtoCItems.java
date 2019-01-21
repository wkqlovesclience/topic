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

	private String bigcatalogname;          // 商品大分类名称
	private int bigcatalogid;               // 商品大分类id

	public static final int CENTER_MAIN_PUSH = 1;
	public static final int SUB_MAIN_PUSH = 2;
	

	private int id;					
	private String   itemid;                // 商品编号
	private String   name;					// 型号
	private String   productname;			// 品牌+名称
	private String   byname  ;              // 别名
	private String   othername;        		// 另类名称
	private String   mainimg1;     		    // 图片最大尺寸
	private String   mainimg2;         		// 图片330*330
	private String   mainimg3;       		// 图片120*120
	private String   mainimg4;        		// 图片170*170
	private String   mainimg5;        		// 图片100*100
	private String   mainimg6;              // 图片50*50
	private int   definitionid;    			// 定义id
	private String   definitionname;   		// 定义名称
	private int   brandid;     				// 品牌id
	private String   brandname;     		// 品牌名称中文  
	private String   brandenglishname;  	// 品牌名称英文
	private int   seriesid;      			// 系列id
	private String   seriesname;        	// 系列名称
	private int   catalogid;      			// 分类id
	private String   catalogname;      		// 分类名称	
	private double   recommendprice;   		// 市场价					-
	private double   originalprice;     	// 原商品价格(即销售价格)		-
	private double   price;       			// 现商品价格（减返现）		-
	private double   memberprice;       	// 会员价格					-
	private String   gift;         			// 赠促销语 					-
	private double   moneyback;         	// 返现						-
	private String   desc;      		    // 产品描述
	private int   salescount;       		// 销售量 
	private int   usergradecount;     		// 用户评价量
	private float   usergrade;      		// 用户评分
	private int   priority;       			// 推荐排序字段				-
	private int   pageviw;         			// 浏览量
	private String   maininfo;         		// 基本属性
	private int   baseproductid;     		// 产品库id
	private float   weight;        			// 重量
	private float   length;          		// 尺寸
	private int   status;        			// 状态(0停用、1启用、8上架、4下架、2促销 、10上架促销、6下架促销、16以旧换新)	-
	private String   comments;       		// 停用理由					-
	private Date   time;          			// 上市时间
	private Date  inputdate;      			// 录入时间					-
	private int   inputuserid;     			// 录入者id					-
	private String   inputer;       		// 录入人  					-
	private Date   updatetime;       		// 更新时间					-
	private String   updater;				// 更新人					-
	private String   productarea;           // 产地
	private String  detail;                 // 包装清单
	private String  volume;                 // 体积
	private String  saleservice;            // 保修售后
	private String   color;                 // 颜色
	private int      points;                // 积分
	private String  saleinfo;               // 销售信息
	
	private String  province;               // 省名称					-
	private String  city;               	// 市名称					-
	private int  provinceid;               	// 省ID						-
	private int  cityid;               		// 市ID						-
	private String  properties;             // 属性：商品名称后面加上的	-
	private int  specialStatus;             // 特殊状态：8(是否一品多价)，4(是否显示)，2(是否特殊属性)，1(是否显示:列表页)	-
	private int showstatus;					// 显示状态：1显示，0不显示（用多价和特殊属性联合判断）							-
	private int count;                      // 商品销售信息统计是否是一品多价
	
	private int limitcount;                 // 限购数量(0代表不限制购买数量，1代表同一个id、手机、电话只能购买一件，以此类推)
	private int paystatus;					// 允许分期付款、不能开具增值发票
											//3(允许分期付款、不能开具增值发票)、2(允许分期付款、能开具增值发票)、1(不允许分期付款、不能开具增值发票)、0(不允许分期付款、能开具增值发票)
	//计量单位
	private String  unit;                   // 计量单位
	private String  size;                   // 规格 
	private String  listpagesize;           // 列表、超市页显示规格 
	private int defectstatus;               // 是否瑕疵品（0：正常品，1：瑕疵品-未使用过 3：瑕疵品-维修过）
	private double subtotalscore;              // 综合评分
	private int showpic;                	// 显示图标(0:无图标,1:惊爆价,2:热卖,3:特价,4:清仓,5:人气,6:新品,7:抢购,8:团购,9:秒杀)
	private int isnew;                      // 是否新品(0:否,1:是)
	private int mainpush;                   // 主推商品和备货(0:不主推,1:总部主推(不备货),2:分公司主推(不备货),5:总部主推备货,6:分公司主推备货)
	private String branchpush;              // 主推分公司的ID，用逗号(,)隔开
	private String[] specialAttrs;			// 特殊属性

	private String stockcompanyid;          // 是否备货的公司的id,用逗号（，）隔开
	private double qiangprice;              // 保存抢购和促销前的价格

	private List<BtocPresent> presentList;
	private List<BtocTaocanPrice> taocanList;
	private List<ItemsFitting> peijianList;

	
	private int validPresentNum;
	private int validTaocanNum;
	private int validPeijianNum;
	
private List<StockInfo> liststock;//配件库存
	
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

	private int fitstatus;                  // 配件状态

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
