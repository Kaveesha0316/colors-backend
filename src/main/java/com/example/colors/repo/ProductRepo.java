package com.example.colors.repo;

import com.example.colors.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {

    public List<Product> findProductByStatus_NameAndNameStartingWithOrderByIdDesc(String status, String name);

    @Query("SELECT p FROM Product p " +
            "WHERE (:statusName IS NULL OR p.status.name = :statusName) " +
            "AND (:name IS NULL OR p.name LIKE CONCAT(:name, '%')) " +
            "AND (:categoryName IS NULL OR p.category.name = :categoryName) " +
            "AND (:priceAfter IS NULL OR p.price >= :priceAfter) " +
            "AND (:priceBefore IS NULL OR p.price <= :priceBefore) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'Price DESC' THEN p.price END DESC, " +
            "CASE WHEN :sortBy = 'Price ASC' THEN p.price END ASC, " +
            "CASE WHEN :sortBy = 'Latest' THEN p.created_at END DESC, " +
            "CASE WHEN :sortBy = 'Oldest' THEN p.created_at END ASC")
    List<Product> findProducts(
            @Param("statusName") String statusName,
            @Param("name") String name,
            @Param("categoryName") String categoryName,
            @Param("priceAfter") Double priceAfter,
            @Param("priceBefore") Double priceBefore,
            @Param("sortBy") String sortBy
    );

    public List<Product> findProductsByUser_IdAndNameStartingWithOrderByIdDesc(Integer id,String name);


    @Query("SELECT p FROM Product p WHERE p.category.name = :categoryName AND p.id != :productId")
    List<Product> findProductsByCategoryNameAndExcludeProductId(
            @Param("categoryName") String categoryName,
            @Param("productId") Integer productId
    );
}
