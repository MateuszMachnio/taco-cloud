package tacos.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import tacos.entity.Taco;
import tacos.repository.TacoRepository;
import tacos.web.api.TacoModel;
import tacos.web.api.TacoRepresentationModelAssembler;

@RepositoryRestController
public class RecentTacosController {

	private TacoRepository tacoRepo;
	
	public RecentTacosController (TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(path="/tacos/recent", produces="application/hal+json")
	public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
	
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		
		CollectionModel<TacoModel> tacoResources = new TacoRepresentationModelAssembler().toCollectionModel(tacos);
		tacoResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
		return new ResponseEntity<>(tacoResources, HttpStatus.OK);
	 }
	 
}
