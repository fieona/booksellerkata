package be.xplorewood;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StockListTest {
    @Test
    void test1() {
        String[] art = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] cd = new String[]{"A", "B"};

        assertThat(StockList.stockSummary(art, cd)).isEqualTo("(A : 200) - (B : 1140)");
    }

    @Test
    void test_whenCategoriesAreEmpty_anEmptyStringIsReturned() {
        String[] bookInventoryByCode = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] categories = new String[]{};

        assertThat(StockList.stockSummary(bookInventoryByCode, categories)).isEmpty();
    }

    @Test
    void test_whenBookInventoryIsEmpty_anEmptyStringIsReturned() {
        String[] bookInventoryByCode = new String[]{};
        String[] categories = new String[]{"A", "B"};

        assertThat(StockList.stockSummary(bookInventoryByCode, categories)).isEmpty();
    }

    @Test
    void test_whenCategoryHasMultipleChars_AnErrorIsThrown() {
        String[] bookInventoryByCode = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] categories = new String[]{"ABC", "B"};

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> StockList.stockSummary(bookInventoryByCode, categories)).withMessage("Fubar");
    }
}
