package be.xplorewood;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StockListTest {
    @Test
    void test1() {
        String[] art = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] cd = new String[]{"A", "B"};

        assertThat(StockList.stockSummary(art, cd)).isEqualTo("(A : 200) - (B : 1140)");
    }
}
