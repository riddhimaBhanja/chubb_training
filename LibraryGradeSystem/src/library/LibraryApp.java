package library;

import java.util.*;

public class LibraryApp {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
     // Creating a list to store books which are available
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("Harry Potter", "J.K. ROWLING"));
        books.add(new Book("Gitanjali", "Rabindranath Tagore"));

        System.out.print("Enter your name: ");
        Member m = new Member(sc.nextLine());
     // It is an infinite menu loop until we press exit
        while (true) {
            System.out.println("\n1. Show Books\n2. Borrow Book\n3. Return Book\n4. My Books\n5. Exit");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> books.forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title))
                         .findFirst().ifPresentOrElse(m::borrowBook,
                         () -> System.out.println("Book not found"));
                }
                case 3 -> {
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title))
                         .findFirst().ifPresentOrElse(m::returnBook,
                         () -> System.out.println("Book not found"));
                }
                case 4 -> m.showBorrowedBooks();
                case 5 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
