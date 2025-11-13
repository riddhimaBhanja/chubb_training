package library;

import java.util.*;

public class Member {
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public void borrowBook(Book b) {
        if (!b.isBorrowed()) {
            b.borrow();
            borrowedBooks.add(b);
            System.out.println(name + " borrowed " + b.getTitle());
        } else {
            System.out.println("Sorry, that book is already borrowed.");
        }
    }

    public void returnBook(Book b) {
        if (borrowedBooks.remove(b)) {
            b.returnBook();
            System.out.println(name + " returned " + b.getTitle());
        } else {
            System.out.println("You didn’t borrow this book.");
        }
    }

    public void showBorrowedBooks() {
        System.out.println(name + "'s borrowed books:");
        borrowedBooks.forEach(System.out::println);
    }
}
