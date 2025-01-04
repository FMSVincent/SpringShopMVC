package fr.fms.entities;


import fr.fms.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "T_Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class CategoryEntity extends CategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Collection<ArticleEntity> articles;

    private LocalDateTime createdAt;

    @PrePersist
    public void createDate() {
        if (createdAt == null)
            createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
