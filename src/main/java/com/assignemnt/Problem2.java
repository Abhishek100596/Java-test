package com.assignemnt;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		List<Stock> stockList = new ArrayList<>();
		try (Scanner scan = new Scanner(System.in)) {
			boolean isRunning = true;

			while(isRunning){
				System.out.println("Please enter Pruduct code: ");
				String productCode = scan.next();

				System.out.println("Please enter Batch number: ");
				String batchNumber = scan.next();

				Date manufacturingDate =null;
				while(true) {
					System.out.println("Please enter Manufacturing date (YYYY-MM-DD): ");
					try {
						manufacturingDate=Date.valueOf(scan.next());
						break;
					}catch(Exception e) {
						System.out.println("Please enter valid date ");
					}
				}

				Date ExpiryDate = null;
				while(true) {
					System.out.println("Please enter Expiry date (YYYY-MM-DD): ");
					try {
						ExpiryDate=Date.valueOf(scan.next());
						break;
					}catch(Exception e) {
						System.out.println("Please enter valid date ");
					}
				}

				System.out.println("Please enter Warehouse code: ");
				String wareHouseCode = scan.next();


				Double quantity = null;

				while(true) {
					System.out.println("Please enter Quantity: ");
					try {
						quantity=Double.valueOf(scan.next());
						break;
					}catch(Exception e) {
						System.out.println("Please enter Quantity in float ");
					}
				}
				Stock a = new Stock(productCode, batchNumber,manufacturingDate,ExpiryDate,wareHouseCode,quantity);
				stockList.add(a);

				while(true) {
					System.out.println("Do you want to continue (Y/N)?");
					String flag=scan.next();
					if(flag.equalsIgnoreCase("N")) {
						isRunning=false;
						break;
					}
					else if(flag.equalsIgnoreCase("Y")) {
						break;
					}
					else {
						System.out.println("Wrong code !!");
					}
				}


			}

			System.out.println("The list of stock :");
			stockList.forEach(stock -> System.out.println(stock));


			String attribute=null;
			while(true) {
				System.out.println("\n\nPlease enter the attribute with which you want to sort the list of stocks: ");
				System.out.println("Product code - 1 ");
				System.out.println("Batch number - 2");
				System.out.println("Manufacturing date - 3");
				System.out.println("Expiry date - 4 ");
				System.out.println("Warehouse code - 5 ");
				System.out.println("Quantity - 6 ");
				attribute=scan.next();

				if(attribute.equals("1")||attribute.equals("2")||attribute.equals("3")||attribute.equals("4")||attribute.equals("5")||attribute.equals("6")) {
					break;
				}
				else {
					System.out.println("Please enter valid number between 1 - 6!!");
				}
			}
			Integer order=null;
			while(true) {
				System.out.println("Please enter the order of sorting (Ascending- 0 /Descending-1 )?");
				try {
					order=Integer.parseInt(scan.next());
					if(order!=1 && order !=0) {
						System.out.println("Please enter number 0 or 1");
						continue;
					}
					break;
				}catch(Exception e) {
					System.out.println("Please enter number 0 or 1");
				}
			}


			System.out.println("\n\n\nOriginal list of stock :");
			stockList.forEach(stock -> System.out.println(stock));
			List<Stock> sortedList = Problem2.getSortedList(stockList,attribute,order);

			System.out.println("Sorted list of stock :");
			System.out.println(sortedList);
		}
	}

	private static List<Stock> getSortedList(List<Stock> stockList, String attribute, Integer order) {
		
		if(order==0) {
			if(attribute.equalsIgnoreCase("1"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getProductCode));
			if(attribute.equalsIgnoreCase("2"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getBatchNumber));
			if(attribute.equalsIgnoreCase("3"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getManufacturingDate));
			if(attribute.equalsIgnoreCase("4"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getExpiryDate));
			if(attribute.equalsIgnoreCase("5"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getWarehouseCode));
			if(attribute.equalsIgnoreCase("6"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getQuantity));
		}else if(order==1){
			if(attribute.equalsIgnoreCase("1"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getProductCode).reversed());
			if(attribute.equalsIgnoreCase("2"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getBatchNumber).reversed());
			if(attribute.equalsIgnoreCase("3"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getManufacturingDate).reversed());
			if(attribute.equalsIgnoreCase("4"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getExpiryDate).reversed());
			if(attribute.equalsIgnoreCase("5"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getWarehouseCode).reversed());
			if(attribute.equalsIgnoreCase("6"))
				Collections.sort(stockList, Comparator.comparing(Stock ::getQuantity).reversed());
		}
		
		return stockList;

	}

}
