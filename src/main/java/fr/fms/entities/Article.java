package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "T_Articles")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String brand;

	@NotNull
	@Size(min = 10, max = 50)
	private String description;

	@DecimalMin("50")
	private double price;
	
	@ManyToOne
	private Category category;

}
