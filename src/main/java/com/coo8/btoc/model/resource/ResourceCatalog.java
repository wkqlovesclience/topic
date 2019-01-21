package com.coo8.btoc.model.resource;

import java.util.Date;

import com.coo8.btoc.model.BaseModel;
import com.coo8.btoc.model.items.BtoCItems;

public class ResourceCatalog extends BaseModel {

	private static final long serialVersionUID = 762903938474327062L;
	public static final int ENABLED = 0;
	public static final int DISABLED = 1;
	public static final int BRANCH_OFFICE_TYPE = 2;//�ֹ�˾������Ʒ
	public static final int HEAD_OFFICE_TYPE = 1;//�ܹ�˾������Ʒ
	public static final int OPEN = 1;//�Էֹ�˾���Ų�Ʒλ
	public static final int CLOSE = 0;//���Էֹ�˾���Ų�Ʒλ
	private Integer id;
	private Integer resourceId;//����ԴID
	private Integer htmlId;//��̬ҳID
	private Integer productId;	//��ƷID
	private Integer position;//��Ʒλ���
	private Integer positionChange;// �ı��Ĳ�Ʒλ��ţ���������ʱʹ��
	private Integer open;	//�Ƿ񿪷Ų�Ʒλ0:δ���ţ�1������
	private String compId;	//�ֹ�˾ID
	private String cityIds;	//����ID���� �ã��ָ���
	private Integer type;//��Ʒ���ͣ�1�ܹ�˾���ã�2�ֹ�˾����
	private Integer status;//��Ʒ״̬��0��Ч��1ʧЧ
	private String creator;
	private String updator;
	private Date createdTime;
	private Date updatedTime;
	private BtoCItems item;//ǰ̨ҳ��ʹ��
	
	public boolean isEnabled() {
		return status != null && status == ENABLED;
	}
	
	public boolean isDisabled() {
		return status != null && status == DISABLED;
	}
	
	public boolean isBranch() {
		return type != null && type == BRANCH_OFFICE_TYPE;
	}
	
	public boolean isHead() {
		return type != null && type == HEAD_OFFICE_TYPE;
	}
	
	public static boolean isBranch(Integer type) {
	    return type != null && type == BRANCH_OFFICE_TYPE;
	}
	
	public static boolean isHead(Integer type) {
	    return type != null && type == HEAD_OFFICE_TYPE;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(Integer htmlId) {
        this.htmlId = htmlId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public BtoCItems getItem() {
        return item;
    }

    public void setItem(BtoCItems item) {
        this.item = item;
    }
    
	public Integer getPositionChange() {
		return positionChange;
	}

	public void setPositionChange(Integer positionChange) {
		this.positionChange = positionChange;
	}
	
	
}