package tacos.web.api;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;

public class IngredientModel extends RepresentationModel<IngredientModel> {

	@Getter
	private final String name;
	
	@Getter
	private final Type type;
	
	public IngredientModel(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}
