package com.coo8.btoc.controller.action.admin.resource;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.coo8.btoc.business.interfaces.block.BlockManager;
import com.coo8.btoc.business.interfaces.items.IItemsManager;
import com.coo8.btoc.model.block.Block;
import com.coo8.btoc.model.items.BtoCItems;
import com.coo8.btoc.model.resource.ResourceCatalog;
/**
 * 获取静态页下某个数据源对应的商品集合
 * @author zhangxin
 *
 */
@Namespace("/admin/resource/product")
public class QueryResourceCatalogProductAction extends ResourceBaseAction {

	private static final long serialVersionUID = 4157510107837087742L;
	private IItemsManager itemsManager;
	private BlockManager blockManager;
	private List<ResourceCatalog> resultlist;
	/**
	 * 获取某静态页的某个块下的商品list
	 */
	@Action(value="list", results={
			@Result(name=SUCCESS, location="/jsp/admin/resource/admin_resource_catalog_product_list.jsp", type="dispatcher"),
			@Result(name=ERROR, location="/jsp/admin/common/common_error_text.jsp", type="dispatcher")
	})
	@Override
	public String execute() {
        ResourceCatalog resourceCatalog = new ResourceCatalog();
        resourceCatalog.setHtmlId(staticHtmlId);
        resourceCatalog.setResourceId(resourceId);
        resourceCatalog.setType(ResourceCatalog.HEAD_OFFICE_TYPE);
        resourceCatalog.setStatus(ResourceCatalog.ENABLED);
        //默认产品位列表
	    List<ResourceCatalog> positions = resourceManager.queryResourceCatalog(resourceCatalog);
	    if(positions.isEmpty()){
	        //块下没有商品时，添加8个默认产品位
	        ResourceCatalog insertO = new ResourceCatalog();
	        insertO.setResourceId(resourceId);
	        insertO.setHtmlId(staticHtmlId);
	        insertO.setCreator(getAdminSessionOperator());
	        insertO.setType(ResourceCatalog.HEAD_OFFICE_TYPE);
	        insertO.setOpen(ResourceCatalog.CLOSE);
	        insertO.setCompId(HEADOFFICEID);
	        insertO.setStatus(ResourceCatalog.ENABLED);
	        if(dateNum == null || dateNum == 0){
	            List<Block> block = blockManager.queryBlockWithHtmlid(staticHtmlId, resourceId);
	            if(!block.isEmpty()){
	                dateNum = block.get(0).getDataNum();
	            }
	        }
	        resourceManager.insertPositions(insertO,dateNum);
	        positions = resourceManager.queryResourceCatalog(resourceCatalog);
	    }
	    String opercompid = getAdminSessionCOMPID();//后台操作员所属公司id
	    if(opercompid == null || opercompid.trim().length() <= 0){
	        return ERROR;
	    }
	    resultlist = new ArrayList<ResourceCatalog>();//结果集
	    for(int i=0 ;i<positions.size();i++){
	        ResourceCatalog result = null;//结果
	        String   productid = "";
	        int opentype = positions.get(i).getOpen() == null ? ResourceCatalog.CLOSE:positions.get(i).getOpen();
	        if(opentype == ResourceCatalog.CLOSE || HEADOFFICEID.equals(opercompid)){
                //产品位关闭或者操作员所属公司为总部时
                productid = positions.get(i).getProductId() == null ?"":positions.get(i).getProductId().toString().trim();
                result = positions.get(i);
            }else{
                ResourceCatalog search = new ResourceCatalog();
                search.setHtmlId(staticHtmlId);
                search.setResourceId(resourceId);
                search.setStatus(ResourceCatalog.ENABLED);
                search.setCompId(opercompid);
                search.setPosition(positions.get(i).getPosition());
                search.setOpen(ResourceCatalog.OPEN);
                List<ResourceCatalog> list = resourceManager.queryResourceCatalog(search);
                if(list.isEmpty() ){
                    //分公司未设置该产品位时，商品ID为“”;
                    productid = "";
                    result = search;
                }else{
                    productid = list.get(0).getProductId() == null ?"":list.get(0).getProductId().toString().trim();
                    result = list.get(0);
                }
            }
	        BtoCItems item = null;//结果里的商品对象
	        if(!"".equals(productid)){
	            item = itemsManager.findPriceById(Integer.valueOf(productid));
	        }
	        result.setItem(item);
	        resultlist.add(result);
	    }
	    return SUCCESS;
	}

    public void setItemsManager(IItemsManager itemsManager) {
		this.itemsManager = itemsManager;
	}

	public List<ResourceCatalog> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<ResourceCatalog> resultlist) {
		this.resultlist = resultlist;
	}

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public void setBlockManager(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

}