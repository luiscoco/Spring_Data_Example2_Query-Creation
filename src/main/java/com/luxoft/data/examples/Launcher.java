package com.luxoft.data.examples;

import com.luxoft.data.examples.model.Book;
import com.luxoft.data.examples.repositories.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    @Bean
    public ApplicationRunner demo(BookRepository repository) {
        return env ->
        {
            printTitle("Spring Data Ex.2; Query Creation");
            printTitle("H2 CONSOLE: localhost:8080/h2-console" +
                    "\nJDBC URL: jdbc:h2:mem:./books");

            repository.saveAll(generateBooks());
            print("10 new books saved.");

            printTitle("All Books In Storage");
            repository.findAll().forEach(System.out::println);

            printTitle("#findByTitle (Effective Java)");
            Book book = repository.findByTitle("Effective Java");
            print(book.toString());

            printTitle("#findAllByOrderByTitleAsc");
            repository.findAllByOrderByTitleAsc().forEach(Launcher::print);

            printTitle("#findByReleaseDateBetween (should be 2017 only)");
            repository.findByReleaseDateBetween(LocalDate.of(2017, 1, 1),
                    LocalDate.of(2018, 1, 1))
                    .forEach(Launcher::print);
        };
    }

    public static List<Book> generateBooks() {
        List<Book> books = new ArrayList<>(10);

        Book book1 = new Book("Effective Java",
                "Joshua Bloch", LocalDate.of(2018, 1, 6));
        Book book2 = new Book("Head First Java, 2nd Edition",
                "Kathy Sierra", LocalDate.of(2009, 5, 11));
        Book book3 = new Book("Java: The Complete Reference",
                "Herbert Schildt", LocalDate.of(2018, 12, 12));
        Book book4 = new Book("Java in a Nutshell: A Desktop Quick Reference",
                "Benjamin J. Evans", LocalDate.of(2018, 12, 23));
        Book book5 = new Book("Core Java Volume",
                "Cay S. Horstmann", LocalDate.of(2018, 9, 7));
        Book book6 = new Book("Introduction to Java Programming and Data Structures",
                "Y. Daniel Liang", LocalDate.of(2017, 3, 11));
        Book book7 = new Book("Java How to Program, Early Objects",
                "Paul J. Deitel", LocalDate.of(2017, 3, 2));
        Book book8 = new Book("Beginning Programming with Java For Dummies",
                "Barry Burd", LocalDate.of(2017, 7, 4));
        Book book9 = new Book("Murach's Java Programming",
                "Joel Murach", LocalDate.of(2017, 6, 22));
        Book book10 = new Book("Modern Java in Action",
                "Raoul-Gabriel Urma", LocalDate.of(2018, 11, 15));

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);

        return books;
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(Book b) {
        System.out.println(b.toString());
    }

    public static void printTitle(String s) {
        System.out.println("\n=======================");
        System.out.println(s);
        System.out.println("=======================");
    }

}
