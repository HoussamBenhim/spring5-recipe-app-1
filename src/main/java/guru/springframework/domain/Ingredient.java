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

public Ingredient() {

}

public Ingredient(String descirption, BigDecimal amount, Recipe recipe, UnitOfMeasure uom) {
	super();
	this.descirption = descirption;
	this.amount = amount;
	this.recipe = recipe;
	this.uom = uom;
}
public UnitOfMeasure getUom() {
	return uom;
}
public void setUom(UnitOfMeasure uom) {
	this.uom = uom;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getDescirption() {
	return descirption;
}
public void setDescirption(String descirption) {
	this.descirption = descirption;
}
public BigDecimal getAmount() {
	return amount;
}
public void setAmount(BigDecimal amount) {
	this.amount = amount;
}
public Recipe getRecipe() {
	return recipe;
}
public void setRecipe(Recipe recipe) {
	this.recipe = recipe;
}

}
