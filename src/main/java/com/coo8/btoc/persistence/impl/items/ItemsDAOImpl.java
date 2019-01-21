package com.coo8.btoc.persistence.impl.items;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.coo8.btoc.model.attribute.Attributeenumvalue;
import com.coo8.btoc.model.attribute.SpecialAttribute;
import com.coo8.btoc.model.catalog.CategoryBrandBase;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.model.items.BtocItemDesc;
import com.coo8.btoc.model.items.ItemQueryParam;
import com.coo8.btoc.model.items.SelectConditions;
import com.coo8.btoc.model.itemsfitting.ItemsFitting;
import com.coo8.btoc.persistence.interfaces.items.IItemsDAO;
import com.coo8.btoc.util.pages.PaginatedArrayList;
import com.coo8.btoc.util.pages.PaginatedList;
@SuppressWarnings("unchecked")
public class ItemsDAOImpl extends SqlMapClientDaoSupport implements IItemsDAO{
	//保存抢购和促销前的价格
	@Override
	public int updateItemsQiangPrice(int id) {
		return getSqlMapClientTemplate().update("Items.updateItemsQiangPrice",id);
	}
	//更改综合积分字段
	@Override
	public int updateItemsSubTotalScore(int id,double subtotalscore){
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("subtotalscore", subtotalscore);
		return getSqlMapClientTemplate().update("Items.updateItemsSubTotalScore",map);
	}
	@Override
	//通过job停止主推商品
	public int updateMainPushByJob(BtoCItems items) {
		return getSqlMapClientTemplate().update("Items.updateMainPushByJob",items);
	}
	//更新是否新品
	@Override
	public int updateItemMainPushById(BtoCItems items) {
		return  getSqlMapClientTemplate().update("Items.updateItemMainPushById",items);
	}
	//通过job停止新品
	@Override
	public int updateIsNewByJob(int id) {
		return getSqlMapClientTemplate().update("Items.updateIsNewByJob",id);
	}
	//更新是否新品
	@Override
	public int updateItemIsNewById(BtoCItems items) {
		return getSqlMapClientTemplate().update("Items.updateItemIsNewById",items);
	}
	//综合评分
	@Override
	public int findItemsInStateShangCount(){
		return (Integer) getSqlMapClientTemplate().queryForObject("Items.findItemsInStateShangCount");
	}
	
	@Override
	public List<BtoCItems> findItemsInStateShang(int pageNo,int pageSize){
		Integer o  =  (Integer)this.getSqlMapClientTemplate().queryForObject("Items.findItemsInStateShangCount");
		if(o == 0){
			return PaginatedArrayList.emptyList();
		}
		PaginatedList<BtoCItems> paginatedArrayList = 
			new PaginatedArrayList<BtoCItems>(Integer.parseInt(o.toString()), pageNo,pageSize);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("begin", paginatedArrayList.getStartPos());
        map.put("end", pageSize);
		List<BtoCItems> list=this.getSqlMapClientTemplate().queryForList("Items.findItemsInStateShang",map);
		if (list != null) {
            paginatedArrayList.addAll(list);
        }
		return paginatedArrayList;
	}
		
