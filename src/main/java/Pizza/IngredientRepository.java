package Pizza;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

	List<Ingredient> findByName(@Param("name") String name);

}