package project1_sample;

public class insertionSort {
	    public static void main(String[] args) {
	        int[] arr = {1002,110,23445,6789,9807,6455,75432};

	        for (int i = 1; i < arr.length; i++) {
	          for(int j=0;j<i;j++) {
	        	  if(arr[i]<arr[j])
	        	  {
	        		  int temp=arr[j];
	        		  arr[j]=arr[i];
	        		  arr[i]=temp;
	        	  }
	          }
	        }

	        System.out.println("Sorted array:");
	        for (int i=0;i<arr.length;i++) {
	            System.out.print(arr[i] + " ");
	        }
	    }
	}