	//根据商品id修改商品限购数量
	@Override
	public int updateLimitCountById(BtoCItems items) {
		Object object = getSqlMapClientTemplate().update("Items.updateLimitCountById",items);
		if(object != null) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",items.getId());
			getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
			return 1 ;
		}
		return 0;
	}
	//根据商品id得到积分
	@Override
	public int findItemsPointById(int id){
		return  (Integer)this.getSqlMapClientTemplate().queryForObject("Items.findItemsPointById",id);
	}
	//通过小分类id查找所有的商品的id，List
	@Override
	public List<String> findItemsIdByDefinitionid(int definitionid){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsIdByDefinitionid", definitionid);
	}
	//修改所有商品的maininfo字段，当修改参数的名称或者参数排序的时候
	@Override
	public void modifyItemsParameter(BtoCItems items){
	
		this.getSqlMapClientTemplate().update("Items.updateParameterByCatalogid",items);
	}
	//按排序选择商品
	@Override
	public PaginatedList<BtoCItems> findItemsByPriority(SelectConditions selconditions,String ASCDESC,int pageNo,int pageSize){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("catalogIdList", selconditions.getCatalogIdList());
		map.put("itemid",selconditions.getItemid());
		map.put("name",selconditions.getName());
		map.put("productname",selconditions.getProductname());
		map.put("catalogid",selconditions.getCatalogid());
		map.put("brandid", selconditions.getBrandid());
		map.put("status", selconditions.getStatus());
		map.put("operator", selconditions.getOperator());
		map.put("inbegintime", selconditions.getInbegintime());
		map.put("inendtime",selconditions.getInendtime());
		map.put("upbegintime", selconditions.getUpbegintime());
		map.put("upendtime", selconditions.getUpendtime());
		Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findItemsByPriorityCount",map);
	        if(obj==null){
	            return null;
	        }
        int count = (Integer) obj;
       
        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
        map.put("startPos", paginatedArrayList.getStartPos());
        map.put("endPos", pageSize);
        List<BtoCItems> list=new ArrayList<BtoCItems>();
        if("asc".equals(ASCDESC))
        	 list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByPriorityASC", map);
        else
        if("desc".equals(ASCDESC))
        		list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByPriorityDESC", map);
        if (list != null) {
            paginatedArrayList.addAll(list);
        }
        return paginatedArrayList;
	}
	//判断商品是否以旧换新
	@Override
	public int findItemsIsTMById(int id){
		Object object=this.getSqlMapClientTemplate().queryForObject("Items.findItemsIsTMById", id);
		if(object!=null)
			return 1;
		else
			return 0;
	}
	//按搜索条件导出商品
	@Override
	public List<BtoCItems> findExportItemsByConditions(SelectConditions selconditions,List<Integer> catalogIdList){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("itemid",selconditions.getItemid());
		map.put("name",selconditions.getName());
		map.put("productname",selconditions.getProductname());
		map.put("catalogid",selconditions.getCatalogid());
		map.put("brandid", selconditions.getBrandid());
		map.put("status", selconditions.getStatus());
		map.put("operator", selconditions.getOperator());
		map.put("inbegintime", selconditions.getInbegintime());
		map.put("inendtime",selconditions.getInendtime());
		map.put("upbegintime", selconditions.getUpbegintime());
		map.put("upendtime", selconditions.getUpendtime());
		map.put("catalogIdList", catalogIdList);
		return this.getSqlMapClientTemplate().queryForList("Items.findExportItemsByConditions", map);
	}
	//按id导出商品
	@Override
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList){
		return this.getSqlMapClientTemplate().queryForList("Items.findExportItemsByIdList", idList);
	}
	//按brandid 查商品
	@Override
	public List<String> findItemsBybrandid(int brandid){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsBybrandid", brandid);
	}
	//按id查商品，用于前台(返回参数少)
	@Override
	public BtoCItems findItemsToFrontById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsToFrontById",id);
	}
	//按id查商品，用于前台
	@Override
	public BtoCItems findFrontItemsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findFrontItemsById",id);
	}
	//按itemid，productname，updater 查商品
	@Override
	public PaginatedList<BtoCItems> findItemsByKeywords(String itemid,String productname
			,String operator,int pageNo,int pageSize, int id){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("id", id);
		map.put("itemid", itemid);
		map.put("productname", productname);
		map.put("operator", operator);
		Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findItemsByKeywordsCount",map);
		
        if(obj==null){
            return null;
        }
        int count = (Integer) obj;
        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
       
        map.put("startPos", paginatedArrayList.getStartPos());
        map.put("endPos", pageSize);
        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByKeywords", map);
        if (list != null) {
            paginatedArrayList.addAll(list);
        }
        return paginatedArrayList;
	}
	//按动态名字，value 查询商品
	@Override
	public PaginatedList<BtoCItems> findScatByName(String definitionname,String scatname,String scatvalue,int pageNo,int pageSize){
		//查询动态表的id，list
		String table = "btoc_" + definitionname + "_product";
		
		String sql = "select iId from " + table + " where `"+ scatname+"` =" + scatvalue;
		List<Integer> idList = this.getSqlMapClientTemplate().queryForList("Items.findParamIdByName", sql);
		if(!idList.isEmpty()){
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("idList", idList);
			Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findScatByNameCount",map);
	        if(obj==null){
	            return null;
	        }
	        int count = (Integer) obj;
	        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
	       
	        map.put("startPos", paginatedArrayList.getStartPos());
	        map.put("endPos", pageSize);
	        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findScatByName", map);
	        if (list != null) {
	            paginatedArrayList.addAll(list);
	        }
	        return paginatedArrayList;
		}
		return null;
	}
	//按价格区间查询商品
	@Override
	public PaginatedList<BtoCItems> findScatByPrice(int catalogid,double minprice,double maxprice,int pageNo,int pageSize){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("catalogid", catalogid);
		map.put("minprice", minprice);
		map.put("maxprice", maxprice);
		Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findScatByPriceCount",map);
        if(obj==null){
            return null;
        }
        int count = (Integer) obj;
        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
       
        map.put("startPos", paginatedArrayList.getStartPos());
        map.put("endPos", pageSize);
        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findScatByPrice", map);
        if (list != null) {
            paginatedArrayList.addAll(list);
        }
        return paginatedArrayList;
	}
	//根据大分类ID查所有分类商品
	@Override
	public PaginatedList<BtoCItems> findItemsByBigCatId(List<Integer> catalogIdList,int pageNo,int pageSize, String showstatus){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("catalogIdList", catalogIdList);
		map.put("showstatus", showstatus);
		Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findItemsByBigCatIdCount",map);
        if(obj==null){
            return null;
        }
        int count = (Integer) obj;
        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
       
        map.put("startPos", paginatedArrayList.getStartPos());
        map.put("endPos", pageSize);
        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByBigCatId", map);
        if (list != null) {
            paginatedArrayList.addAll(list);
        }
        return paginatedArrayList;
	}
	
	//按name查所有属性
	@Override
	public List<BtoCItems> findItemsByName(String name){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsByName",name);
	}
	//查询动态表里的数据
	@Override
	public Object findParamColumnsValue(BtoCItems btocitems) {
		
		String table = "btoc_" + btocitems.getDefinitionname() + "_product";
		
		String sql = "select * from " + table + " where itemid ='" + btocitems.getItemid()+"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		
		
		
		return object;
	}
	
	//查询动态表里的数据,批量改商品参数用
	@Override
	public Object findItemsParameter(String itemid,String definitionname) {
		String table = "btoc_" + definitionname + "_product";
		String sql = "select * from " + table + " where itemid ='" + itemid +"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		return object;
	}
		
	//查询配件的商品
	@Override
	public PaginatedList<BtoCItems> findFitConditions(String productname,int catalogid,int brandid,int pageNo,int pageSize){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("productname",productname);
			map.put("catalogid", catalogid);
			map.put("brandid", brandid);
			Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findFitConditionsCount",map);
	        if(obj==null){
	            return null;
	        }
	        int count = (Integer) obj;
	        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
	        map.put("startPos", paginatedArrayList.getStartPos());
	        map.put("endPos", pageSize);
	        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findFitConditions", map);
	        if (list != null) {
	            paginatedArrayList.addAll(list);
	        }
	        return paginatedArrayList;
		}
	//根据具体的查询条件，查询具体的商品，并进行分页
	  @Override
		public PaginatedList<BtoCItems> findItemsByConditions(SelectConditions selconditions,int pageNo,int pageSize,String showstatus){
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("catalogIdList", selconditions.getCatalogIdList());
			map.put("itemid",selconditions.getItemid());
			map.put("name",selconditions.getName());
			map.put("productname",selconditions.getProductname());
			map.put("catalogid",selconditions.getCatalogid());
			map.put("brandid", selconditions.getBrandid());
			map.put("status", selconditions.getStatus());
			map.put("operator", selconditions.getOperator());
			map.put("inbegintime", selconditions.getInbegintime());
			map.put("inendtime",selconditions.getInendtime());
			map.put("upbegintime", selconditions.getUpbegintime());
			map.put("upendtime", selconditions.getUpendtime());
			map.put("defectstatus", selconditions.getDefectstatus());
			map.put("showstatus", showstatus);
			Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findItemsByConditionsCount",map);
		        if(obj==null){
		            return null;
		        }
	        int count = (Integer) obj;
	       
	        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
	        map.put("startPos", paginatedArrayList.getStartPos());
	        map.put("endPos", pageSize);
	        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByConditions", map);
	        if (list != null) {
	            paginatedArrayList.addAll(list);
	        }
	        return paginatedArrayList;
		}
		
	//查询所有的商品，进行分页
		@Override
		public PaginatedList<BtoCItems> findAllItems(int pageNo,int pageSize,String showstatus){
			Map<String,Object> map =new HashMap<String,Object>();
			Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findAllItemsCount");
		        if(obj==null){
		            return null;
		        }
	        int count = (Integer) obj;
	        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
	        map.put("startPos", paginatedArrayList.getStartPos());
	        map.put("endPos", pageSize);
	        map.put("showstatus", showstatus);
	        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findAllItems", map);
	        if (list != null) {
	            paginatedArrayList.addAll(list);
	        }
	        return paginatedArrayList;
		}
	  
	  
		@Override
	  public List<String> findItemsByCatalog(List<Integer> catalogIdList,int catalogid,int brandid){
		  Map<String,Object> map=new HashMap<String, Object>();
		  map.put("catalogIdList", catalogIdList);
		  map.put("catalogid", catalogid);
		  map.put("brandid", brandid);
		  return this.getSqlMapClientTemplate().queryForList("Items.findItemsByCatalog",map);
	  }
	//根据商品编号和商品型号查询item
		@Override
	  public PaginatedList<BtoCItems> findItemsByBrand(String itemid,String name,int catalogid,int brandid,int pageNo,int pageSize){
			Map<String,Object> map =new HashMap<String,Object>();
	        map.put("itemid",itemid);
	        map.put("name",name);
	        map.put("catalogid", catalogid);
	        map.put("brandid", brandid);
	        Object obj = this.getSqlMapClientTemplate().queryForObject("Items.findItemsByBrandCount", map);
	        if(obj==null){
	            return null;
	        }
	        int count = (Integer) obj;
	        PaginatedList<BtoCItems> paginatedArrayList = new PaginatedArrayList<BtoCItems>(count, pageNo,pageSize);
	        map.put("startPos", paginatedArrayList.getStartPos());
	        map.put("endPos", pageSize);
	        List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.findItemsByBrand", map);
	        if (list != null) {
	            paginatedArrayList.addAll(list);
	        }
	        return paginatedArrayList;
		}
	  
	  //查询商品的所有列
		@Override
	public BtoCItems findItemsAllColumnsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsAllColumnsById", id);
	}
	//查询小分类下的所有商品
		@Override
	public List<BtoCItems> findItemsBycatalogid(int catalogid ,int brandid){
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("catalogid", catalogid);
		map.put("brandid",brandid);
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsBycatalogid", map);
	}
	//查询移动的商品
		@Override
	public BtoCItems findItemsMoveItemsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsMoveItemsById", id);
	}
    //  按 id 查询商品
	@Override
	public BtoCItems findAllItemsById(int id) {	
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findAllItemsById",id);
	}
	//根据商品状态查询商品ID列表 
	@Override
	public List<Integer> findAllItemsByStatus(int status) {
		return  this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByStatus",status);
	}
	//  按 itemid 查询原商品 specialStatus&8=0
	@Override
	public BtoCItems findAllItemsByPid(String itemid) {	
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findAllItemsByPid",itemid);
	}
	
	//按 itemid 查询商品
	@Override
	public List<BtoCItems> findAllItemsByItemId(String itemid) {	
		return  this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByItemId",itemid);
	}
  //  按 name 查询商品
	@Override
	public List<BtoCItems> findAllItemsByName(String name) {	
		return  (List<BtoCItems>) this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByName",name);
	}
	 //  按 id查询商品价格
	@Override
	public BtoCItems findPriceById(int itemid){
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findPriceById",itemid);
		
	}
	 //  查询商品配件
	@Override
	public List<BtoCItems> findPeiByStatus(List fittingIdList){
		return  this.getSqlMapClientTemplate().queryForList("Items.findPeiByStatus",fittingIdList);
	}
	
	//插入商品
	@Override
	public int insert(BtoCItems items){
		int obj = (Integer) this.getSqlMapClientTemplate().insert("Items.insert",items);
		Map<String ,Object> map=new HashMap<String ,Object>();
		map.put("id", obj);
		map.put("itemid", "P"+obj);
		this.getSqlMapClientTemplate().update("Items.updateItemsAfterCopy",map);
        return obj;
		
	}
	@Override
	public int insertManyPriceItem(BtoCItems item){
		int r = (Integer) this.getSqlMapClientTemplate().insert("Items.insert",item);
		return r;
	}
	//插入复制动态表的数据
	@Override
	public int insertCopyAttributes(int id,String definitionname,Map mapValue){
		String sname="";
		String svalue="";
		String itemid="P"+id;
		String table="btoc_"+definitionname+"_product";
		Object object = null;
		if(mapValue!=null){
			for(Object key :mapValue.keySet()){
				sname+=",`"+key+"`";
				if(mapValue.get(key)==null)
					svalue+=",''";
				else
					svalue+=",'"+mapValue.get(key)+"'";
				
			}
			String sql="insert into "+table+"(itemid,iId"+sname+",`status`) values('"+itemid+"',"+id+svalue+",0)";
			object=this.getSqlMapClientTemplate().insert("Items.insertItemsAttributes",sql);		
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	//添加商品属性（动态表）
	@Override
	public int insertItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList){
		Object object = null;
		if(attrivalueList!=null){
			String sname="";
			String svalue="";
			for(int i=0;i<attrivalueList.size();i++){
				Attributeenumvalue attvalue=attrivalueList.get(i);
				if(attvalue!=null && attvalue.getValue()!=null && attvalue.getValue()!=""){
					sname+=",`"+attvalue.getAttributename()+"`";
					svalue+=",'"+attvalue.getValue()+"'";
				}				
			}
			
			int status = (items.getShowstatus()==1)&&((items.getStatus()&8)==8)?1:0; // 上架、前台显示
			
			String tablename=items.getDefinitionname();
			String sql="insert into btoc_"+tablename+"_product(itemid,iId,`status`"+sname
				+") values('"+items.getItemid()+"',"+items.getId()+","+status+svalue+")";
			object=this.getSqlMapClientTemplate().insert("Items.insertItemsAttributes",sql);
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	
	//插入到动态表的数据
	@Override
	public int insertDynamicTable(int id,String itemid,String definitionname){
		String table ="btoc_"+definitionname+"_product";
		String sql="insert into "+table+"(itemid,iId) values('"+itemid+"',"+id+")";	
		Object object=this.getSqlMapClientTemplate().insert("Items.insertDynamicTable",sql);
		if(object!=null)
			return 1;
		else return 0;
	}
	//插入到动态表的数据,移动商品时使用
	@Override
	public int insertMoveItemsValue(String sql){
		Object object=this.getSqlMapClientTemplate().insert("Items.insertMoveItemsValue",sql);
		if(object!=null)
			return 1;
		else return 0;
	}
	//修改商品抢购价格
	@Override
	public int updateSnatchbuyPrice	(int id ,double originalprice,double moneyback,int showpic){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",id);
		map.put("originalprice", originalprice);
		map.put("moneyback", moneyback);
		map.put("updatetime",new Date());
		map.put("showpic", showpic);
		Object object=this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPrice",map);
		this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		this.getSqlMapClientTemplate().update("Items.updateSnatPriceProcedure");
		if(object!=null)
			return 1;
		else return 0;
		
	}
	//修改商品用户评分
	@Override
	public int updateItemsByUsergrade(int id,float usergrade){
		//查商品usergrade
		Object object=null;
		BtoCItems items = (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsToFrontById", id);
		if(items!=null){
			float grade=items.getUsergrade();
			if(Math.abs(usergrade-grade)>0){
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",id);
				map.put("usergrade", usergrade);
				object=this.getSqlMapClientTemplate().update("Items.updateItemsByUsergrade",map);
			}
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	
	//新添加的修改商品用户评分，刘俊梅使用
	@Override
	public int updateItemsByUsergrade(String itemid,float usergrade){
		//查商品usergrade
		Object object=null;
		List<BtoCItems> list= (List<BtoCItems>) this.getSqlMapClientTemplate().queryForList("Items.findItemsToFrontByItemId", itemid);
		if(!list.isEmpty()){
			for(BtoCItems items : list){
				float grade=items.getUsergrade();
				if(Math.abs(usergrade-grade)>0){
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("id",items.getId());
					map.put("usergrade", usergrade);
					object=this.getSqlMapClientTemplate().update("Items.updateItemsByUsergrade",map);
				}
			}
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	// 修改商品maininfo
	@Override
	public int updateMaininfo(BtoCItems items){
		return this.getSqlMapClientTemplate().update("Items.updateMaininfo",items);
	}
	//修改商品参数（动态表）
	@Override
	public int updateItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList){
		Object object=null;
		if(attrivalueList!=null){
			String setsql="";
			//动态表的表名是definitionname
			String table="btoc_"+items.getDefinitionname()+"_product";
			
			//构造sql语句的内容
			for(int i=0;i<attrivalueList.size();i++){
				Attributeenumvalue attvalue=attrivalueList.get(i);
				if(attvalue!=null){
					setsql+="`"+attvalue.getAttributename()+"`='"+attvalue.getValue()+"',";
				}
			}
			String sql="update "+table+" set "+setsql.substring(0,setsql.length()-1)+" where itemid='"+items.getItemid()+"'";
			object=this.getSqlMapClientTemplate().update("Items.updateItemsAttributes",sql);
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	//修改商品价格
	@Override
	public int updateOriginalprice(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int i=this.getSqlMapClientTemplate().update("Items.updateOriginalprice",items);
		this.getSqlMapClientTemplate().update("Items.updatePriceProcedure",map);
		return i;
	}
	//修改商品返现
	@Override
	public int updateItemsMoneyback(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int status=items.getStatus();
		int i=this.getSqlMapClientTemplate().update("Items.updateItemsMoneyback",items);
		if((status&8) != 0)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		return i;
	}
	//修改商品参数
	@Override
	public int updateParam(BtoCItems items){
		int i= this.getSqlMapClientTemplate().update("Items.updateParam",items);
		return i;
	}
	
	//修改商品价格
	@Override
	public int updatePrice(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int i=this.getSqlMapClientTemplate().update("Items.updatePrice",items);
        return i;
	}
	//修改商品状态
	@Override
	public int updateStatus(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int status=items.getStatus();
		int i=this.getSqlMapClientTemplate().update("Items.updateStatus",items);
		if((status&8)!=0 && i==1)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		else if((status&4)!=0 || status==0)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
        return i;
	}
	//修改商品促销
	@Override
	public int updateGift(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updateGift",items);
	}
	//单独修改商品的促销语
	@Override
	public int updatePriceGiftById(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updatePriceGiftById",items);
	}
	//通过itemid得到多价商品，用于以旧换新
	@Override
	public List<BtoCItems> findYiJiuHuanXinByItemId(String itemid){
		return getSqlMapClientTemplate().queryForList("Items.findYiJiuHuanXinByItemId",itemid);
	}
	//单独修改多价以旧换新的商品
	@Override
	public int updateYiJiuStatusById(BtoCItems items){
		return getSqlMapClientTemplate().update("Items.updateYiJiuStatusById",items);
	}
	
	
	//按id修改商品排序
	@Override
	public int updatePriorityById(String updater,Date updatetime,int id,int priority){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemid", "P"+id);
		map.put("priority", priority);
		map.put("updater", updater);
		map.put("updatetime", new Date());
		return this.getSqlMapClientTemplate().update("Items.updatePriorityById",map);
	}
	//修改商品排序
	@Override
	public int updatePriority(String updater,Date updatetime,int downid,int downprio){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("downid", downid);
		map.put("downprio", downprio);
		map.put("updater", updater);
		map.put("updatetime", new Date());
		return this.getSqlMapClientTemplate().update("Items.updatePriority",map);
	}
	//修改商品
	@Override
	public int update(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updateItems",items);
	}
	//根据商品id修改商品积分
	@Override
	public int updatePointById(BtoCItems items){
		return this.getSqlMapClientTemplate().update("Items.updatePointById",items);
	}
	//修改商品的分类
	@Override
	public int updateItemsCatalog(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int status=items.getStatus();
		int i=this.getSqlMapClientTemplate().update("Items.updateItemsByCatalog",items);
		
		//这个地方需要改，上架状态8不对，是否需要发布商品
		if((status&8)!=0 && i>=1)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		return i;
	}
	//修改商品发布
	@Override
	public int updateTemplate(int itemid){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemid", itemid);
		return this.getSqlMapClientTemplate().update("Items.getProductTemplateById",map);
	}
	
	//删除动态表的数据
	@Override
	public int deleteItemsAttributes(int id,String definitionname){
		String table ="btoc_"+definitionname+"_product";
		String sql="delete from "+table+" where iId="+id;
		return this.getSqlMapClientTemplate().delete("Items.deleteItemsAttributes",sql);
	}
	
	//删除动态表的数据，并返回动态表的数据
	@Override
	public Object deleteAttributesByItemid(String itemid,String definitionname){
		//得到动态表的数据，目的是进行数据转移
        String table = "btoc_" + definitionname + "_product";
		String sql = "select * from " + table + " where itemid ='" + itemid+"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		
		//删除动态表的数据
		if(object!=null){
			sql="delete from "+table+" where itemid='"+itemid+"'";
			this.getSqlMapClientTemplate().delete("Items.deleteItemsAttributes",sql);
		}
		return object;
	}
	//删除商品
	@Override
	public int delete(int id){
		Object obj = this.getSqlMapClientTemplate().delete("Items.delete",id);
        if(obj!=null){
            return Integer.parseInt(obj.toString());
        }
        return -1;
	}
	
	@Override
	public List<BtoCItems> getRecommended (Map map) {
		List<BtoCItems> list = this.getSqlMapClientTemplate().queryForList("Items.queryrecommended", map);
		return list;
	}

	@Override
	public List<BtoCItems> getItemsByIds(String ids) {
		return getSqlMapClientTemplate().queryForList("Items.queryItemByIds", ids);
	}
	
	@Override
	public void publishAllProduct(int startnum , int pagesize) {
	    Map map=new HashMap();
	       map.put("startnum", startnum);
	       map.put("pagesize", pagesize);
		getSqlMapClientTemplate().update("Items.publishAllProductPage",map);
	}
    @Override
    public int queryOnlineItemCount() {
        
        return (Integer) getSqlMapClientTemplate().
        queryForObject("Items.queryOnlineItemcount");
    }
    @Override
    public List<Integer> queryOnlineItemId(int startnum, int pagesize) {
        List queryForList = getSqlMapClientTemplate().queryForList(
                "Items.queryOnlineitemid", null, startnum, pagesize);
        return queryForList;
    }
	@Override
	public boolean isExsitManyPriceItem(int itemid, int provinceid, int cityid) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("itemid", itemid);
		args.put("provinceid", provinceid);
		args.put("cityid", cityid);
		
		Object r = getSqlMapClientTemplate().queryForObject("Items.isExsitManyPriceItem", args);
		
		if(r == null)return false;
		
		return Integer.parseInt(r.toString()) > 0?true:false;
	}
	
	@Override
	public List<BtoCItems> getManyPriceItems(int itemid) {
		return getSqlMapClientTemplate().queryForList("Items.getManyPriceItems", itemid);
	}
	
	@Override
	public boolean updateManyPriceItemPrice(int itemid, double price) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("id", itemid);
		args.put("price", price);
		
		int r = getSqlMapClientTemplate().update("Items.updateManyPriceItemPrice", args);
		return r==1?true:false;
	}
	@Override
	public boolean updateShowSpecialItem(int itemid) {
		Object itemid_Str = getSqlMapClientTemplate().queryForObject("Items.getItemidByid", itemid);
		if(itemid_Str == null) return false;
		
		List ids = getSqlMapClientTemplate().queryForList("Items.selectSpecialAttrItemids", itemid);
		
		int esc = getSqlMapClientTemplate().update("Items.cancelAllSpecialstatus", ids);
		int set = getSqlMapClientTemplate().update("Items.updateShowSpecialItem", itemid);
		
		return esc>0&&set>0;
	}
	@Override
	public int getEndPageShowNum(int itemid) {
		Object num = getSqlMapClientTemplate().queryForObject("Items.getEndPageShowNum", itemid);
		return num==null?0:Integer.parseInt(num.toString());
	}
	@Override
	public int getListPageShowNum(int itemid) {
		Object num = getSqlMapClientTemplate().queryForObject("Items.getListPageShowNum", itemid);
		return num==null?0:Integer.parseInt(num.toString());
	}
	@Override
	public void escEndPageShow(int itemid) {
		Object id = getSqlMapClientTemplate().queryForObject("Items.getOriginalidByid", itemid);
		getSqlMapClientTemplate().update("Items.escEndPageShow", id);
	}
	@Override
	public void associatedShowSpecialItemid(int itemid) {
		getSqlMapClientTemplate().update("Items.associatedShowSpecialItemid", itemid);
	}
	@Override
	public void synchronizationDataToSpecial(int newid, int oldid) {
		Map<String, Integer> args = new HashMap<String, Integer>(); 
		args.put("newid", newid);
		args.put("oldid", oldid);
		
		getSqlMapClientTemplate().update("Items.synchronizationDataToSpecial", args);
	}
	@Override
	public List<BtoCItems> getManyPriceItemsForInterface(String itemid) {
		return getSqlMapClientTemplate().queryForList("Items.getManyPriceItemsForInterface", itemid);
	}
	
	@Override
	public void updateItemShowStatus(int itemid) {
		List ids = getSqlMapClientTemplate().queryForList("Items.getAllManyPriceItemidByiid", itemid);
		ids.add(itemid);
		
		getSqlMapClientTemplate().update("Items.stopItemShowStatus", ids);
	}
	
	@Override
	public void updateOriginalItemShowStatus(int itemid) {
		List ids = getSqlMapClientTemplate().queryForList("Items.getAllOriginalItemidByiid", itemid);
		ids.add(itemid);
		
		getSqlMapClientTemplate().update("Items.stopItemShowStatus", ids);
	}
	@Override
	public void updateIidToDynamicTable(int itemid, int id) {
		Object catid = getSqlMapClientTemplate().queryForObject("Attribute.getItemCatalogId", id);
		
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("itemid", itemid);
		args.put("id", id);
		args.put("catid", catid);
		getSqlMapClientTemplate().update("Items.updateIidToDynamicTable", args);
	}
	@Override
	public int countManyPriceItemNum(int itemid) {
		Object num = getSqlMapClientTemplate().queryForObject("Items.countManyPriceItemNum", itemid);
		return Integer.parseInt(num.toString());
	}
	@Override
	public List<BtoCItems> getItemAndManyPriceItems(int id) {
		return getSqlMapClientTemplate().queryForList("Items.getItemAndManyPriceItems", id);
	}
	@Override
	public BtoCItems getSaleinfoAndMoneybackByIid(int id) {
		Object obj = getSqlMapClientTemplate().queryForObject("Items.getSaleinfoAndMoneybackByIid", id);
		return obj==null?null:(BtoCItems)obj;
	}
	@Override
	public void setEndPageShow(int itemid) {
		getSqlMapClientTemplate().update("Items.setEndPageShow", itemid);
		
		// 同步新的终端页显示到特殊属性表
		}
	@Override
	public Object getItemCatalogId(int itemid) {
		return getSqlMapClientTemplate().queryForObject("Attribute.getItemCatalogId", itemid);
	}
	@Override
	public void escAllShowStatusToDynamicTable(Map<String, Object> maps) {
		getSqlMapClientTemplate().update("Items.escAllShowStatusToDynamicTable", maps);
	}
	@Override
	public void setShowStatusToDynamicTable(Map<String, Object> args) {
		getSqlMapClientTemplate().update("Items.setShowStatusToDynamicTable", args);
	}
	@Override
	public void setNewShowStatusItemidToDynamicTable(Map<String, Object> args) {
		getSqlMapClientTemplate().update("Items.setNewShowStatusItemidToDynamicTable", args);
	}
	@Override
	public int updateShowManyPriceItem(int itemid, List<Integer> ids) {
		Object itemid_Str = getSqlMapClientTemplate().queryForObject("Items.getItemidByid", itemid);
		if(itemid_Str == null) return -1;
		
		Map maps = new HashMap();
		maps.put("ids", ids);
		maps.put("itemid", itemid_Str);
		
		int esc = getSqlMapClientTemplate().update("Items.cancelAllShowstatus", maps);			// 取消多价、关联的 showStatus = 0
		int set = getSqlMapClientTemplate().update("Items.showStatus", itemid);					// 设置 showStatus = 1
		getSqlMapClientTemplate().update("Items.cancelSpecialStatus", itemid_Str.toString());	// 取消状态4：specialStatus
		getSqlMapClientTemplate().update("Items.setSpecialStatus", itemid);						// 设置状态4：specialStatus
		
		return (esc>0&&set>0)?1:-1;
	}
	@Override
	public void escAllAndAddShowNowItem(int itemid) {
		List<Integer> ids = getSqlMapClientTemplate()
			.queryForList("Items.selectManyPriceAndSpecialAttrItemids", itemid); // 所有相关联的终端页显示商品（上架的）
		
		updateShowManyPriceItem(itemid, ids); // 更新多价相关
		
		Object catid = getItemCatalogId(itemid);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("catid", catid);
		maps.put("ids", ids);
		maps.put("itemid", itemid);
		
		escAllShowStatusToDynamicTable(maps);		// 取消所有动态表列表页显示
		setShowStatusToDynamicTable(maps);			// 设置动态表列表页显示 status = 1
		setNewShowStatusItemidToDynamicTable(maps);	// 同步动态表itemid（新的列表页显示商品id）
	}
	@Override
	public void updateListPageItemsShow(int itemid) {
		escEndPageShow(itemid);	// 停止原商品终端页显示
		
		int specialNum = getEndPageShowNum(itemid);	// 当前商品的所有多价商品里面终端页显示的数量
		
		if(specialNum < 1)
			setEndPageShow(itemid);	// 添加终端页显示、并同步数据到特殊属性表(已取消)
	}
	@Override
	public List<Integer> getManyPriceAndSpecialAttrItemid(int itemid) {
		return getSqlMapClientTemplate()
			.queryForList("Items.selectManyPriceAndSpecialAttrItemids", itemid); // 所有关联、多价商品（上架的）
	}

	@Override
	public int countShelvesManyPriceItemNum(int itemid) {
		Object num = getSqlMapClientTemplate().queryForObject(
				"Items.countShelvesManyPriceItemNum", itemid); // 多价商品数量（上架的）

		return num == null ? 0 : Integer.parseInt(num.toString());
	}
	@Override
	public List<Integer> getShelvesItemAndManyPriceItems(int itemid) {
		return getSqlMapClientTemplate().queryForList("Items.getShelvesItemAndManyPriceItems", itemid);
	}
	@Override
	public List<Integer> getShelvesItemAndManyPriceItemsByIds(String[] ids) {
		return getSqlMapClientTemplate().queryForList("Items.getShelvesItemAndManyPriceItemsByIds", ids);
	}
	
	@Override
	public boolean isSpecialAttrItem(String[] ids) {
		Object num = getSqlMapClientTemplate().queryForObject("Items.isSpecialAttrItem", ids);
		
		return num==null?false:Integer.parseInt(num.toString())>0?true:false;
	}
	
	@Override
	public int getProductidByItemid(int id) {
		Object pid = getSqlMapClientTemplate().queryForObject("Items.getProductidByItemid", id);
		return pid==null?-1:Integer.parseInt(pid.toString());
	}
	@Override
	public Map<String, Set> getSpecialAttributeValue(int id) {
		List<SpecialAttribute> atts = getSqlMapClientTemplate().queryForList("Attribute.getSpecialAttributeValue", id);
		
		Map<String, Set> priority = new HashMap();
		
		for(SpecialAttribute s:atts){
			if(!priority.containsKey(s.getAttributename()))
				priority.put(s.getAttributename(), new HashSet());
			
			priority.get(s.getAttributename()).add(s.getAttvalue());
		}
		
		return priority;
	}
	@Override
	public void deleteSpecialAttributeBuItemid(int id) {
		getSqlMapClientTemplate().delete("Items.deleteSpecialAttributeBuItemid", id);
	}
	@Override
	public int countSpecialItems(int productid) {
		Object num = getSqlMapClientTemplate().queryForObject("Attribute.countSpecialItems", productid);
		return num == null?0:Integer.parseInt(num.toString());
	}
	
	@Override
	public void deleteAllSpecialAttrributeByItemid(int productid) {
		getSqlMapClientTemplate().delete("Attribute.deleteSpecialAttribute", productid);
		getSqlMapClientTemplate().delete("Attribute.deleteSpecialAttributePriority", productid);
	}
	
	@Override
	public void deleteNowSpecialAttribute(int id) {
		int productid = getProductidByItemid(id);
		
		if(productid < 0)return;
		
		deleteSpecialAttributeBuItemid(id);
		
		int itemnum = countSpecialItems(productid);

		if(itemnum < 2){ // 如果只删除当前关联以后只剩下一下关联，则全部清除
			Object showid = getSqlMapClientTemplate().queryForObject("Items.getEndSpecialItem", productid);
			dynamicTablesAndMerchandiseUpdates(productid, showid);
			getSqlMapClientTemplate().update("Items.escSpecialstatusByProductid", productid); // 取消剩下的商品状态：2/1
			deleteAllSpecialAttrributeByItemid(productid);
		} else{
			// 重新生成排序

			Object shownum = getSqlMapClientTemplate().queryForObject("Items.getShowstatusItemNumByProductid", productid);
			
			if(Integer.parseInt(shownum.toString()) < 1){
				Object showid = getSqlMapClientTemplate().queryForObject("Items.getShowstatusItemByProductid", productid);
				if(showid == null)
					showid = getSqlMapClientTemplate().queryForObject("Items.getOldShowstatusItemByProductid", productid);
				
				dynamicTablesAndMerchandiseUpdates(productid, showid);
			}
		}
	}
	
	/** 更新动态表和商品 */
	private void dynamicTablesAndMerchandiseUpdates(int productid, Object showid) {
		Map<String, Object> args = new HashMap<String, Object>();

		Object catid = getItemCatalogId(Integer.parseInt(showid.toString()));
		Object itemid = getSqlMapClientTemplate().queryForObject("Items.getItemidByid", showid);
		
		args.put("catid", catid);
		args.put("showid", showid);
		args.put("itemid", itemid);
		args.put("productid", productid);
		
		getSqlMapClientTemplate().update("Items.showStatus", showid); // 设置列表页显示
		getSqlMapClientTemplate().update("Items.escDynamicTable", args); // 取消动态表 status
		getSqlMapClientTemplate().update("Items.synchronousToDynamicTable", args); // 同步到动态表 status
		getSqlMapClientTemplate().update("Items.synchronousIidToDynamicTable", args); // 同步到动态表 status
	}
	
	@Override
	public void publishItem(BtoCItems item) {
		int status = item.getStatus();
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",item.getId());
		
		if((status&8)!=0)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		else if((status&4)!=0 || status==0)
			this.getSqlMapClientTemplate().update("Items.updateXiaProcedure",map);
	}
	
	@Override
	public void restoreTheDynamicTable(int id) {
		Map<String, Object> args = new HashMap<String, Object>();
		
		args.put("catid", getItemCatalogId(id));
		args.put("id", id);
		
		getSqlMapClientTemplate().update("Items.restoreTheDynamicTable", args);
	}
	
	@Override
	public void setShowStatusToDynamicTable(BtoCItems item){
		Map<String, Object> args = new HashMap<String, Object>();
		Object catid = getItemCatalogId(item.getId());
		
		args.put("catid", catid);
		args.put("itemid", item.getItemid());
		
		getSqlMapClientTemplate().update("Items.setShowStatusToDynamicTableByitemid", args);
	}
	
	@Override
	public void updateItemStatus(int id, int i) {
		Map<String, Object> args = new HashMap<String, Object>();
		
		args.put("id", id);
		args.put("status", i);
		
		getSqlMapClientTemplate().update("Items.updateItemStatus", args);
	}
	
	@Override
	public void updateParamNoStatus(BtoCItems btocitems) {
		getSqlMapClientTemplate().update("Items.updateParamNoStatus",btocitems);
	}
	
	@Override
	public void updateTheDynamicTableStatus(Map<String, Object> args) {
		getSqlMapClientTemplate().update("Items.updateTheDynamicTableStatus",args);
	}
	@Override
	public List<Integer> getPromotionalGoodsByManyPriceAndSpecialAttr(int id) {
		return getSqlMapClientTemplate()
			.queryForList("Items.getPromotionalGoodsByManyPriceAndSpecialAttr", id); // 所有关联、多价商品（上架促销的）
	}
	
	@Override
	public void escPromotionalGoodsStatus(List<Integer> ids) {
		getSqlMapClientTemplate().update("Items.escPromotionalGoodsStatus", ids);
	}
	
	@Override
	public int getShowStatusItem(List<Integer> ids) {
		List<Integer> id = getSqlMapClientTemplate().queryForList("Items.getShowStatusItemByIds", ids);
		
		if(id.size()>1)
			try {
				throw new Exception("同步更新商品的促销状态：返回值过多（关联、多价商品有多个前台显示）");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		else if(id.size()==1)return id.get(0);
		
		return -1;
	}
	
	@Override
	public void setPromotionalGoodsStatusForShowItem(int showid) {
		getSqlMapClientTemplate().update("Items.setPromotionalGoodsStatusForShowItem", showid);
	}
	
	@Override
	public List<BtoCItems> searchSpecialItem(Map<String, Object> args) {
		List<String> specialattr = getSqlMapClientTemplate().queryForList("Items.getDefinationSpecialAttribute", args.get("defid"));
		args.put("specialattr", specialattr);
		
		return getSqlMapClientTemplate().queryForList("Items.searchSpecialItem", args);
	}
	
	@Override
	public List<String> getSpecialAttrColomn(Map<String, Object> args) {
		return getSqlMapClientTemplate().queryForList("Items.getDefinationSpecialAttribute", args.get("defid"));
	}
	
	@Override
	public int countSearchSpecialItem(Map<String, Object> args) {
		Object num = getSqlMapClientTemplate().queryForObject("Items.countSearchSpecialItem", args);
		
		return num == null?0:Integer.parseInt(num.toString());
	}
	
	@Override
	public Map<String, String> getItemSpecialAttrValMap(
			Map<String, Object> args) {
		
		return getSqlMapClientTemplate().queryForMap("Items.getItemSpecialAttrValMap", args, "itemid", "value");
	}
	@Override
	public List<BtoCItems> getSpecialItem(int id) {
		return getSqlMapClientTemplate().queryForList("Items.getSpecialItem", id);
	}

	@Override
	public List<BtoCItems> mobilePrice() {
		return getSqlMapClientTemplate().queryForList("Items.mobilePrice");
	}
	@Override
	public void modifyStatus(ItemsFitting fit) {
		getSqlMapClientTemplate().update("ItemsFitting.modifyStatus",fit);
	}
	
	
	@Override
	public List<BtoCItems> queryCommonItems(ItemQueryParam param) {
		int count = queryCommonItemsCount(param);
		
		if (count == 0) 
			return PaginatedArrayList.emptyList();
		
		
		PaginatedList<BtoCItems> resultList = new PaginatedArrayList<BtoCItems>(
				count, param.getPageIndex(), param.getPageSize());
		
		List<BtoCItems> tempList = getSqlMapClientTemplate()
				.queryForList("Items.commonQueryItems", param, 
						resultList.getStartPos(), resultList.getPageSize());
		
		resultList.addAll(tempList);
		
		return resultList;
	}
	
	private int queryCommonItemsCount(ItemQueryParam param) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject("Items.commonQueryItemsCount", param);
		
		return count == null ? 0 : count.intValue();
	}
	@Override
	public ItemsFitting findFitByItemid(Integer presentId0) {
		return (ItemsFitting) getSqlMapClientTemplate().queryForList("ItemsFitting.selectFitting", presentId0);
	}
	@Override
	public List<CategoryBrandBase> getCatalogListById(Integer catalogId) {
		return  getSqlMapClientTemplate().queryForList("Items.getCatalogListById", catalogId);
	}
	@Override
	public List<BtoCItems> getItemListByCid(Integer catalogId) {
		return  getSqlMapClientTemplate().queryForList("Items.getItemListByCid", catalogId);
	}
	@Override
	public List<CategoryBrandBase> getCatalogListByIdMap(Integer[] catalogArr) {
		return  getSqlMapClientTemplate().queryForList("Items.getCatalogListByIdMap", catalogArr);
	}
	@Override
	public BtocItemDesc findItemDescById(Integer id) {
		List<BtocItemDesc> itemDescList = getSqlMapClientTemplate().queryForList("Items.findItemDescById",id);
		if(!itemDescList.isEmpty()){
			return itemDescList.get(0);
		}
		return null;
	}
	
}
