package ru.netology.ProductManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();

    Smartphone phone1 = new Smartphone(1, "IPhone 11", 100_000, "Apple");
    Smartphone phone2 = new Smartphone(1890, "IPhone 12", 150_000, "Apple");
    Smartphone phone3 = new Smartphone(568, "IPhone 13", 200_000, "Apple");
    Book book1 = new Book(45, "Мастер и Маргарита", 1500, "Булгаков");
    Book book2 = new Book(4555, "Портрет Дориана Грэя", 1499, "Оскар Уайльд");
    Book book3 = new Book(12, "Портрет", 1645, "Гоголь");

    @Test
    public void findEmpty() {
        Product[] expected = {};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void addProduct() {
        repo.add(phone1);
        repo.add(phone2);
        repo.add(phone3);
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        Product[] actual = repo.findAll();
        Product[] expected = {phone1, phone2, phone3, book1, book2, book3};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void removeRealId() {
        repo.add(phone1);
        repo.add(phone2);
        repo.add(phone3);
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);
        repo.removeById(1890);
        repo.removeById(4555);
        Product[] actual = repo.findAll();
        Product[] expected = {phone1, phone3, book1, book3};
        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    void removeNotRealId() {
        repo.add(phone1);
        repo.add(phone2);
        repo.add(phone3);
        repo.add(book1);
        repo.add(book2);
        repo.add(book3);

        Assertions.assertThrows(NotFoundException.class,
                () -> repo.removeById(999));
    }
}