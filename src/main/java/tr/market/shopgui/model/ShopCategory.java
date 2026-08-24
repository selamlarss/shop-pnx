package tr.market.shopgui.model;

import java.util.List;

public class ShopCategory {
    private final String id;
    private final String name;
    private final String image;
    private final List<ShopItem> items;

    public ShopCategory(String id, String name, String image, List<ShopItem> items) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.items = items;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getImage() { return image; }
    public List<ShopItem> getItems() { return items; }
}