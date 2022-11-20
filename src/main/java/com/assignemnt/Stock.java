package com.assignemnt;

import java.util.Date;

import org.springframework.boot.autoconfigure.SpringBootApplication;

//Class as mentioned in problem 1
public class Stock{
	private String productCode;
	private String batchNumber;
	private Date manufacturingDate;
	private Date expiryDate;
	private String warehouseCode;
	private Double quantity;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public Date getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Stock [productCode=" + productCode + ", batchNumber=" + batchNumber + ", manufacturingDate="
				+ manufacturingDate + ", expiryDate=" + expiryDate + ", warehouseCode=" + warehouseCode + ", quantity="
				+ quantity + "]";
	}
	public Stock(String productCode, String batchNumber, Date manufacturingDate, Date expiryDate, String warehouseCode,
			Double quantity) {
		super();
		this.productCode = productCode;
		this.batchNumber = batchNumber;
		this.manufacturingDate = manufacturingDate;
		this.expiryDate = expiryDate;
		this.warehouseCode = warehouseCode;
		this.quantity = quantity;
	}
	
	
}