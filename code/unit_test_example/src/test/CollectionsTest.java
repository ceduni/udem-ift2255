package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Author;
import main.Book;
import test.utils.NumberGenerator;

class CollectionsTest {
	private List<Book> bookCollection;

	@BeforeEach
	public void setUp() {
		bookCollection = new ArrayList<>();

		// Données initiales pour les tests
		bookCollection.add(new Book("Thinking, Fast and Slow", "Daniel Kahneman", 2011));
		bookCollection.add(new Book("Les impatientes", "Djaili Amadou Amal", 2020));
		bookCollection.add(new Book("100 Things Every Designer Needs to Know About People", "Susan Weinschenk", 2020));
		bookCollection.add(new Book("Libérez votre cerveau!", "Idriss Aberkane", 2016));
	}

	@Test
	public void testAddBook() {
		// Arrange
		Book newBook = new Book("When the Body Says No: The Cost of Hidden Stress", new Author("Gabor Maté"), 2004);
		int sizeBefore = bookCollection.size();

		// Act
		var result = bookCollection.add(newBook);

		// Assert
		assertTrue(result);
		assertTrue(bookCollection.contains(newBook));
		assertEquals(bookCollection.size(), sizeBefore + 1);
	}

	@Test
	public void testRemoveBook() {
		// Arrange
		int randIndex = NumberGenerator.generateNumber(0, bookCollection.size() - 1);
		Book bookToRemove = bookCollection.get(randIndex);
		int sizeBefore = bookCollection.size();

		// Act
		var result = bookCollection.remove(bookToRemove);

		// Assert
		assertTrue(result);
		assertFalse(bookCollection.contains(bookToRemove));
		assertEquals(bookCollection.size(), sizeBefore - 1);
	}
}
