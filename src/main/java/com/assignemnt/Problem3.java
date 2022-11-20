package com.assignemnt;

public class Problem3 {

	public static void main(String[] args) {
		int arr[] = { 6,2,8,1,3,4,-1,-2 };

		int len = arr.length;

		// Initialize highest and second highest with minimum integer value
		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;

		//Traversing the array
		for (int i = 0; i < len; i++) { 
			// If the number is greater than max 
			if (arr[i] > max) {
				// assign max to secondMax
				secondMax = max;
				// Set new max
				max = arr[i];
			}
			// If the number is less than max and greater than secondMax
			if (arr[i] < max && arr[i] > secondMax) {
				secondMax = arr[i];
			}
		}

		System.out.println("Second largest element of the array " + secondMax);
	}
}
