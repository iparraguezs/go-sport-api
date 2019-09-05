package cl.go.sport.api.controllers.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
	private String key;
    private SearchOperation operation;
    private Object value;
	public boolean isOrPredicate() {
		// TODO Auto-generated method stub
		return false;
	}
}
