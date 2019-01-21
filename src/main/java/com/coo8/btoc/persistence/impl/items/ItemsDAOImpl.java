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
	//���������ʹ���ǰ�ļ۸�
	@Override
	public int updateItemsQiangPrice(int id) {
		return getSqlMapClientTemplate().update("Items.updateItemsQiangPrice",id);
	}
	//�����ۺϻ����ֶ�
	@Override
	public int updateItemsSubTotalScore(int id,double subtotalscore){
		Map map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("subtotalscore", subtotalscore);
		return getSqlMapClientTemplate().update("Items.updateItemsSubTotalScore",map);
	}
	@Override
	//ͨ��jobֹͣ������Ʒ
	public int updateMainPushByJob(BtoCItems items) {
		return getSqlMapClientTemplate().update("Items.updateMainPushByJob",items);
	}
	//�����Ƿ���Ʒ
	@Override
	public int updateItemMainPushById(BtoCItems items) {
		return  getSqlMapClientTemplate().update("Items.updateItemMainPushById",items);
	}
	//ͨ��jobֹͣ��Ʒ
	@Override
	public int updateIsNewByJob(int id) {
		return getSqlMapClientTemplate().update("Items.updateIsNewByJob",id);
	}
	//�����Ƿ���Ʒ
	@Override
	public int updateItemIsNewById(BtoCItems items) {
		return getSqlMapClientTemplate().update("Items.updateItemIsNewById",items);
	}
	//�ۺ�����
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
		
	//������Ʒid�޸���Ʒ�޹�����
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
	//������Ʒid�õ�����
	@Override
	public int findItemsPointById(int id){
		return  (Integer)this.getSqlMapClientTemplate().queryForObject("Items.findItemsPointById",id);
	}
	//ͨ��С����id�������е���Ʒ��id��List
	@Override
	public List<String> findItemsIdByDefinitionid(int definitionid){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsIdByDefinitionid", definitionid);
	}
	//�޸�������Ʒ��maininfo�ֶΣ����޸Ĳ��������ƻ��߲��������ʱ��
	@Override
	public void modifyItemsParameter(BtoCItems items){
	
		this.getSqlMapClientTemplate().update("Items.updateParameterByCatalogid",items);
	}
	//������ѡ����Ʒ
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
	//�ж���Ʒ�Ƿ��Ծɻ���
	@Override
	public int findItemsIsTMById(int id){
		Object object=this.getSqlMapClientTemplate().queryForObject("Items.findItemsIsTMById", id);
		if(object!=null)
			return 1;
		else
			return 0;
	}
	//����������������Ʒ
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
	//��id������Ʒ
	@Override
	public List<BtoCItems> findExportItemsByIdList(List<Integer> idList){
		return this.getSqlMapClientTemplate().queryForList("Items.findExportItemsByIdList", idList);
	}
	//��brandid ����Ʒ
	@Override
	public List<String> findItemsBybrandid(int brandid){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsBybrandid", brandid);
	}
	//��id����Ʒ������ǰ̨(���ز�����)
	@Override
	public BtoCItems findItemsToFrontById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsToFrontById",id);
	}
	//��id����Ʒ������ǰ̨
	@Override
	public BtoCItems findFrontItemsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findFrontItemsById",id);
	}
	//��itemid��productname��updater ����Ʒ
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
	//����̬���֣�value ��ѯ��Ʒ
	@Override
	public PaginatedList<BtoCItems> findScatByName(String definitionname,String scatname,String scatvalue,int pageNo,int pageSize){
		//��ѯ��̬���id��list
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
	//���۸������ѯ��Ʒ
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
	//���ݴ����ID�����з�����Ʒ
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
	
	//��name����������
	@Override
	public List<BtoCItems> findItemsByName(String name){
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsByName",name);
	}
	//��ѯ��̬���������
	@Override
	public Object findParamColumnsValue(BtoCItems btocitems) {
		
		String table = "btoc_" + btocitems.getDefinitionname() + "_product";
		
		String sql = "select * from " + table + " where itemid ='" + btocitems.getItemid()+"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		
		
		
		return object;
	}
	
	//��ѯ��̬���������,��������Ʒ������
	@Override
	public Object findItemsParameter(String itemid,String definitionname) {
		String table = "btoc_" + definitionname + "_product";
		String sql = "select * from " + table + " where itemid ='" + itemid +"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		return object;
	}
		
	//��ѯ�������Ʒ
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
	//���ݾ���Ĳ�ѯ��������ѯ�������Ʒ�������з�ҳ
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
		
	//��ѯ���е���Ʒ�����з�ҳ
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
	//������Ʒ��ź���Ʒ�ͺŲ�ѯitem
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
	  
	  //��ѯ��Ʒ��������
		@Override
	public BtoCItems findItemsAllColumnsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsAllColumnsById", id);
	}
	//��ѯС�����µ�������Ʒ
		@Override
	public List<BtoCItems> findItemsBycatalogid(int catalogid ,int brandid){
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("catalogid", catalogid);
		map.put("brandid",brandid);
		return this.getSqlMapClientTemplate().queryForList("Items.findItemsBycatalogid", map);
	}
	//��ѯ�ƶ�����Ʒ
		@Override
	public BtoCItems findItemsMoveItemsById(int id){
		return (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findItemsMoveItemsById", id);
	}
    //  �� id ��ѯ��Ʒ
	@Override
	public BtoCItems findAllItemsById(int id) {	
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findAllItemsById",id);
	}
	//������Ʒ״̬��ѯ��ƷID�б� 
	@Override
	public List<Integer> findAllItemsByStatus(int status) {
		return  this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByStatus",status);
	}
	//  �� itemid ��ѯԭ��Ʒ specialStatus&8=0
	@Override
	public BtoCItems findAllItemsByPid(String itemid) {	
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findAllItemsByPid",itemid);
	}
	
	//�� itemid ��ѯ��Ʒ
	@Override
	public List<BtoCItems> findAllItemsByItemId(String itemid) {	
		return  this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByItemId",itemid);
	}
  //  �� name ��ѯ��Ʒ
	@Override
	public List<BtoCItems> findAllItemsByName(String name) {	
		return  (List<BtoCItems>) this.getSqlMapClientTemplate().queryForList("Items.findAllItemsByName",name);
	}
	 //  �� id��ѯ��Ʒ�۸�
	@Override
	public BtoCItems findPriceById(int itemid){
		return  (BtoCItems) this.getSqlMapClientTemplate().queryForObject("Items.findPriceById",itemid);
		
	}
	 //  ��ѯ��Ʒ���
	@Override
	public List<BtoCItems> findPeiByStatus(List fittingIdList){
		return  this.getSqlMapClientTemplate().queryForList("Items.findPeiByStatus",fittingIdList);
	}
	
	//������Ʒ
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
	//���븴�ƶ�̬�������
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
	//�����Ʒ���ԣ���̬��
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
			
			int status = (items.getShowstatus()==1)&&((items.getStatus()&8)==8)?1:0; // �ϼܡ�ǰ̨��ʾ
			
			String tablename=items.getDefinitionname();
			String sql="insert into btoc_"+tablename+"_product(itemid,iId,`status`"+sname
				+") values('"+items.getItemid()+"',"+items.getId()+","+status+svalue+")";
			object=this.getSqlMapClientTemplate().insert("Items.insertItemsAttributes",sql);
		}
		if(object!=null)
			return 1;
		else return 0;
	}
	
	//���뵽��̬�������
	@Override
	public int insertDynamicTable(int id,String itemid,String definitionname){
		String table ="btoc_"+definitionname+"_product";
		String sql="insert into "+table+"(itemid,iId) values('"+itemid+"',"+id+")";	
		Object object=this.getSqlMapClientTemplate().insert("Items.insertDynamicTable",sql);
		if(object!=null)
			return 1;
		else return 0;
	}
	//���뵽��̬�������,�ƶ���Ʒʱʹ��
	@Override
	public int insertMoveItemsValue(String sql){
		Object object=this.getSqlMapClientTemplate().insert("Items.insertMoveItemsValue",sql);
		if(object!=null)
			return 1;
		else return 0;
	}
	//�޸���Ʒ�����۸�
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
	//�޸���Ʒ�û�����
	@Override
	public int updateItemsByUsergrade(int id,float usergrade){
		//����Ʒusergrade
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
	
	//����ӵ��޸���Ʒ�û����֣�����÷ʹ��
	@Override
	public int updateItemsByUsergrade(String itemid,float usergrade){
		//����Ʒusergrade
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
	// �޸���Ʒmaininfo
	@Override
	public int updateMaininfo(BtoCItems items){
		return this.getSqlMapClientTemplate().update("Items.updateMaininfo",items);
	}
	//�޸���Ʒ��������̬��
	@Override
	public int updateItemsAttributes(BtoCItems items,List<Attributeenumvalue> attrivalueList){
		Object object=null;
		if(attrivalueList!=null){
			String setsql="";
			//��̬��ı�����definitionname
			String table="btoc_"+items.getDefinitionname()+"_product";
			
			//����sql��������
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
	//�޸���Ʒ�۸�
	@Override
	public int updateOriginalprice(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int i=this.getSqlMapClientTemplate().update("Items.updateOriginalprice",items);
		this.getSqlMapClientTemplate().update("Items.updatePriceProcedure",map);
		return i;
	}
	//�޸���Ʒ����
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
	//�޸���Ʒ����
	@Override
	public int updateParam(BtoCItems items){
		int i= this.getSqlMapClientTemplate().update("Items.updateParam",items);
		return i;
	}
	
	//�޸���Ʒ�۸�
	@Override
	public int updatePrice(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int i=this.getSqlMapClientTemplate().update("Items.updatePrice",items);
        return i;
	}
	//�޸���Ʒ״̬
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
	//�޸���Ʒ����
	@Override
	public int updateGift(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updateGift",items);
	}
	//�����޸���Ʒ�Ĵ�����
	@Override
	public int updatePriceGiftById(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updatePriceGiftById",items);
	}
	//ͨ��itemid�õ������Ʒ�������Ծɻ���
	@Override
	public List<BtoCItems> findYiJiuHuanXinByItemId(String itemid){
		return getSqlMapClientTemplate().queryForList("Items.findYiJiuHuanXinByItemId",itemid);
	}
	//�����޸Ķ���Ծɻ��µ���Ʒ
	@Override
	public int updateYiJiuStatusById(BtoCItems items){
		return getSqlMapClientTemplate().update("Items.updateYiJiuStatusById",items);
	}
	
	
	//��id�޸���Ʒ����
	@Override
	public int updatePriorityById(String updater,Date updatetime,int id,int priority){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemid", "P"+id);
		map.put("priority", priority);
		map.put("updater", updater);
		map.put("updatetime", new Date());
		return this.getSqlMapClientTemplate().update("Items.updatePriorityById",map);
	}
	//�޸���Ʒ����
	@Override
	public int updatePriority(String updater,Date updatetime,int downid,int downprio){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("downid", downid);
		map.put("downprio", downprio);
		map.put("updater", updater);
		map.put("updatetime", new Date());
		return this.getSqlMapClientTemplate().update("Items.updatePriority",map);
	}
	//�޸���Ʒ
	@Override
	public int update(BtoCItems items){
		return  this.getSqlMapClientTemplate().update("Items.updateItems",items);
	}
	//������Ʒid�޸���Ʒ����
	@Override
	public int updatePointById(BtoCItems items){
		return this.getSqlMapClientTemplate().update("Items.updatePointById",items);
	}
	//�޸���Ʒ�ķ���
	@Override
	public int updateItemsCatalog(BtoCItems items){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id",items.getId());
		int status=items.getStatus();
		int i=this.getSqlMapClientTemplate().update("Items.updateItemsByCatalog",items);
		
		//����ط���Ҫ�ģ��ϼ�״̬8���ԣ��Ƿ���Ҫ������Ʒ
		if((status&8)!=0 && i>=1)
			this.getSqlMapClientTemplate().update("Items.updateSnatchbuyPriceProcedure",map);
		return i;
	}
	//�޸���Ʒ����
	@Override
	public int updateTemplate(int itemid){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemid", itemid);
		return this.getSqlMapClientTemplate().update("Items.getProductTemplateById",map);
	}
	
	//ɾ����̬�������
	@Override
	public int deleteItemsAttributes(int id,String definitionname){
		String table ="btoc_"+definitionname+"_product";
		String sql="delete from "+table+" where iId="+id;
		return this.getSqlMapClientTemplate().delete("Items.deleteItemsAttributes",sql);
	}
	
	//ɾ����̬������ݣ������ض�̬�������
	@Override
	public Object deleteAttributesByItemid(String itemid,String definitionname){
		//�õ���̬������ݣ�Ŀ���ǽ�������ת��
        String table = "btoc_" + definitionname + "_product";
		String sql = "select * from " + table + " where itemid ='" + itemid+"'";
		Object object = this.getSqlMapClientTemplate().queryForObject("Items.findParamColumnsValue", sql);
		
		//ɾ����̬�������
		if(object!=null){
			sql="delete from "+table+" where itemid='"+itemid+"'";
			this.getSqlMapClientTemplate().delete("Items.deleteItemsAttributes",sql);
		}
		return object;
	}
	//ɾ����Ʒ
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
		
		// ͬ���µ��ն�ҳ��ʾ���������Ա�
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
		
		int esc = getSqlMapClientTemplate().update("Items.cancelAllShowstatus", maps);			// ȡ����ۡ������� showStatus = 0
		int set = getSqlMapClientTemplate().update("Items.showStatus", itemid);					// ���� showStatus = 1
		getSqlMapClientTemplate().update("Items.cancelSpecialStatus", itemid_Str.toString());	// ȡ��״̬4��specialStatus
		getSqlMapClientTemplate().update("Items.setSpecialStatus", itemid);						// ����״̬4��specialStatus
		
		return (esc>0&&set>0)?1:-1;
	}
	@Override
	public void escAllAndAddShowNowItem(int itemid) {
		List<Integer> ids = getSqlMapClientTemplate()
			.queryForList("Items.selectManyPriceAndSpecialAttrItemids", itemid); // ������������ն�ҳ��ʾ��Ʒ���ϼܵģ�
		
		updateShowManyPriceItem(itemid, ids); // ���¶�����
		
		Object catid = getItemCatalogId(itemid);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("catid", catid);
		maps.put("ids", ids);
		maps.put("itemid", itemid);
		
		escAllShowStatusToDynamicTable(maps);		// ȡ�����ж�̬���б�ҳ��ʾ
		setShowStatusToDynamicTable(maps);			// ���ö�̬���б�ҳ��ʾ status = 1
		setNewShowStatusItemidToDynamicTable(maps);	// ͬ����̬��itemid���µ��б�ҳ��ʾ��Ʒid��
	}
	@Override
	public void updateListPageItemsShow(int itemid) {
		escEndPageShow(itemid);	// ֹͣԭ��Ʒ�ն�ҳ��ʾ
		
		int specialNum = getEndPageShowNum(itemid);	// ��ǰ��Ʒ�����ж����Ʒ�����ն�ҳ��ʾ������
		
		if(specialNum < 1)
			setEndPageShow(itemid);	// ����ն�ҳ��ʾ����ͬ�����ݵ��������Ա�(��ȡ��)
	}
	@Override
	public List<Integer> getManyPriceAndSpecialAttrItemid(int itemid) {
		return getSqlMapClientTemplate()
			.queryForList("Items.selectManyPriceAndSpecialAttrItemids", itemid); // ���й����������Ʒ���ϼܵģ�
	}

	@Override
	public int countShelvesManyPriceItemNum(int itemid) {
		Object num = getSqlMapClientTemplate().queryForObject(
				"Items.countShelvesManyPriceItemNum", itemid); // �����Ʒ�������ϼܵģ�

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

		if(itemnum < 2){ // ���ֻɾ����ǰ�����Ժ�ֻʣ��һ�¹�������ȫ�����
			Object showid = getSqlMapClientTemplate().queryForObject("Items.getEndSpecialItem", productid);
			dynamicTablesAndMerchandiseUpdates(productid, showid);
			getSqlMapClientTemplate().update("Items.escSpecialstatusByProductid", productid); // ȡ��ʣ�µ���Ʒ״̬��2/1
			deleteAllSpecialAttrributeByItemid(productid);
		} else{
			// ������������

			Object shownum = getSqlMapClientTemplate().queryForObject("Items.getShowstatusItemNumByProductid", productid);
			
			if(Integer.parseInt(shownum.toString()) < 1){
				Object showid = getSqlMapClientTemplate().queryForObject("Items.getShowstatusItemByProductid", productid);
				if(showid == null)
					showid = getSqlMapClientTemplate().queryForObject("Items.getOldShowstatusItemByProductid", productid);
				
				dynamicTablesAndMerchandiseUpdates(productid, showid);
			}
		}
	}
	
	/** ���¶�̬�����Ʒ */
	private void dynamicTablesAndMerchandiseUpdates(int productid, Object showid) {
		Map<String, Object> args = new HashMap<String, Object>();

		Object catid = getItemCatalogId(Integer.parseInt(showid.toString()));
		Object itemid = getSqlMapClientTemplate().queryForObject("Items.getItemidByid", showid);
		
		args.put("catid", catid);
		args.put("showid", showid);
		args.put("itemid", itemid);
		args.put("productid", productid);
		
		getSqlMapClientTemplate().update("Items.showStatus", showid); // �����б�ҳ��ʾ
		getSqlMapClientTemplate().update("Items.escDynamicTable", args); // ȡ����̬�� status
		getSqlMapClientTemplate().update("Items.synchronousToDynamicTable", args); // ͬ������̬�� status
		getSqlMapClientTemplate().update("Items.synchronousIidToDynamicTable", args); // ͬ������̬�� status
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
			.queryForList("Items.getPromotionalGoodsByManyPriceAndSpecialAttr", id); // ���й����������Ʒ���ϼܴ����ģ�
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
				throw new Exception("ͬ��������Ʒ�Ĵ���״̬������ֵ���ࣨ�����������Ʒ�ж��ǰ̨��ʾ��");
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
