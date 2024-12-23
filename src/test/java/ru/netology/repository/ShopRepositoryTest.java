package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;

public class ShopRepositoryTest {
    ShopRepository repository = new ShopRepository();

    Product product1 = new Product(13, "Кошачий корм", 900);
    Product product2 = new Product(14, "Мячик для кошки", 80);

    @BeforeEach
    public void setup() {
        repository.add(product1);
        repository.add(product2);
    }

    @Test
    public void shouldRemoveByIdSuccessfully() {
        repository.removeById(product1.getId());

        Product[] expected = {product2};
        Assertions.assertArrayEquals(expected, repository.findAll());
    }

    @Test
    public void shouldRemoveUnsuccessfully() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(15);
        });
    }

    @Test
    public void shouldAddToArraySuccessfully() {
        Product[] expected = {product1, product2};
        Assertions.assertArrayEquals(expected, repository.findAll());
    }

    @Test
    public void shouldAddUnsuccessfully() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product1);
        });
    }
}