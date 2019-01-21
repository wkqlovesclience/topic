package com.coo8.item.controller.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coo8.btoc.util.pages.PaginatedList;
import com.coo8.item.business.interfaces.IItemManager;
import com.coo8.item.model.ItemCategory;
import com.coo8.item.model.ItemFloor;
import com.coo8.topic.controller.action.BaseAction;

/**
 * 
 *
 * @author jiaziwei
 */
@Namespace("/Item")
public class ItemAction extends BaseAction {

	private static final long serialVersionUID = -2735013644095139314L;

	private static Logger logger = LoggerFactory.getLogger(ItemAction.class);

	private IItemManager itemManager;

	private Integer pageNumber = 1;

	private Integer pageSize = 10;

	private PaginatedList<ItemFloor> listFloor;

	private PaginatedList<ItemCategory> listCategroy;

	private ItemFloor floor;

	private ItemCategory category;

	private String ids;

	@Action(value = "listFloors", results = { @Result(name = "success", location = "/jsp/item/floorlist.jsp") })
	public String listFloors() {
		logger.info("Floor list start!");
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("pageNumber", pageNumber);
		search.put("pageSize", pageSize);
		search.put("sortColumns", "is_valid desc,priority desc");
		logger.info("Floor list 查询参数：" + search);
		listFloor = itemManager.findByMap(search);
		logger.info("Floor list end!");
		return "success";
	}

	@Action(value = "createFloor", results = { @Result(name = "success", location = "/jsp/item/floor.jsp") })
	public String createFloor() {
		logger.info("Floor create start!");
		this.floor = null;
		logger.info("Floor create end!");
		return "success";
	}

	@Action(value = "editFloor", results = { @Result(name = "success", location = "/jsp/item/floor.jsp") })
	public String edit() {
		logger.info("Floor edit start!");
		if (floor != null && floor.getId() > 0) {
			this.floor = itemManager.getById(floor.getId());
		}
		logger.info("Floor edit end!");
		return "success";
	}

	@Action(value = "saveFloor", results = { @Result(name = "success", type = "redirect", location = "/Item/listFloors.action") })
	public String saveFloor() {
		logger.info("Floor save start!");
		if (floor != null && floor.getId() > 0) {
			itemManager.updateFloor(floor); // 更新
		} else {
			itemManager.saveFloor(floor); // 插入
		}
		logger.info("Floor save end!");
		return "success";
	}

	private HashSet<Integer> splitIds(String str, String sign) {
		String s[] = str.split(sign);
		List<Integer> newids = new ArrayList<Integer>();
		for (int i = 0; i < s.length; i++) {
			if (!"".equals(s[i])) {
				newids.add(Integer.parseInt(s[i]));
			}
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		if (!newids.isEmpty()) {
			hs.addAll(newids);
		}
		return hs;
	}

	@Action(value = "deleteFloors", results = { @Result(name = "success", type = "redirect", location = "/Item/listFloors.action") })
	public String deleteFloors() {
		logger.info("Floor delete start!");
		if (ids != null && !"".equals(ids)) {
			for (Integer id : splitIds(ids, ";")) {
				logger.info("Floor delete 删除数据id：" + id);
				itemManager.deleteFloor(id);
			}
		}
		logger.info("Floor delete end!");
		return "success";
	}

	/* 查询楼层下面的分类 */
	@Action(value = "listCategories", results = { @Result(name = "success", location = "/jsp/item/categorylist.jsp") })
	public String listCategoryList() {
		logger.info("CategoryList list start!");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", pageSize);
		paramMap.put("sortColumns", "is_valid desc,priority desc");
		Integer qtitlePram = floor.getId();
		if (qtitlePram != 0) {
			paramMap.put("floorId", qtitlePram);
		} else {
			paramMap.put("floorId", qtitlePram);
		}

		listCategroy = itemManager.selectAllCategoryByfloorid(paramMap);
		logger.info("CategoryList list end!");
		return SUCCESS;
	}

	/* 删除分类 */
	@Action(value = "deleteCategories", results = { @Result(name = "success", type = "redirect", location = "/Item/listCategories.action?floor.Id=${floor.getId()}") })
	public String deleteCategories() {
		logger.info("Category delete start!");
		if (ids != null && !"".equals(ids)) {
			for (Integer id : splitIds(ids, ";")) {
				logger.info("Category delete 删除数据id：" + id);
				itemManager.deleteByCategoryById(id);
			}
		}
		logger.info("Category delete end!");
		return "success";
	}

	/* 保存楼层分类 */
	@Action(value = "saveCategory", results = { @Result(name = "success", type = "redirect", location = "/Item/listCategories.action?floor.Id=${floor.getId()}"),
			@Result(name = "listFloors", type = "redirect", location = "/Item/listFloors.action") })
	public String saveCategory() {
		if (floor == null || (floor = itemManager.getById(floor.getId())) == null) {
			return "listFloors";
		}
		logger.info("Category save start!");
		if (category.getId() > 0) {
			itemManager.updateItemCategoryById(category); // 更新
		} else {
			category.setFloorId(floor.getId());
			itemManager.save(category); // 插入
		}
		logger.info("Category save end!");
		return "success";
	}

	/* 添加楼层分类 */
	@Action(value = "createCategory", results = { @Result(name = "success", location = "/jsp/item/category.jsp"),
			@Result(name = "listFloors", type = "redirect", location = "/Item/listFloors.action") })
	public String addcategory() {
		if (floor == null || floor.getId() <= 0) {
			return "listFloors";
		}
		logger.info("category create start!");
		category = null;
		logger.info("category create end!");
		return "success";
	}

	/* 编辑分类 */
	@Action(value = "editCategory", results = { @Result(name = "success", location = "/jsp/item/category.jsp"),
			@Result(name = "listFloors", type = "redirect", location = "/Item/listFloors.action") })
	public String editcategory() {
		if (category == null || category.getId() <= 0 || (category = itemManager.selectCategoryById(category.getId())) == null) {
			return "listFloors";
		}
		if (category.getFloorId() <= 0 || (floor = itemManager.getById(category.getFloorId())) == null) {
			return "listFloors";
		}
		logger.info("Category edit start!");
		logger.info("Category edit end!");
		return "success";
	}

	public IItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(IItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PaginatedList<ItemFloor> getListFloor() {
		return listFloor;
	}

	public void setListFloor(PaginatedList<ItemFloor> listFloor) {
		this.listFloor = listFloor;
	}

	public PaginatedList<ItemCategory> getListCategroy() {
		return listCategroy;
	}

	public void setListCategroy(PaginatedList<ItemCategory> listCategroy) {
		this.listCategroy = listCategroy;
	}

	public ItemFloor getFloor() {
		return floor;
	}

	public void setFloor(ItemFloor floor) {
		this.floor = floor;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
