package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books;
    public MemBookDao() {
        this.books = new ArrayList<>();
        this.books.add(new Book("9780593099322", "Dune", "A deluxe hardcover edition of Frank Herbert’s epic masterpiece—a triumph of the imagination and one of the bestselling science fiction novels of all time.", 688, new ArrayList<>(List.of("J.R.R. Tolkien"))));
        this.books.add(new Book("0345339681", "J2EE Design and Development - expert one-on-one", "Rod Johnson is an enterprise Java architect specializing in scalable web applications.  He has worked with both Java and JEE since their release, and he is a member of the JSR 154 Expert Group defining the Servelet 2.4 Specification.", 742, new ArrayList<>(List.of("Rod Johnson"))));
        this.books.add(new Book("0261103571", "The Fellowship of the Ring", "The first volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them", 432, new ArrayList<>(List.of("J.R.R. Tolkien"))));
        this.books.add(new Book("1514297272", "The Two Towers", "The second volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS", 448, new ArrayList<>(List.of("J.R.R. Tolkien"))));
        this.books.add(new Book("1514298139", "The Return of the King", "The third volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS", 432, new ArrayList<>(List.of("J.R.R. Tolkien"))));
    }

    @Override
    public List<Book> list() {
        return this.books;
    }

    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}
