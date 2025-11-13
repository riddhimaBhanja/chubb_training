package project1_sample;

public class linearSearch {
	    public static void main(String[] args) {
	        int[] arr = {2, 6, 7, 17, 29, 31, 67, 89, 101};
	        int n = 8;
	        boolean found = false;

	        for (int i = 0; i < arr.length; i++) {
	            if (arr[i] == n) {
	                System.out.println("Element " + n + " found at index " + i);
	                found = true;
	                break;
	            }
	        }

	        if (!found)
	            System.out.println("Element " + n + " not found.");
	    }
	}



