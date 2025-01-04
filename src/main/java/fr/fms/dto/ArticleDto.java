package fr.fms.dto;

import fr.fms.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    @NotNull
    @PositiveOrZero
    private long id;

    @NotBlank(message = "brand is mandatory")
    @Size(min = 3, max = 50)
    private String brand;

    @NotBlank(message = "description is mandatory")
    @Size(min = 3, max = 50)
    private String description;

    @NotBlank(message = "price is mandatory")
    @DecimalMin(message = "should be a decimal number", value = "50")
    private double price;

    @NotBlank(message = "category is mandatory")
    private CategoryEntity category;

}
