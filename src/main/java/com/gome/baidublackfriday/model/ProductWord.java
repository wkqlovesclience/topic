package com.gome.baidublackfriday.model;

public class ProductWord {
     private Integer id;
     private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductWord(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ProductWord() {
		super();
	}
    
}
