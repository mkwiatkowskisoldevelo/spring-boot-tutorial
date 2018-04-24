package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Called for POST serverUrl/products requests.
     * Used for creating new {@link Product}.
     * Returns 201 HTTP code if product was added successfully.
     * Returns 400 HTTP code if product was invalid.
     *
     * @param product request body mapped to {@link Product} class
     * @return newly created product
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    /**
     * Called for PUT serverUrl/products/{id} requests.
     * Used for updating {@link Product}.
     * Returns 200 HTTP code if product was updated successfully.
     * Returns 400 HTTP code if product was invalid.
     * Returns 404 HTTP code if product with given id was not found.
     *
     * @param product request body mapped to {@link Product} class
     * @param id path parameter that holds id of product to be updated
     * @return updated product
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(
        @RequestBody Product product,
        @PathVariable("id") Long id) {
        return productService.update(product, id);
    }

    /**
     * Called for GET serverUrl/products/{id} requests.
     * Used for retrieving {@link Product} by id.
     * Returns 200 HTTP code if product was found.
     * Returns 404 HTTP code if product with given id was not found.
     *
     * @param id path parameter that holds id of product to be retrieved
     * @return newly created product
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product get(@PathVariable("id") Long id) {
        return productService.get(id);
    }

    /**
     * Called for GET serverUrl/products requests.
     * Used for getting all {@link Product}.
     * Returns 200 HTTP code if products were found.
     *
     * @return all products stored in data base
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return productService.findAll();
    }

    /**
     * Called for DELETE serverUrl/products/{id} requests.
     * Used for removing existing {@link Product}.
     * Returns 204 HTTP code if product was removed successfully.
     * Returns 404 HTTP code if product with given id was not found.
     *
     * @param id path parameter that holds id of product to be removed
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") Long id) {
        productService.remove(id);
    }

    /**
     * Called for GET serverUrl/products/byName?name={name} requests.
     * Used for retrieving existing {@link Product} by name ignoring case.
     * Returns 200 HTTP code if product with given name was found.
     * Returns 404 HTTP code if product with given name was not found.
     *
     * @param name request parameter used for searching by name
     */
    @GetMapping("/byName")
    @ResponseStatus(HttpStatus.OK)
    public Product findByName(@RequestParam(value = "name") String name) {
        return productService.findProductByNameIgnoreCase(name);
    }
}
