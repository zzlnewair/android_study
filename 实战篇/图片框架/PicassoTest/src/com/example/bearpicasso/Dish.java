package com.example.bearpicasso;

//��ʾ���ࣨ����������߲ˡ���Ʒ������ȣ�
public class Dish {

	private String imgUrl; // ͼƬ��ַ
	private String name; // ����
	private String price; // �˼�
	
	public Dish(String imgUrl, String name, String price) {
		this.imgUrl = imgUrl;
		this.name = name;
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
