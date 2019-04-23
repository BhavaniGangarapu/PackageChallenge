package packer;


import packer.domain.Item;
import packer.domain.builders.ItemBuilder;
import packer.domain.exceptions.APIException;
import packer.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static packer.util.Util.formatOutput;


public class Packer {

    public static final int MAX_WEIGHT = 100;
    public static final String REGEX = "[(\\s:)]+";

    public static String pack(String filePath) throws APIException {
        List<List<Item>> packages = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(s -> {
                packages.add(process(s));
            });
        } catch (IOException e) {
            throw new APIException("Incorrect input / fileName");
        }
        return formatOutput(packages);
    }

    public static List<Item> process(String line) {
        List<Item> items = new ArrayList<>();
        String[] arr = line.split(": ");
        Double maxSize = Double.parseDouble(arr[0]);
        List<String> stringList = Arrays.asList(arr[1].trim().split(REGEX));
        stringList.subList(1, stringList.size())
                .forEach(item -> items.add(ItemBuilder.mapToPakcage(item.split(","))));
        return findBestPackage(maxSize, items);
    }

    private static List<Item> findBestPackage(Double maxSize, List<Item> itemList) {
        List<List<Item>> listOfItems = createPackages(itemList);
        List<Item> bestPackage = null;
        double maxCost = 0.0;
        double maxWeight = MAX_WEIGHT;
        for (List<Item> items : listOfItems) {
            double packageWeight = Util.totalWeight(items);
            if (packageWeight > maxSize) {
                continue;
            } else {
                double packageCost = Util.totalCost(items);
                if (packageCost > maxCost) {
                    maxCost = packageCost;
                    bestPackage = items;
                    maxWeight = packageWeight;
                } else if (packageCost == maxCost) {
                    if (packageWeight < maxWeight) {
                        maxCost = packageCost;
                        bestPackage = items;
                        maxWeight = packageWeight;
                    }
                }
            }
        }
        return bestPackage;
    }

    /**
     * Chose Iterative Algorithm  - "we keep generating the next combination from the current one until we have generated all combinations".
     * to generate all combinations of items because
     * 1. maximum packages can be only 15
     * 2. not having knowledge how many items can a package have
     *
     */
    static List<List<Item>> createPackages(List<Item> items) {
        List<List<Item>> packages = new ArrayList<>();
        for (Item currentItem : items) {
            int combinationSize = packages.size();
            for (int index = 0; index < combinationSize; index++) {
                List<Item> combination = packages.get(index);
                List<Item> newCombination = new ArrayList<>(combination);
                newCombination.add(currentItem);
                packages.add(newCombination);
            }
            ArrayList<Item> current = new ArrayList<>();
            current.add(currentItem);
            packages.add(current);
        }
        return packages;
    }

}
