package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import tacos.controller.DesignTacoController;
import tacos.entity.Taco;

public class TacoRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

	public TacoRepresentationModelAssembler() {
		super(DesignTacoController.class, TacoModel.class);
	}
	
	@Override 
	protected TacoModel instantiateModel(Taco taco) { 
		return new TacoModel(taco); 
	}
	
	@Override 
	public TacoModel toModel(Taco taco) {
		return createModelWithId(taco.getId(), taco);
	}
	
}
