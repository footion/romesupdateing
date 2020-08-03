package test;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class entityCar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column
	private String num;
	
	@Column
	private String type;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="entityH_id")
	private entityH entityH;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public entityH getEntityH() {
		return entityH;
	}

	public void setEntityH(entityH entityH) {
		this.entityH = entityH;
	}

	public entityCar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
