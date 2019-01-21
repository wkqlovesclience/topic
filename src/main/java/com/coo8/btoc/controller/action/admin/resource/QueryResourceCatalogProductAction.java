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
 * ��ȡ��̬ҳ��ĳ������Դ��Ӧ����Ʒ����
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
	 * ��ȡĳ��̬ҳ��ĳ�����µ���Ʒlist
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
        //Ĭ�ϲ�Ʒλ�б�
	    List<ResourceCatalog> positions = resourceManager.queryResourceCatalog(resourceCatalog);
	    if(positions.isEmpty()){
	        //����û����Ʒʱ�����8��Ĭ�ϲ�Ʒλ
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
	    String opercompid = getAdminSessionCOMPID();//��̨����Ա������˾id
	    if(opercompid == null || opercompid.trim().length() <= 0){
	        return ERROR;
	    }
	    resultlist = new ArrayList<ResourceCatalog>();//�����
	    for(int i=0 ;i<positions.size();i++){
	        ResourceCatalog result = null;//���
	        String   productid = "";
	        int opentype = positions.get(i).getOpen() == null ? ResourceCatalog.CLOSE:positions.get(i).getOpen();
	        if(opentype == ResourceCatalog.CLOSE || HEADOFFICEID.equals(opercompid)){
                //��Ʒλ�رջ��߲���Ա������˾Ϊ�ܲ�ʱ
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
                    //�ֹ�˾δ���øò�Ʒλʱ����ƷIDΪ����;
                    productid = "";
                    result = search;
                }else{
                    productid = list.get(0).getProductId() == null ?"":list.get(0).getProductId().toString().trim();
                    result = list.get(0);
                }
            }
	        BtoCItems item = null;//��������Ʒ����
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