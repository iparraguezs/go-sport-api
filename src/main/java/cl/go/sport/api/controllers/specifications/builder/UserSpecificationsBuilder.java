package cl.go.sport.api.controllers.specifications.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import cl.go.sport.api.controllers.specifications.SearchCriteria;
import cl.go.sport.api.controllers.specifications.SearchOperation;
import cl.go.sport.api.controllers.specifications.UserSpecification;
import cl.go.sport.api.persistence.model.User;

public class UserSpecificationsBuilder {
	     
	private List<SearchCriteria> params;     
	
	public UserSpecificationsBuilder with(
        String key, String operation, Object value, String prefix, String suffix) {
       
          Optional<SearchOperation> findFirst = Arrays.asList(SearchOperation.values())
					  .stream()
					  .filter(o -> o.getValue().equals(operation))
					  .findFirst();
          if (findFirst.isPresent()) {
			SearchOperation op = findFirst.get();
			if (op != null) {
				if (op == SearchOperation.EQUALITY) {
					boolean startWithAsterisk = prefix.contains("*");
					boolean endWithAsterisk = suffix.contains("*");
					if (startWithAsterisk && endWithAsterisk) {
						op = SearchOperation.CONTAINS;
					} else if (startWithAsterisk) {
						op = SearchOperation.ENDS_WITH;
					} else if (endWithAsterisk) {
						op = SearchOperation.STARTS_WITH;
					}
				}
				params.add(new SearchCriteria(key, op, value));
			} 
		}
		return this;
      }
   
      public Specification<User> build() {
          if (params.size() == 0) {
              return null;
          }
   
          Specification<User> result = new UserSpecification(params.get(0));
        
          for (int i = 1; i < params.size(); i++) {
              result = params.get(i).isOrPredicate()
                ? Specification.where(result).or(new UserSpecification(params.get(i))) 
                : Specification.where(result).and(new UserSpecification(params.get(i)));
          }
   
          return result;
      }
}
