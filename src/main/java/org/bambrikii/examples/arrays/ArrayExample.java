package org.bambrikii.examples.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 16/07/17 14:36.
 */
public class ArrayExample {
	public static void main(String[] args) {
		arrayDeclaration();
		arrayLength();
		arraysEqualByReferenceOnly();
		arrayListsAreEqualByContent();
		cannotRemoveFromEmptyArrayList();
		explicitTypeRequiredToAddToList();
		unsortedArrayBinarySearch();
		sortedList();
		arrayHasNoAsListMethod();
	}

	private static void arrayHasNoAsListMethod() {
		String[] names = {"Tom", "Dick", "Harry"};
//		List<String> list = names.asList();
		List<String> list = Arrays.asList(names);
		list.set(0, "Sue");
		System.out.println(names[0]);
	}

	private static void sortedList() {
		List<Integer> list = Arrays.asList(10, 4, -1, 5);
		Collections.sort(list);
		Integer array[] = list.toArray(new Integer[4]);
		System.out.println(array[0]);
	}

	private static void unsortedArrayBinarySearch() {
		int[] random = {6, -4, 12, 0, -10};
		int x = 12;
		int y = Arrays.binarySearch(random, x);
		System.out.println(y);
	}

	private static void explicitTypeRequiredToAddToList() {
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
//		list.add(7);
		for (String s : list) System.out.print(s);
	}

	private static void cannotRemoveFromEmptyArrayList() {
		try {
			new ArrayList<Integer>().remove(0);
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}

	private static void arrayListsAreEqualByContent() {
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		System.out.println(list1.equals(list2));
	}

	private static void arraysEqualByReferenceOnly() {
		int[] arr1 = new int[]{1, 2, 3};
		int[] arr2 = new int[]{1, 2, 3};
		System.out.println(arr1.equals(arr2));
	}

	private static void arrayLength() {
		char[] c = new char[2];
		// int length = c.capacity;
		// int length = c.capacity();
		int length = c.length;
		// int length = c.length();
		// int length = c.size;
		// int length = c.size();
	}

	private static void arrayDeclaration() {
		int[][] scores = new int[5][];
		Object[][][] cubbies = new Object[3][0][5];
		// String beans[] = new beans[6];
		java.util.Date[] dates[] = new java.util.Date[2][];
		// int[][] types = new int[];
		// int[][] java = new int[][];
	}
}
