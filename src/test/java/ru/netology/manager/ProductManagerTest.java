package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Война и мир", 10, "Л.Н.Толстой");
    Product second = new Book(2, "Анна Каренина", 20, "Л.Н.Толстой");
    Product third = new Book(3, "Дама с собачкой", 30, "А.П.Чехов");
    Product fourth = new Smartphone(4, "IPhoneX", 100, "Apple");
    Product fifth = new Smartphone(5, "Galaxy Z", 200, "Samsung");

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    void shouldSearchByBookAuthor() {
        Product[] actual = manager.searchBy("Л.Н.Толстой");
        Product[] expected = new Product[] {first, second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByBookName() {
        Product[] actual = manager.searchBy("Анна Каренина");
        Product[] expected = new Product[] {second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByBookNameNotExist() {
        Product[] actual = manager.searchBy("Толстый и тонкий");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneName() {
        Product[] actual = manager.searchBy("Galaxy Z");
        Product[] expected = new Product[] {fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneMaker() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[] {fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneNameNotExist() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }

}