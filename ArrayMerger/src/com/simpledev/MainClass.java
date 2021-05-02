package com.simpledev;

import java.util.Arrays;

public class MainClass {

	private int[] merge(int[] arr1, int[] arr2) {

		int arr1Size = arr1.length;
		int arr2Size = arr2.length;
		int finalSize = arr1Size + arr2Size;
		int pos = 0;
		int[] newArray = new int[finalSize];

		for (int i : arr1) {
			newArray[pos] = i;
			pos++;
		}

		for (int k : arr2) {
			newArray[pos] = k;
			pos++;
		}

		Arrays.sort(newArray);
		return newArray;
	}

	public static void main(String[] args) {

		MainClass main = new MainClass();
		int[] ar1 = { 1, 3, 5, 7, 9 };
		int[] ar2 = { 2, 4, 6, 8, 10 };
		int[] mergedArray = main.merge(ar1, ar2);
		for (int i : mergedArray) {
			System.out.println(i);
		}
	}
}