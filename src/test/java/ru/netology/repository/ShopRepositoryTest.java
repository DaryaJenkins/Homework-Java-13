package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;

public class ShopRepositoryTest {
    ShopRepository repository = new ShopRepository();

    Product product1 = new Product(13, "Кошачий корм", 900);
    Product product2 = new Product(14, "Мячик для кошки", 80);

    @Test
    public void shouldRemoveByIdSuccessfully() {
        repository.add(product1);
        repository.add(product2);

        repository.removeById(product1.getId());

        Product[] expected = {product2};
        Assertions.assertArrayEquals(expected, repository.findAll());
    }

    @Test
    public void shouldRemoveUnsuccessfully() {
        repository.add(product1);
        repository.add(product2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(15);
        });
    }

    @Test
    public void shouldAddToArraySuccessfully() {
        repository.add(product1);
        repository.add(product2);

        Product[] expected = {product1, product2};
        Assertions.assertArrayEquals(expected, repository.findAll());
    }

    @Test
    public void shouldAddUnsuccessfully() {
        repository.add(product1);
        repository.add(product2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product1);
        });
    }
}