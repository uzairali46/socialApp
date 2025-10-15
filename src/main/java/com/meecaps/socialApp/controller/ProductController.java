package com.meecaps.socialApp.controller;

import com.meecaps.socialApp.entity.Product;
import com.meecaps.socialApp.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final  ProductRepository productRepository;
    private final EntityManager entityManager;


    public ProductController(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    @PostMapping("/create")
    public String CreateProduct(@RequestBody Product product){

       Product product1 = new Product();

       product1.setId(product.getId());
       product1.setProductName(product.getProductName());
       product1.setPrice(product.getPrice());;
       product1.setQuantity(product.getQuantity());

        productRepository.save(product1);

        return "created successfullyyyyyyy";
    }

    @GetMapping("/get")
    public List<Product> GetProduct(){
        return productRepository.findAll();
    }

    // Filter name and price

    @GetMapping("/filter")
    public List<Product> getProductByNameAndPrice(@RequestParam String productName,
                                                  @RequestParam String price){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteriaQuery.from(Product.class);
        List<Predicate> condition = new ArrayList<>();

        if(productName != null){

            condition.add(criteriaBuilder.equal(root.get("productName"),productName));
        }
        if(price != null){
            condition.add(criteriaBuilder.equal(root.get("price"),productName));
        }

        criteriaQuery.select(root).where(condition.toArray(new Predicate[0]));

        // By using OR

//        criteriaQuery.select(root).
//                where(criteriaBuilder.or(condition.toArray(new Predicate[0])));
//

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }



}
