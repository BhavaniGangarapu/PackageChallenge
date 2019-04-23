package packer.util;

import com.google.common.base.Joiner;
import packer.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static double totalCost(List<Item> items) {
        return items.stream()
                .map(Item::getCost)
                .mapToDouble(Double::valueOf)
                .sum();
    }

    public static double totalWeight(List<Item> items) {
        return items.stream()
                .map(Item::getWeight)
                .mapToDouble(Double::valueOf)
                .sum();
    }

    public static String formatOutput(List<List<Item>> output) {
        StringBuilder join = new StringBuilder();
        for (List<Item> op : output) {
            if (op == null){
                join.append("- \n");
            }else{
                join.append(Joiner.on(", ").join(mapIndexToList(op)) + "\n");
            }
        }
        return join.toString();
    }

    private static List<Integer> mapIndexToList(List<Item> op) {
        return op.stream()
                .mapToInt(Item::getIndex)
                .boxed()
                .collect(Collectors.toList());
    }
}
