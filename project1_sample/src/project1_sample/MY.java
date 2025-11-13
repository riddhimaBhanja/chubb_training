package project1_sample;

public class MY {
	public static void main(String args[]) {
		
	int[] arr= {1002,110,23445,6789,9807,6455,75432};
	for(int i=0;i<arr.length-1;i++) {
		if(arr[i+1]<arr[i]) {
			int temp=arr[i];
			arr[i]=arr[i+1];
			arr[i+1]=temp;
		}
	}
	System.out.println("sorted array:");
		for(int i=0;i<arr.length-1;i++) {
			
			System.out.print(arr[i]+",");
		}
}
}

