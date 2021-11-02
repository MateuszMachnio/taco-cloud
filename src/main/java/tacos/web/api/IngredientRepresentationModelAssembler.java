package tacos.web.api;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import tacos.entity.Ingredient;

public class IngredientRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

	public IngredientRepresentationModelAssembler() {
		super(IngredientController2.class, IngredientModel.class);
	}
	
	@Override
	protected IngredientModel instantiateModel(Ingredient ingredient) {
		return new IngredientModel(ingredient);
	}

	@Override
	public IngredientModel toModel(Ingredient ingredient) {
		return createModelWithId(ingredient.getId(), ingredient);
	}

}
