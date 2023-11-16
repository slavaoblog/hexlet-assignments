package exercise.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@EqualsAndHashCode(of = {"title", "price"})
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;
    private int price;
}
// END
