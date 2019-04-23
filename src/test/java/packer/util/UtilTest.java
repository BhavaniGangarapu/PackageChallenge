package packer.util;

import com.google.common.collect.Lists;
import org.junit.Test;
import packer.domain.Item;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UtilTest {

    @Test
    public void maxCost() {
        assertThat(Util.totalCost(generateItems())).isEqualTo(200.0);
    }

    @Test
    public void maxWeight() {
        assertThat(Util.totalWeight(generateItems())).isEqualTo(60.0);

    }

    private List<Item> generateItems() {
        final Item item = Item.builder().index(1).cost(100.0).weight(30.0).build();
        return Lists.newArrayList(item, item);
    }

}