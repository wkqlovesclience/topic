package com.gome.productwords.model;



import java.util.Date;

/**
 * create by wangkeqiang-ds
 * ��Ʒ��
 */
public class ProductWordsCard {
	private Integer id;
	private String productWordsBase;//��Ʒ�ʴʸ�
	private String productWordsName;//��Ʒ�ʴ���
	private Date createDate;
	private Date updateDate;
	private String creator;
	private String modifier;

	private Integer isDelete;//�Ƿ�ɾ��		0��ɾ��  1δɾ��
	private Integer isEditor;//�Ƿ�༭		0�ѱ༭  1δ�༭
	private Integer isInvalid;//�Ƿ񷢲�   0δ����  1�ѷ���
	private String beginLetter;//����ĸ
	private Integer isEnable;//�Ƿ����㷢��չʾ����
	private Integer search_status;//�Ƿ���в���
	private Integer search_amount;//�����������



	public ProductWordsCard() {
	}

	public ProductWordsCard(Integer id, String productWordsBase, String productWordsName, Date createDate, Date updateDate, String creator, String modifier, Integer isDelete, Integer isEditor, Integer isInvalid, String beginLetter, Integer isEnable, Integer search_status) {
		this.id = id;
		this.productWordsBase = productWordsBase;
		this.productWordsName = productWordsName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.creator = creator;
		this.modifier = modifier;
		this.isDelete = isDelete;
		this.isEditor = isEditor;
		this.isInvalid = isInvalid;
		this.beginLetter = beginLetter;
		this.isEnable = isEnable;
		this.search_status = search_status;
	}

    public ProductWordsCard(Integer id, String productWordsBase, String productWordsName, Date createDate, Date updateDate, String creator, String modifier, Integer isDelete, Integer isEditor, Integer isInvalid, String beginLetter, Integer isEnable, Integer search_status, Integer search_amount) {
        this.id = id;
        this.productWordsBase = productWordsBase;
        this.productWordsName = productWordsName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.creator = creator;
        this.modifier = modifier;
        this.isDelete = isDelete;
        this.isEditor = isEditor;
        this.isInvalid = isInvalid;
        this.beginLetter = beginLetter;
        this.isEnable = isEnable;
        this.search_status = search_status;
        this.search_amount = search_amount;
    }

    public Integer getSearch_amount() {
        return search_amount;
    }

    public void setSearch_amount(Integer search_amount) {
        this.search_amount = search_amount;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductWordsBase() {
		return productWordsBase;
	}

	public void setProductWordsBase(String productWordsBase) {
		this.productWordsBase = productWordsBase;
	}

	public String getProductWordsName() {
		return productWordsName;
	}

	public void setProductWordsName(String productWordsName) {
		this.productWordsName = productWordsName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsEditor() {
		return isEditor;
	}

	public void setIsEditor(Integer isEditor) {
		this.isEditor = isEditor;
	}

	public Integer getIsInvalid() {
		return isInvalid;
	}

	public void setIsInvalid(Integer isInvalid) {
		this.isInvalid = isInvalid;
	}

	public String getBeginLetter() {
		return beginLetter;
	}

	public void setBeginLetter(String beginLetter) {
		this.beginLetter = beginLetter;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getSearch_status() {
		return search_status;
	}

	public void setSearch_status(Integer search_status) {
		this.search_status = search_status;
	}

	@Override
	public String toString() {
		return "ProductWordsCard{" +
				"id=" + id +
				", productWordsBase='" + productWordsBase + '\'' +
				", productWordsName='" + productWordsName + '\'' +
				", createDate=" + createDate +
				", updateDate=" + updateDate +
				", creator='" + creator + '\'' +
				", modifier='" + modifier + '\'' +
				", isDelete=" + isDelete +
				", isEditor=" + isEditor +
				", isInvalid=" + isInvalid +
				", beginLetter='" + beginLetter + '\'' +
				", isEnable=" + isEnable +
				", search_status=" + search_status +
				'}';
	}
}
