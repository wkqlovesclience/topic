package com.coo8.btoc.business.impl.queue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.btoc.model.queue.BlockQueue;
import com.coo8.btoc.model.queue.ProductQueue;
import com.coo8.btoc.persistence.interfaces.queue.QueueDao;

public class QueueManagerImpl implements QueueManager {

    private QueueDao queueDao;

    private static final int STATUS = 0;

    private static final int PRIORITY = 0;

    private static final String SITE_GOME = "gome";

    @SuppressWarnings("unused")
    private static final int BLOCK_RTYPE_COMMON = 2;//普通块，位于pblock

    @SuppressWarnings("unused")
    private static final int BLOCK_RTYPE_STATIC = 3;//静态块，位于sblock

    private static final int BLOCK_TYPE_AUTO = 0;

    public QueueDao getQueueDao() {
        return queueDao;
    }

    public void setQueueDao(QueueDao queueDao) {
        this.queueDao = queueDao;
    }

    @Override
    public void insertBlockQueue(BlockQueue blockQueue) {
        queueDao.insertBlockQueue(blockQueue);
    }

    @Override
    public void insertProductQueue(ProductQueue productQueue) {
        queueDao.insertProductQueue(productQueue);
    }

    @Override
    public List<Integer> getRelatedBlockIdList(Map<String, Object> map) {
        return queueDao.getRelatedBlockIdList(map);
    }

    @Override
    public void publish(int rfid, int templateId, int product_type, int product_rtype, int block_rtype) {
        ProductQueue proQueue = new ProductQueue();
        proQueue.setRfid(rfid);
        proQueue.setStatus(STATUS);
        proQueue.setType(product_type);
        proQueue.setPriority(PRIORITY);
        proQueue.setInputDate(new Date());
        proQueue.setRtype(product_rtype);
        proQueue.setTemplateId(templateId);
        proQueue.setSite(SITE_GOME);

        queueDao.insertProductQueue(proQueue);

        publishBolck(rfid, templateId, product_type, block_rtype);
    }

    private void publishBolck(int rfid, int templateId, int product_type, int block_rtype) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("templateId", templateId);
        map.put("status", STATUS);
        map.put("type", BLOCK_TYPE_AUTO);
        List<Integer> bolckIdList = queueDao.getRelatedBlockIdList(map);

        Integer isTestFlag = null;
        if (product_type == 7) {
            isTestFlag = 1;
        }

        for (Integer blockId : bolckIdList) {
            BlockQueue blockQueue = new BlockQueue();
            blockQueue.setTemplateId(templateId);
            blockQueue.setBlockId(blockId);
            blockQueue.setRfid(rfid);
            blockQueue.setRtype(block_rtype);
            blockQueue.setPriority(PRIORITY);
            blockQueue.setInputDate(new Date());
            blockQueue.setStatus(STATUS);
            blockQueue.setSite(SITE_GOME);
            blockQueue.setParade1(isTestFlag);

            queueDao.insertBlockQueue(blockQueue);
        }

    }

}
