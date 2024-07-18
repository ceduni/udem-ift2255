package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Book {
	private String name;
	private int year;
	private Collection<Author> authors;

	public Book(String name, String[] authors, int year) {
		this(name, year);
		for (String author : authors) {
			this.authors.add(new Author(author));
		}
	}

	public Book(String name, Author[] authors, int year) {
		this(name, year);
		Collections.addAll(this.authors, authors);
	}

	public Book(String name, Author author, int year) {
		this(name, year);
		this.authors.add(author);
	}

	public Book(String name, String author, int year) {
		this(name);
		this.authors.add(new Author(author));
	}

	public Book(String name, String[] authors) {
		this(name);
		for (String author : authors) {
			this.authors.add(new Author(author));
		}
	}

	public Book(String name, String author) {
		this(name);
		this.authors.add(new Author(author));
	}

	public Book(String name, int year) {
		this(name);
		this.year = year;
	}

	public Book(String name) {
		this.name = name;
		this.authors = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Collection<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return name + ", " + authors.toString() + " (" + year + ")";
	}
}
