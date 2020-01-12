package com.example.myamazon.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.example.myamazon.util.ValidationUtils.throwIfNull;
import static com.example.myamazon.web.ControllerUtils.getHeadersWithLocation;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public PagedModel<Product> findProduct(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Product> products = productService.findAll(pageable);
        PagedModel<Product> pagedModel = assembler.toModel(products);
        return pagedModel;
    }

    /**
     * Creates a new product.
     *
     * @param product [required]
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@RequestBody @Valid @NotNull Product product) {
        Product createdProduct = productService.create(product);
        return new ResponseEntity<Product>(createdProduct, getHeadersWithLocation(createdProduct), HttpStatus.CREATED);
    }

    /**
     * Update product
     *
     * @param id - id of the product to update
     * @param product - product to update
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product update(@PathVariable @NotBlank String id, @RequestBody @Valid Product product) {
        throwIfNull(product.getId(), "id");
        if (!id.equals(product.getId())) {
            throw new ValidationException(
                    "productId path variable doesn't correspond to productId attribute in Json requestBody");
        }
        return productService.update(product);
    }
}
