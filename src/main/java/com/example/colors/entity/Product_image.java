package com.example.colors.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class Product_image {

    public Product_image() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_path1",length = 45, nullable = false)
    private String image_path1;

    @Column(name = "image_path2",length = 45, nullable = false)
    private String image_path2;

    @Column(name = "image_path3",length = 45, nullable = false)
    private String image_path3;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_path1() {
        return image_path1;
    }

    public void setImage_path1(String image_path1) {
        this.image_path1 = image_path1;
    }

    public String getImage_path2() {
        return image_path2;
    }

    public void setImage_path2(String image_path2) {
        this.image_path2 = image_path2;
    }

    public String getImage_path3() {
        return image_path3;
    }

    public void setImage_path3(String image_path3) {
        this.image_path3 = image_path3;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
