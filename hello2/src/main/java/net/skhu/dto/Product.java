package net.skhu.dto;

//VO, DTO 역할을 하는 클래스
//제품의 이름과 가격 데이터를 넣어서 전달하기 위한 클래스
//데이터를 채워서 전달하기 위한 목적의 클래스를 DTO라고 부른다.
public class Product {

	String name;
	int unitCost;

	//위 멤버변수값 모두 초기화할 생성자 자동구현
	//alt + shift + s  o
	public Product(String name, int unitCost) {
		super();
		this.name = name;
		this.unitCost = unitCost;
	}
	//getter,setter메소드 자동구현
	//alt + shift +  s    r
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}








}
