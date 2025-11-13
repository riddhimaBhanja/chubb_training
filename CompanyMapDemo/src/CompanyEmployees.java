import java.util.*;

public class CompanyEmployees {
    public static void main(String[] args) {

        // Creating a map of CompanyName -> List of Employees
        Map<String, List<String>> companyMap = new HashMap<>();

        // Adding companies with employees
        companyMap.put("TCS", Arrays.asList("Aman", "Sonal", "Rohit"));
        companyMap.put("Infosys", Arrays.asList("Priya", "Vikas", "Neha"));
        companyMap.put("Wipro", Arrays.asList("Raj", "Kunal", "Meena"));
        companyMap.put("IBM", Arrays.asList("Karan", "Swati", "Ajay"));
        // Note: Cognizant intentionally not added to test addition

        // Printing all entries
        System.out.println("Company and Employee Details:");
        for (Map.Entry<String, List<String>> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Check if Cognizant exists; if not, add it with employees
        if (!companyMap.containsKey("Cognizant")) {
            System.out.println("\nCognizant not found! Adding Cognizant...");
            companyMap.put("Cognizant", new ArrayList<>(Arrays.asList("Aashish Choudhary", "Ravi", "Anita")));
        }

        // Check if Ram exists in Cognizant
        List<String> cognizantEmployees = companyMap.get("Cognizant");

        if (cognizantEmployees.contains("Ram")) {
            System.out.println("\nRam is working in Cognizant");
        } else {
            System.out.println("\nRam not found in Cognizant");
        }

        // Print updated map
        System.out.println("\nUpdated Company and Employee Details:");
        for (Map.Entry<String, List<String>> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}