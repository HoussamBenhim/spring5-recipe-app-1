package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(exclude = {"recipe"} )
@Entity
public class Ingredient {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String descirption;
private BigDecimal amount;
@ManyToOne
private Recipe recipe;
@OneToOne(fetch = FetchType.EAGER)
private UnitOfMeasure uom;

public Ingredient(String descirption, BigDecimal amount, Recipe recipe, UnitOfMeasure uom) {
	super();
	this.descirption = descirption;
	this.amount = amount;
	this.recipe = recipe;
	this.uom = uom;
}

public Ingredient() {
	super();
}

}
