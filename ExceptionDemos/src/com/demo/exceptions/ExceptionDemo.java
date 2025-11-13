package com.demo.exceptions;

import java.io.*;

public class ExceptionDemo {

    public static void main(String[] args) throws IOException {

        System.out.println("Starting exception demos...\n");

        demoTryCatch();
        demoMultipleCatch();
        demoDivideByZero();
        demoArrayIndexOutOfBounds();
        demoFileNotFound();
        demoIOException();
        demoCustomCheckedException();
        demoCustomRuntimeException();
        demoThrowAndThrows();

        System.out.println("\nDemos finished.");
    }

    // 1) simple try-catch
    public static void demoTryCatch() {
        System.out.println("demoTryCatch:");
        try {
            int x = Integer.parseInt("notANumber");
            System.out.println("Parsed: " + x);
        } catch (NumberFormatException e) {
            System.out.println("Caught NumberFormatException: " + e.getMessage());
        }
    }

    // 2) try with multiple catches (order: specific -> general)
    @SuppressWarnings("null")
	public static void demoMultipleCatch() {
        System.out.println("demoMultipleCatch:");
        try {
            String s = null;
            System.out.println(s.length()); // will throw NullPointerException
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Generic Exception caught");
        }
    }

    // 3) divide by zero -> ArithmeticException
    public static void demoDivideByZero() {
        System.out.println("demoDivideByZero:");
        try {
            int a = 10;
            int b = 0;
            int c = a / b;
            System.out.println("Result: " + c);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException (divide by zero).");
            e.printStackTrace(System.out);
        }
    }

    // 4) ArrayIndexOutOfBoundsException
    public static void demoArrayIndexOutOfBounds() {
        System.out.println("demoArrayIndexOutOfBounds:");
        try {
            int[] arr = {1,2,3};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }

    // 5) FileNotFoundException example
    public static void demoFileNotFound() throws IOException {
        System.out.println("demoFileNotFound:");
        try {
            FileReader fr = new FileReader("this_file_does_not_exist.txt"); // triggers FileNotFoundException
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException: " + e.getMessage());
        }
    }

    // 6) IOException example (use try-with-resources to show IO handling)
    public static void demoIOException() {
        System.out.println("demoIOException:");
        // create a temporary file then try to read beyond: we can still show catching IOException via BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader("this_file_does_not_exist_either.txt"))) {
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) { // FileNotFoundException is a subclass of IOException
            System.out.println("Caught IOException: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // 7) custom checked exception (extends Exception)
    public static void demoCustomCheckedException() {
        System.out.println("demoCustomCheckedException:");
        try {
            maybeThrowChecked(true);
        } catch (MyCheckedException e) {
            System.out.println("Caught MyCheckedException: " + e.getMessage());
        }
    }

    // checked exception method (must declare throws)
    public static void maybeThrowChecked(boolean doThrow) throws MyCheckedException {
        if (doThrow) throw new MyCheckedException("Checked problem occurred");
        System.out.println("No checked exception thrown.");
    }

    // 8) custom runtime exception (extends RuntimeException)
    public static void demoCustomRuntimeException() {
        System.out.println("demoCustomRuntimeException:");
        try {
            maybeThrowRuntime(true);
        } catch (MyRuntimeException e) {
            System.out.println("Caught MyRuntimeException: " + e.getMessage());
        }
    }

    public static void maybeThrowRuntime(boolean doThrow) {
        if (doThrow) throw new MyRuntimeException("Runtime problem occurred");
        System.out.println("No runtime exception thrown.");
    }

    // 9) throw and throws example: caller declares throws and caller handles it
    public static void demoThrowAndThrows() {
        System.out.println("demoThrowAndThrows:");
        try {
            methodThatThrows();
        } catch (IOException e) {
            System.out.println("Handled IOException from methodThatThrows: " + e.getMessage());
        }
    }

    // method declares throws and uses throw inside
    public static void methodThatThrows() throws IOException {
        // simulate an IO problem
        throw new IOException("Simulated IO failure from methodThatThrows");
    }

    // --- custom exception classes ---
    // checked exception
    public static class MyCheckedException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyCheckedException(String msg) { super(msg); }
    }

    // runtime (unchecked) exception
    public static class MyRuntimeException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyRuntimeException(String msg) { super(msg); }
    }
}
