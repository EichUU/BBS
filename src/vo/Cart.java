package vo;

import java.net.URLEncoder;

public class Cart {	//장바구니 항목 하나의 정보를 저장하는 클래스
	
	private String image;
	private String kind;
	private int price;
	private int qty;
	private String encodingKind; 
	
	public String getEncodingKind() {	// 파라미터의 kind값의 한글 인코딩을 위한 코드
		try {
			encodingKind=URLEncoder.encode(kind, "UTF-8");
		}catch(Exception e) {
			System.out.println("인코딩에러 : "+e.getMessage());
		}
		return encodingKind;
	}
	
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
