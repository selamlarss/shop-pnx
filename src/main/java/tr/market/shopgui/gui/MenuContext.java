package tr.market.shopgui.gui;

import tr.market.shopgui.model.ShopCategory;
import tr.market.shopgui.model.ShopItem;

public class MenuContext {
    private MenuType type;
    private ShopCategory category;
    private ShopItem item;
    private boolean isBuying;

    public MenuContext(MenuType type) {
        this.type = type;
    }

    public MenuType getType() { return type; }
    public void setType(MenuType type) { this.type = type; }

    public ShopCategory getCategory() { return category; }
    public void setCategory(ShopCategory category) { this.category = category; }

    public ShopItem getItem() { return item; }
    public void setItem(ShopItem item) { this.item = item; }

    public boolean isBuying() { return isBuying; }
    public void setBuying(boolean buying) { isBuying = buying; }
}