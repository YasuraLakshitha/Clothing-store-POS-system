package edu.icet.clothify.util.itemsizes;

import java.util.ArrayList;
import java.util.List;

public enum ItemSizes {
    XXS, XS, S, M, L, XL, XXL, XXXL;

    private final List<ItemSizes> itemSizesList;

    ItemSizes() {
        itemSizesList = new ArrayList<ItemSizes>();
    }

    public List<ItemSizes> getItemSizesList() {
        itemSizesList.add(XXS);
        itemSizesList.add(XS);
        itemSizesList.add(S);
        itemSizesList.add(M);
        itemSizesList.add(L);
        itemSizesList.add(XL);
        itemSizesList.add(XXL);
        itemSizesList.add(XXXL);
        return itemSizesList;
    }
}
