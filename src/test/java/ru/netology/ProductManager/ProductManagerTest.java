package ru.netology.ProductManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Smartphone phone1 = new Smartphone(1, "IPhone 11", 100_000, "Apple");
    Smartphone phone2 = new Smartphone(1890, "IPhone 12", 150_000, "Apple");
    Smartphone phone3 = new Smartphone(568, "IPhone 13", 200_000, "Apple");
    Book book1 = new Book(45, "Мастер и Маргарита", 1500, "Булгаков");
    Book book2 = new Book(4555, "Портрет Дориана Грэя", 1499, "Оскар Уайльд");
    Book book3 = new Book(12, "Портрет", 1645, "Гоголь");

    @BeforeEach
    public void before() {
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
    }

    @Test
    void AddProduct() {
        Product[] actual = repo.findAll();
        Product[] expected = {phone1, phone2, phone3, book1, book2, book3};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void SearchBookIfNameAvailable() {
        Product[] actual = manager.searchBy("Портрет");
        Product[] expected = {book2, book3};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void SearchBookIfNameNotAvailable() {
        Product[] actual = manager.searchBy("Властелин колец");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void SearchPhoneIfNameAvailable() {
        Product[] actual = manager.searchBy("Galaxy");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void SearchPhoneIfNameNotAvailable() {
        Product[] actual = manager.searchBy("IPhone 15");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldFindBookByName() {
        boolean expected = true;
        boolean actual = manager.matches(book1, "Мастер и Маргарита");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindBookByAuthor() {
        boolean expected = false;
        boolean actual = manager.matches(book1, "Булгаков");
        assertEquals(expected, actual);
    }

    @Test
    public void searchProductPrice() {
        Product[] actual = manager.searchBy("100_000");
        Product[] expected = {};
        Assertions.assertArrayEquals(actual, expected);
    }
}