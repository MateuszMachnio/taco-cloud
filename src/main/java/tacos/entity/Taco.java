package tacos.entity;

import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Taco {

	@NotNull
	@Size
	private String name;
	private List<String> ingredients;
	
}
