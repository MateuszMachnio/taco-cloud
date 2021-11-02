package tacos.web.api;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import tacos.entity.Ingredient;
import tacos.entity.Taco;

public class TacoModel extends RepresentationModel<TacoModel> {

	private static final IngredientRepresentationModelAssembler ingredientAssembler = new IngredientRepresentationModelAssembler();
	
	@Getter
	private final String name;
	
	@Getter
	private final Date createdAt;
	
	@Getter
	private final CollectionModel<IngredientModel> ingredientModels;

	public TacoModel(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredientModels = ingredientAssembler.toCollectionModel(taco.getIngredients());
	}
	
}
