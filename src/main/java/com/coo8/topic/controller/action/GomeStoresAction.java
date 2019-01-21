package com.coo8.topic.controller.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.business.interfaces.queue.QueueManager;
import com.coo8.common.diamond.PropertiesUtils;
import com.coo8.common.utils.Chinese2PinyinUtil;
import com.coo8.common.utils.ConstantUtil;
import com.coo8.topic.business.interfaces.IGomeStoresManager;
import com.coo8.topic.model.gomeStores.StoresCoordinate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Namespace("/gomeStores")
public class GomeStoresAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(GomeStoresAction.class);
	private IGomeStoresManager gomeStoresManager;
	private QueueManager queueManager;

	private Integer type; // 1:����Ԥ��,0:ֱ�ӷ���
	private Boolean isAll; // �Ƿ�ȫ������
	private Integer id;
	private Integer startId;
	private Integer endId;

	private static final int template_id = PropertiesUtils.getIntValueByKey("template_id"); // �����ŵ�ģ��Id
	private static final int task_type = PropertiesUtils.getIntValueByKey("task_type"); // ��������TaskType
	private static final int block_rtype = PropertiesUtils.getIntValueByKey("block_rtype"); // ������
	private static final int wap_template_id = PropertiesUtils.getIntValueByKey("wap_template_id");
	private static final int wap_task_type = PropertiesUtils.getIntValueByKey("wap_task_type");

	private boolean isEmpty(Object obj) {
		if (obj == null || "".equals(obj.toString())) {
			return true;
		}
		return false;
	}

	private boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/jsp/gomeStores/list.jsp") })
	public String list() {
		logger.info("gomeStores list start!");
		logger.info("gomeStores list end!");
		return SUCCESS;
	}

	@Action(value = "publish")
	public void publish() {
		logger.info("gomeStores publish start!");

		logger.info("gomeStores publish ��������type:" + type);
		if (isEmpty(type)) {
			writeAjaxStr("error");
			return;
		}
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (type == 1) {
			// ����Ԥ��
			if (isNotEmpty(isAll) && isAll == true) {
				// ȫ����������Ԥ��
				List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
				for (Integer itemId : idsList) {
					queueManager.publish(itemId, template_id, 7, task_type, block_rtype);
				}
			} else if (isNotEmpty(isAll) && isAll == false) {
				if (isNotEmpty(id)) {
					// ��������Ԥ��
					if (gomeStoresManager.isStoresExist(id)) {
						queueManager.publish(id, template_id, 7, task_type, block_rtype);
						writeAjaxStr("success:" + ConstantUtil.DOMAIN_GOME_BASEURL + "/stores/"
								+ gomeStoresManager.getPinyinCityName(id).toLowerCase() + "/test/" + id + ".html");
						return;
					} else {
						writeAjaxStr("error");
						return;
					}
				} else if (isNotEmpty(startId) && isNotEmpty(endId)) {
					// ������������Ԥ��
					searchMap.put("startId", startId);
					searchMap.put("endId", endId);
					List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
					for (Integer itemId : idsList) {
						queueManager.publish(itemId, template_id, 7, task_type, block_rtype);
					}
				}
			}
			writeAjaxStr("success");
			return;
		} else if (type == 0) {
			// ����
			if (isNotEmpty(isAll) && isAll == true) {
				// ȫ����������
				List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
				for (Integer itemId : idsList) {
					queueManager.publish(itemId, template_id, 0, task_type, block_rtype);
				}
			} else if (isNotEmpty(isAll) && isAll == false) {
				if (isNotEmpty(id)) {
					// ��������
					if (gomeStoresManager.isStoresExist(id)) {
						queueManager.publish(id, template_id, 0, task_type, block_rtype);
						writeAjaxStr("success:" + ConstantUtil.DOMAIN_GOME_BASEURL + "/stores/"
								+ gomeStoresManager.getPinyinCityName(id).toLowerCase() + "/" + id + ".html");
						return;
					} else {
						writeAjaxStr("error");
						return;
					}
				} else if (isNotEmpty(startId) && isNotEmpty(endId)) {
					// ���䷢��
					searchMap.put("startId", startId);
					searchMap.put("endId", endId);
					List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
					for (Integer itemId : idsList) {
						queueManager.publish(itemId, template_id, 0, task_type, block_rtype);
					}
				}
			}
			writeAjaxStr("success");
			logger.info("gomeStores publish end!");
			return;
		}
	}

	@Action(value = "wapPublish")
	public void wapPublish() {
		logger.info("gomeStores wapPublish start!");

		logger.info("gomeStores wapPublish ��������type��" + type);
		if (isEmpty(type)) {
			writeAjaxStr("error");
			return;
		}
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (type == 1) {
			// ����Ԥ��
			if (isNotEmpty(isAll) && isAll == true) {
				// ȫ����������Ԥ��
				List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
				for (Integer itemId : idsList) {
					queueManager.publish(itemId, wap_template_id, 7, wap_task_type, block_rtype);
				}
			} else if (isNotEmpty(isAll) && isAll == false) {
				if (isNotEmpty(id)) {
					// ��������Ԥ��
					if (gomeStoresManager.isStoresExist(id)) {
						queueManager.publish(id, wap_template_id, 7, wap_task_type, block_rtype);
						writeAjaxStr("success:" + ConstantUtil.DOMAIN_WAP_GOME_BASEURL + "/stores/"
								+ gomeStoresManager.getPinyinCityName(id).toLowerCase() + "/test/" + id + ".html");
						return;
					} else {
						writeAjaxStr("error");
						return;
					}
				} else if (isNotEmpty(startId) && isNotEmpty(endId)) {
					// ������������Ԥ��
					searchMap.put("startId", startId);
					searchMap.put("endId", endId);
					List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
					for (Integer itemId : idsList) {
						queueManager.publish(itemId, wap_template_id, 7, wap_task_type, block_rtype);
					}
				}
			}
			writeAjaxStr("success");
			return;
		} else if (type == 0) {
			// ����
			if (isNotEmpty(isAll) && isAll == true) {
				// ȫ����������
				List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
				for (Integer itemId : idsList) {
					queueManager.publish(itemId, wap_template_id, 0, wap_task_type, block_rtype);
				}
			} else if (isNotEmpty(isAll) && isAll == false) {
				if (isNotEmpty(id)) {
					// ��������
					if (gomeStoresManager.isStoresExist(id)) {
						queueManager.publish(id, wap_template_id, 0, wap_task_type, block_rtype);
						writeAjaxStr("success:" + ConstantUtil.DOMAIN_WAP_GOME_BASEURL + "/stores/"
								+ gomeStoresManager.getPinyinCityName(id).toLowerCase() + "/" + id + ".html");
						return;
					} else {
						writeAjaxStr("error");
						return;
					}
				} else if (isNotEmpty(startId) && isNotEmpty(endId)) {
					// ���䷢��
					searchMap.put("startId", startId);
					searchMap.put("endId", endId);
					List<Integer> idsList = gomeStoresManager.getStoresCoorIds(searchMap);
					for (Integer itemId : idsList) {
						queueManager.publish(itemId, wap_template_id, 0, wap_task_type, block_rtype);
					}
				}
			}
			writeAjaxStr("success");

			logger.info("gomeStores wapPublish end!");
			return;
		}
	}

	@Action(value = "exportBasicInfo")
	public void exportBasicInfo() {
		logger.info("gomeStores exportBasicInfo start!");
		WritableWorkbook workbook = null;
		OutputStream os = null;
		HttpServletResponse response = getResponse();

		String fileName = "SEO�����ŵ������Ϣ";
		try {
			String fname = new String(fileName.getBytes("gbk"), "ISO-8859-1");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");

			os = response.getOutputStream();
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("�ŵ���Ϣ", 0);
			String[] columns = { "�ŵ�Id", "����", "��ַ", "����Id", "����Name", "����ƴ��", "�ŵ�URL" };
			for (int i = 0; i < columns.length; i++) {
				Label titleCol = new Label(i, 0, columns[i]);
				sheet.addCell(titleCol);
			}

			Map<String, Object> map = new HashMap<String, Object>();
			List<StoresCoordinate> storesCoorList = gomeStoresManager.getStoresCoordinateList(map);
			int counter = 1;
			for (StoresCoordinate storesCoor : storesCoorList) {
				Integer nid = storesCoor.getId();
				String name = storesCoor.getName();
				String address = storesCoor.getAddress();

				String cityId = storesCoor.getCityId();
				String cityName = gomeStoresManager.getDivisionName(cityId);
				String dealCityName = isEmpty(cityName) ? ""
						: Chinese2PinyinUtil.parseChinese(cityName.replace("��", "")).toLowerCase();


				String accessURL = ConstantUtil.DOMAIN_GOME_BASEURL + "/stores/" + dealCityName + "/" + nid + ".html";

				Label label_id = new Label(0, counter, String.valueOf(nid));
				Label label_name = new Label(1, counter, name);
				Label label_address = new Label(2, counter, address);
				Label label_cityId = new Label(3, counter, cityId);
				Label label_cityName = new Label(4, counter, cityName);
				Label label_dealCityName = new Label(5, counter, dealCityName);


				Label label_accessURL = new Label(6, counter, accessURL);

				sheet.addCell(label_id);
				sheet.addCell(label_name);
				sheet.addCell(label_address);
				sheet.addCell(label_cityId);
				sheet.addCell(label_cityName);
				sheet.addCell(label_dealCityName);


				sheet.addCell(label_accessURL);

				counter++;
			}

			workbook.write();
			response.flushBuffer();
			workbook.close();
			os.flush();
			os.close();

		} catch (UnsupportedEncodingException e) {
			logger.error("gomeStores exportBasicInfo UnsupportedEncodingException�쳣��" + e.getMessage(),e);
		} catch (IOException e) {
			logger.error("gomeStores exportBasicInfo IOException�쳣��" + e.getMessage(),e);
		} catch (RowsExceededException e) {
			logger.error("gomeStores exportBasicInfo RowsExceededException�쳣��" + e.getMessage(),e);
		} catch (WriteException e) {
			logger.error("gomeStores exportBasicInfo WriteException�쳣��" + e.getMessage(),e);
		}

		logger.info("gomeStores exportBasicInfo end!");
	}

	public IGomeStoresManager getGomeStoresManager() {
		return gomeStoresManager;
	}

	public void setGomeStoresManager(IGomeStoresManager gomeStoresManager) {
		this.gomeStoresManager = gomeStoresManager;
	}

	public QueueManager getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(QueueManager queueManager) {
		this.queueManager = queueManager;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStartId() {
		return startId;
	}

	public void setStartId(Integer startId) {
		this.startId = startId;
	}

	public Integer getEndId() {
		return endId;
	}

	public void setEndId(Integer endId) {
		this.endId = endId;
	}

	public Boolean getIsAll() {
		return isAll;
	}

	public void setIsAll(Boolean isAll) {
		this.isAll = isAll;
	}

}
