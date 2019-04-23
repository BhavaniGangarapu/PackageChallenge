package packer.domain.builders;

import org.junit.Test;
import packer.domain.Item;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class ItemBuilderTest {

    @Test
    public void mapToPakcage() {
        //given
        final String[] inputString = {"1", "53.38", "€45"};
        //when
        Item item = ItemBuilder.mapToPakcage(inputString);
        //then
        assertThat(item).isNotNull();
        assertThat(item.getIndex()).isEqualTo(1);
        assertThat(item.getWeight()).isEqualTo(53.38);
        assertThat(item.getCost()).isEqualTo(45.0);
    }
}