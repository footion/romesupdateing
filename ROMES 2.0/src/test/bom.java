//package test;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table
//public class bom {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private int id;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Product product;
//
//	@JoinColumn(name = "meterial")
//	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
//	private Product meterial; // these lines currently exist
//
//	@Column(name="number")
//	private int count;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Product getMeterial() {
//		return meterial;
//	}
//
//	public void setMeterial(Product meterial) {
//		this.meterial = meterial;
//	}
//
//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//
//
//	@Override
//	public String toString() {
//		return "BomEntity [id=" + id + ", product=" + meterial.getName() + " count=" + count + "]";
//	}
//
//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
//
//}
