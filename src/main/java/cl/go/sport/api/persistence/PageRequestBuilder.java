package cl.go.sport.api.persistence;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class PageRequestBuilder {
	
	private static final String ORDER_SYMBOL_ASC = "+";

	private static final String ORDER_SYMBOL_DESC = "-";

	private static final int OFFSET = 1;

	@Value("${app.default.pagination.page-number}")
	private Integer pageNumber;
	
	@Value("${app.default.pagination.page-size}")
	private Integer pageSize;
	
	private static Integer defaultPageNumber;
	
	private static Integer defaultPageSize;

	private PageRequestBuilder() {
		// Do nothing
	}
	
	@PostConstruct
	private void initStatic()
	{
		PageRequestBuilder.setDefaultPageNumber(this.pageNumber);
		PageRequestBuilder.setDefaultPageSize(this.pageSize);
	}

	private static void setDefaultPageNumber(Integer defaultPageNumber) {
		PageRequestBuilder.defaultPageNumber = defaultPageNumber;
	}

	private static void setDefaultPageSize(Integer defaultPageSize) {
		PageRequestBuilder.defaultPageSize = defaultPageSize;
	}

	/**
	 * Constructs PageRequest
	 * 
	 * @param pageSize
	 * @param pageNumber
	 * @param sortingCriteria
	 * @return
	 */
	public static PageRequest getPageRequest(Integer pageSize, Integer pageNumber, String sortingCriteria) {

		Set<String> sortingFileds = new LinkedHashSet<>(
				Arrays.asList(StringUtils.split(StringUtils.defaultIfEmpty(sortingCriteria, ""), ",")));

		List<Order> sortingOrders = sortingFileds
				.stream()
				.map(PageRequestBuilder::getOrder)
				.collect(Collectors.toList());

		Sort sort = sortingOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortingOrders);

		int page = ObjectUtils.defaultIfNull(pageNumber, PageRequestBuilder.defaultPageNumber) - OFFSET;
		int size = ObjectUtils.defaultIfNull(pageSize, PageRequestBuilder.defaultPageSize);
		
		return PageRequest.of(page, size, sort);
	}

	private static Order getOrder(String value) {

		if (StringUtils.startsWith(value, ORDER_SYMBOL_DESC)) {
			return new Order(Direction.DESC, StringUtils.substringAfter(value, ORDER_SYMBOL_DESC));
		} else if (StringUtils.startsWith(value, ORDER_SYMBOL_ASC)) {
			return new Order(Direction.ASC, StringUtils.substringAfter(value, ORDER_SYMBOL_ASC));
		} else {
			// Sometimes '+' from query param can be replaced as ' '
			return new Order(Direction.ASC, StringUtils.trim(value));
		}

	}
}
