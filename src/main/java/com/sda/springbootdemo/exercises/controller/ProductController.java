package com.sda.springbootdemo.exercises.controller;

import com.sda.springbootdemo.exercises.model.Product;
import com.sda.springbootdemo.exercises.model.Receipt2;
import com.sda.springbootdemo.exercises.repository.ProductRepository;
import com.sda.springbootdemo.exercises.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private ProductRepository productRepository;

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
     * @param name used for searching products containing given param ignoring case
     * @param minPrice used for searching products with prices greater equal than given value
     * @param maxPrice used for searching products with prices lesser equal than given value
     * @param pageable contains sorting and paging parameters
     * @return all products matching parameters
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> search(
        @RequestParam(value = "name", required = false, defaultValue = "") String name,
        @RequestParam(value = "minPrice", required = false, defaultValue = "0") Double minPrice,
        @RequestParam(value = "maxPrice", required = false, defaultValue = "10000") Double maxPrice,
        Pageable pageable) {
        return productService.search(name, minPrice, maxPrice, pageable);
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
     * @return found product with given name
     */
    @GetMapping("/byName")
    @ResponseStatus(HttpStatus.OK)
    public List<Receipt2> findByName(
        @RequestParam(value = "name") String name) {
        return productRepository.search(name);
    }

    @GetMapping("/byNamePaged")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> findByName(
        Pageable pageable) {
        return productRepository.findByNameIgnoreCaseContaining("", pageable);
    }
}
