package tr.market.shopgui.model;

public class ShopItem {
    private final String name;
    private final String itemName;
    private final int meta;
    private final double buyPrice;
    private final double sellPrice;

    public ShopItem(String name, String itemName, int meta, double buyPrice, double sellPrice) {
        this.name = name;
        this.itemName = itemName;
        this.meta = meta;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public String getName() { return name; }
    public String getItemName() { return itemName; }
    public int getMeta() { return meta; }
    public double getBuyPrice() { return buyPrice; }
    public double getSellPrice() { return sellPrice; }
}