//package test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//
//@Entity
//@Table(name = "product_table")
//public class product {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private int id;
//
//	@Column(name = "name")
//	private String name;
//
//	@Column(name = "image")
//	private byte[] image;
//
//	@Column(name = "isSaleable")
//	private boolean isSaleable;
//
//	@Column(name = "isBuyable")
//	private boolean isBuyable;
//
//	@OneToOne(mappedBy = "product")
//	private ProductCommonInfo commonInfo;
//
//	@OneToMany(mappedBy="product",
//			cascade=CascadeType.ALL,orphanRemoval=true,fetch = FetchType.EAGER)
//	private List<BomEntity> boms = new ArrayList<BomEntity>();
//
//	 public void addBom(BomEntity bom) {
//		 boms.add(bom);
//		 bom.setProduct(this);
//	    }
//	 
//	    public void removeBom(BomEntity bom) {
//	    	boms.remove(bom);
//	    	bom.setProduct(null);
//	    }
//
//	
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
//
//	public boolean isSaleable() {
//		return isSaleable;
//	}
//
//	public void setSaleable(boolean isSaleable) {
//		this.isSaleable = isSaleable;
//	}
//
//	public boolean isBuyable() {
//		return isBuyable;
//	}
//
//	public void setBuyable(boolean isBuyable) {
//		this.isBuyable = isBuyable;
//	}
//
//	public ProductCommonInfo getCommonInfo() {
//		return commonInfo;
//	}
//
//	public void setCommonInfo(ProductCommonInfo commonInfo) {
//		this.commonInfo = commonInfo;
//	}
//
//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", isSaleable="
//				+ isSaleable + ", isBuyable=" + isBuyable + ", commonInfo=" + commonInfo + "]";
//	}
//
//	public List<BomEntity> getBoms() {
//		return boms;
//	}
//
//	public void setBoms(List<BomEntity> boms) {
//		this.boms = boms;
//	}
//
//	
//
//	
//	
//
//	
//
//}