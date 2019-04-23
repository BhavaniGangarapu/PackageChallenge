package packer.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.google.common.base.MoreObjects.toStringHelper;


/**
 * Builder pattern because it provides clear separation between the construction and representation of an object.
 * Lombok : not needed getter and setter/methods
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private int index;
    private Double weight;
    private Double cost;

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("index", index)
                .add("weight", weight)
                .add("cost", cost)
                .toString();
    }
}
