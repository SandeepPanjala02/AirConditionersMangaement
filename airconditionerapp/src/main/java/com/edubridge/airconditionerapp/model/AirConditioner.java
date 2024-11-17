package com.edubridge.airconditionerapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "air_conditioners")
public class AirConditioner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String brand;
	private String model;
	private double price;
	private Double powerRating;
	
	public AirConditioner() {}
	
	public AirConditioner(String brand, String model, double price, Double powerRating) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.powerRating = powerRating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public Double getPoweRating() {
		return powerRating;
	}
	public void setPoweRating(Double poweRating) {
		this.powerRating = poweRating;
	}
	@Override
	public String toString() {
		return "AirConditioner [id=" + id + ", brand=" + brand + ", model=" + model + ", price=" + price
				+ ", poweRating=" + powerRating + "]";
	}
	
	
	

}
