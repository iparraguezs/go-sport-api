package cl.go.sport.api.controllers.specifications;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchOperation {

	EQUALITY(":"), NEGATION("!"), GREATER_THAN(">"), LESS_THAN("<"), LIKE("~"), STARTS_WITH(""), ENDS_WITH(""), CONTAINS("");
	
	private String value;

	public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~" };
}
