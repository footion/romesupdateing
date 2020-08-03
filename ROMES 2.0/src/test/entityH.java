package test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class entityH {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column
	private String Name;
	
	@OneToOne(mappedBy = "entityH")
	private entityCar Car;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public entityCar getCar() {
		return Car;
	}

	public void setCar(entityCar car) {
		Car = car;
	}

	public entityH() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
