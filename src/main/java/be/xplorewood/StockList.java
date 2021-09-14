package be.xplorewood;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static be.xplorewood.Stock.createStock;

public class StockList {

	// 1st parameter is the stocklist (L in example), = ABART 20
	// 2nd parameter is list of categories (M in example)
	public static String stockSummary(String[] stockList, String[] requestedCategories) {
		// your code here
		Map<Character, Integer> quantityPerCategory = Arrays.stream(stockList)
				.collect(Collectors.toMap(getCategoryFunction(), getQuantityForCategoryFunction(), Integer::sum));

		String summary = quantityPerCategory.entrySet().stream()
				.filter((entry) -> isRequestedCategory(requestedCategories, entry))
				.map(entry -> createStock(entry.getKey(), entry.getValue()))
				.map(Stock::summarize)
				.collect(Collectors.joining(" - "));
		return summary;
	}

	private static Function<String, Character> getCategoryFunction() {
		return bookCode -> bookCode.charAt(0);
	}

	private static Function<String, Integer> getQuantityForCategoryFunction() {
		return bookCode -> {
			String[] bookCodeAndQuantity = bookCode.split(" ");
			return Integer.valueOf(bookCodeAndQuantity[1]);
		};
	}

	private static boolean isRequestedCategory(String[] requestedCategories, Map.Entry<Character, Integer> quantityPerCategoryEntry) {
		String category = quantityPerCategoryEntry.getKey().toString();
		return Arrays.asList(requestedCategories).contains(category);
	}
}

class Stock {
	private Character category;
	private Integer quantity;

	private Stock(Character category, Integer quantity) {
		this.category = category;
		this.quantity = quantity;
	}

	public static Stock createStock(Character category, Integer quantity) {
		return new Stock(category, quantity);
	}

	public String summarize() {
		return String.format("(%s : %d)", category, quantity);
	}
}