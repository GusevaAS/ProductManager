package ru.netology.ProductManager;

public class ProductRepository {

    Product[] products = new Product[0];

    public Product[] findAll() {
        return products;
    }

    public void add(Product product) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public void removeById(int id) {
        Product foundProduct = findById(id);
        if (foundProduct == null) {
            throw new NotFoundException(id);
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product products : products) {
            if (products.getId() != id) {
                tmp[copyToIndex] = products;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    private Product findById(int id) {
        for (Product products : products) {
              if (products.getId() == id) {
                return products;
            }
        }
        return  null;
    }

}
