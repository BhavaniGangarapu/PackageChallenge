package packer.domain.builders;

import packer.domain.Item;

import static java.lang.Double.parseDouble;

public class ItemBuilder {

    public static Item mapToPakcage(String[] item) {
        return Item.builder()
                .index(Integer.valueOf(item[0]))
                .weight(parseDouble(item[1]))
                .cost(parseDouble(item[2].replaceAll("€", "")))
                .build();
    }
}
