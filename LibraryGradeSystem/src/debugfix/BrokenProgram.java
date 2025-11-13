package debugfix;

public class BrokenProgram {
    public static void main(String args[]) {
        int n[] = {1, 2, 3};
        try {
            System.out.println(n[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry!Tried to access outside the given array.");
        }
        System.out.println("Fixed it!");
    }
}
