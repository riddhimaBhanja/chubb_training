package project1_sample;

public class binarySearch {
	    public static void main(String[] args) {
	        int[] arr = {2, 6, 7, 17, 29, 31, 67, 89, 101};
	        int n = 8;

	        int left = 0, right = arr.length - 1;
	        boolean found = false;

	        while (left <= right) {
	            int mid = left + (right - left) / 2;

	            if (arr[mid] == n) {
	                System.out.println("Element " + n + " found at index " + mid);
	                found = true;
	                break;
	            } else if (arr[mid] < n)
	                left = mid + 1;
	            else
	                right = mid - 1;
	        }

	        if (!found)
	            System.out.println("Element " + n + " not found.");
	    }
	}

