package tacos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.data.repositoryInterface.IngredientRepository;
import tacos.data.repositoryInterface.TacoRepository;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Order;
import tacos.entity.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		model.addAttribute("taco", new Taco());
				
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
	
		Taco saved = tacoRepo.save(taco);
		order.addTaco(saved);
		
		return "redirect:/orders/current";
	}
	
	
	
	
	
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
		return ingredients.stream()
				.filter(ingredient -> ingredient.getType() == type)
				.collect(Collectors.toList());
	}
	
}
