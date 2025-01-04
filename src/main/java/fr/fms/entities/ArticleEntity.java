package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "T_Articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;

    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    @DecimalMin("50")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private LocalDateTime createdAt;

    @PrePersist
    public void createDate() {
        if (createdAt == null)
            createdAt = LocalDateTime.now();
    }

}
