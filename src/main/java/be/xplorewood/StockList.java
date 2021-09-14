package be.xplorewood;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockList {

	// 1st parameter is the stocklist (L in example),
	// 2nd parameter is list of requestCategories (M in example)
	public static String stockSummary(String[] bookInventoryByCode, String[] requestCategories) {
		// your code here
		if (bookInventoryByCode.length == 0) {
			return "";
		}

		Map<BookCategory, Integer> bookCountPerCategory = Arrays.stream(requestCategories)
				.collect(Collectors.toMap(BookCategory::of, s -> 0));

		for (String bookInventoryItem : bookInventoryByCode) {
			BookCategory category = BookCategory.of(bookInventoryItem.charAt(0));
			bookCountPerCategory.computeIfPresent(category, (character, currentQuantity) -> {
				String[] codeAndQuantity = bookInventoryItem.split(" ");
				Integer quantity = Integer.valueOf(codeAndQuantity[1]);
				return currentQuantity + quantity;
			});
		}

		return bookCountPerCategory.entrySet().stream()
				.map(entry -> String.format("(%s : %s)", entry.getKey().getCategory(), entry.getValue()))
				.collect(Collectors.joining(" - "));
	}
}

class BookCategory {
	private char category;

	private BookCategory(char category) {
		this.category = category;
	}

	public static BookCategory of(String categoryAsString) {
		if (categoryAsString.length() != 1) {
			throw new RuntimeException("Fubar");
		}
		return new BookCategory(categoryAsString.charAt(0));
	}

	public static BookCategory of(char categoryChar) {
		return new BookCategory(categoryChar);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BookCategory that = (BookCategory) o;
		return category == that.category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category);
	}

	public char getCategory() {
		return category;
	}
}
