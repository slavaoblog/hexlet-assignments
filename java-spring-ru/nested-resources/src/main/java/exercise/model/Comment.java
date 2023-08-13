package exercise.model;

import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    // BEGIN
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;
    // END
}
