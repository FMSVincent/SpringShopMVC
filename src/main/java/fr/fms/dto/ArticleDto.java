package fr.fms.dto;

import fr.fms.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    @NotNull
    @PositiveOrZero
    private long id;

    private String brand;

    private String description;

    @DecimalMin(message = "should be a decimal number", value = "50")
    private double price;

    private CategoryEntity category;

}
