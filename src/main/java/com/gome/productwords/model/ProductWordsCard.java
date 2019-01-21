package com.gome.productwords.model;



import java.util.Date;

/**
 * create by wangkeqiang-ds
 * 商品词
 */
public class ProductWordsCard {
	private Integer id;
	private String productWordsBase;//商品词词根
	private String productWordsName;//商品词词名
	private Date createDate;
	private Date updateDate;
	private String creator;
	private String modifier;

	private Integer isDelete;//是否删除		0已删除  1未删除
	private Integer isEditor;//是否编辑		0已编辑  1未编辑
	private Integer isInvalid;//是否发布   0未发布  1已发布
	private String beginLetter;//首字母
	private Integer isEnable;//是否满足发布展示条件
	private Integer search_status;//是否进行补词
	private Integer search_amount;//搜索结果个数



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
