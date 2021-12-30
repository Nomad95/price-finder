package pl.igor.pricefinder.search.pricefindersearch.searching.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@EqualsAndHashCode
@Table(name = "Product")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long searchId;
    private LocalDate searchDate;
    private String source;
    private String itemId;
    private String name;
    private String brand;
    private String category;
    private String categoryId;
    @Embedded
    @AttributeOverride(name = "gross", column = @Column(name = "final_price"))
    @AttributeOverride(name = "currency", column = @Column(name = "final_currency"))
    private Price finalPrice;
    @Embedded
    @AttributeOverride(name = "gross", column = @Column(name = "base_price"))
    @AttributeOverride(name = "currency", column = @Column(name = "base_currency"))
    private Price basePrice;
    private String itemUrl;
    private String pictureUrl;
}
