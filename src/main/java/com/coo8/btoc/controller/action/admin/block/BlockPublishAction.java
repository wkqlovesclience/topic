package com.coo8.btoc.controller.action.admin.block;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.helpler.appendapply.AppendApplyConfig;
import com.coo8.btoc.business.interfaces.block.BlockManager;
import com.coo8.btoc.business.interfaces.items.IItemsManager;
import com.coo8.btoc.config.ReloadableConfig;
/**
 * 块发布处理后台
 * @author zhangxin
 *
 */
@Namespace("/admin/block")
public class BlockPublishAction extends BlockBaseAction{
    private static final long serialVersionUID = 455016112941459233L;
    protected  IItemsManager itemsManager;    // 商品service接口
	private  static Logger logger = LoggerFactory.getLogger(BlockPublishAction.class);
    private ExecutorService executes = Executors.newFixedThreadPool(AppendApplyConfig.getPublishThreadPoolSize());
    private int pagesize = 100;
    protected Integer blockid;//要发布的块ID
    protected Integer templateid;//要发布的模板ID
    /**
     * 发布商品块
     * @return
     */
    @Action("publishItemBlock")
    public void publishItemBlock() {
      
        try {
            HttpServletResponse hr= ServletActionContext.getResponse();
            hr.setContentType("text/xml;charset=GBK");
            PrintWriter out = hr.getWriter();
            if(blockid == null || templateid == null){
                out.print( "块id或者模板id不存在！");
                return;
            }
            if(templateid != 2){
                out.print( "该块不属于商品模板中的块！");
                return;
            }
            String adminname=getAdminSessionOperator();
            int count = itemsManager.queryOnlineItemcount();
            logger.info("==="+adminname+"====发布商品块=共"+count+"个======");
            int num = (int)Math.ceil((double) count/pagesize);
          
            for(int i=1; i<=num; i++)
            {
                try {
                    @SuppressWarnings("rawtypes")
                    Future f = executes.submit(new BlockThread(this.blockManager, (i-1)*pagesize, blockid, templateid));
                    if(f.get()==null){
                        if(i==num)
                        {
                            out.print("块"+blockid+"发布完成");
                        }
                        logger.info("第" + i +"页"+"商品块"+blockid+"发布成功");
                    }else{
                        if(i==num)
                        {
                            out.print("块"+blockid+"发布完成");
                        }
                        logger.info("第" + i +"页"+"商品块"+blockid+"发布失败");
                    }
                } catch (Exception e) {
                          
                	logger.error(e.getMessage(), e);

                } 
            }
        } catch (Exception e) {
            
        	logger.error(e.getMessage(), e);
        }
        
    }
  
    private class BlockThread implements Runnable {
        private BlockManager blockManager;//商品业务接口
        private int start;
        private Integer blockid;
        private Integer templateid;
       
        public BlockThread(BlockManager blockManager,  int i, Integer blockid, Integer templateid){
            this.blockManager=blockManager;
            this.start=i;
            this.blockid = blockid;
            this.templateid = templateid;
        }
        
        @Override
        public void run() {
            if(start >= 0){
                try{
                    blockManager.insertBlockQueue(start, pagesize, blockid, templateid);
                } catch (Exception e) {   
                	logger.error(e.getMessage(), e);
                }
            }
        }

    }
    public Integer getBlockid() {
        return blockid;
    }
    public void setBlockid(Integer blockid) {
        this.blockid = blockid;
    }
    public Integer getTemplateid() {
        return templateid;
    }
    public void setTemplateid(Integer templateid) {
        this.templateid = templateid;
    }
    public IItemsManager getItemsManager() {
        return itemsManager;
    }
    public void setItemsManager(IItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }
}
