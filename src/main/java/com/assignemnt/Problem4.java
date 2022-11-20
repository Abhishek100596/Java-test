package com.assignemnt;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.assignemnt")
public class Problem4 {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Problem4.class, args);
		Problem4getMap getMaps= ctx.getBean(Problem4getMap.class);

		try (Scanner scan = new Scanner(System.in)) {
			boolean isRunning = true;
			while(isRunning){
				Double totalIncome = null;

				//input total taxable income till it receives right data
				while(true) {
					System.out.println("Please the total taxable income: ");
					try {
						totalIncome=Double.valueOf(scan.next());
						break;
					}catch(Exception e) {
						System.out.println("Please enter valid income!! ");
					}
				}
				Map<String,String> countryNameAndTaxType=getMaps.getCountryTaxType();

				String countryName=null;
				
				//input country name till it receives right data
				while(true) {
					System.out.println("Please enter country name: ");
					countryName=scan.next();
					if(countryNameAndTaxType.containsKey(countryName)) {
						break;
					}
					System.out.println("Country detail is not present!! ");

				}

				String totalIncomeTax=null;
				if(countryNameAndTaxType.get(countryName).equalsIgnoreCase("flat")) {
					String flatTaxRate= getMaps.getFlatRateMap(countryName);
					totalIncomeTax=Problem4.calculateFlatTax(flatTaxRate,totalIncome);
				}
				if(countryNameAndTaxType.get(countryName).equalsIgnoreCase("graduated")) {
					List<Map<String, String>> flatTaxRate= getMaps.getGraduatedMap(countryName);
					totalIncomeTax=Problem4.calculategraduatedTax(flatTaxRate,totalIncome);
				}

				System.out.println("The total income tax: "+totalIncomeTax);

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
		}
		int exitCode =SpringApplication.exit(ctx,  () -> 0);
		System.exit(exitCode);

	}

	private static String calculategraduatedTax(List<Map<String, String>> flatTaxRate, Double totalIncome) {
		Double totalIncomeTax=0.0;	
		for(int i=0;i<flatTaxRate.size();i++) {
			if(!flatTaxRate.get(i).get("upperIncome").equalsIgnoreCase("-")&& totalIncome<Double.parseDouble(flatTaxRate.get(i).get("upperIncome"))) {
				totalIncomeTax=totalIncomeTax+(totalIncome-Double.parseDouble(flatTaxRate.get(i).get("lowerIncome")))*(Double.parseDouble(flatTaxRate.get(i).get("taxRate"))/100.0);
				break;
			}
			if(flatTaxRate.get(i).get("upperIncome").equalsIgnoreCase("-")) {
				totalIncomeTax=totalIncomeTax+(totalIncome-Double.parseDouble(flatTaxRate.get(i).get("lowerIncome")))*(Double.parseDouble(flatTaxRate.get(i).get("taxRate"))/100.0);
				continue;
			}
			totalIncomeTax=totalIncomeTax+(Double.parseDouble(flatTaxRate.get(i).get("upperIncome"))-Double.parseDouble(flatTaxRate.get(i).get("lowerIncome")))*(Double.parseDouble(flatTaxRate.get(i).get("taxRate"))/100.0);
		}
		if(totalIncomeTax<0)
			totalIncomeTax=0.0;
		return totalIncomeTax.toString();
	}

	private static String calculateFlatTax(String flatTaxRate, Double totalIncome) {

		return ((Double.parseDouble(flatTaxRate)/100.0)*totalIncome)+"";
	}
}

@Component
class Problem4getMap {	
	@Value("#{${flat.tax.rates}}")
	private Map<String, String> map;

	@Value("#{${country.tax.type}}")
	private Map<String, String> countryTaxType;

	@Value("#{${graduated.tax.rates}}")
	private Map<String, List<Map<String,String>>> graduatedMap;

	public String getFlatRateMap(String countryName) {
		return map.get(countryName);
	}

	public Map<String, String> getCountryTaxType() {
		return countryTaxType;
	}

	public List<Map<String, String>> getGraduatedMap(String countryName) {
		return graduatedMap.get(countryName);
	}
}
