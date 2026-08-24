package tr.market.shopgui.manager;

import org.powernukkitx.utils.Config;
import tr.market.shopgui.Main;
import tr.market.shopgui.model.ShopCategory;
import tr.market.shopgui.model.ShopItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShopManager {
    private final Map<String, ShopCategory> categories = new LinkedHashMap<>();

    public void loadShops() {
        categories.clear();
        Config config = Main.getInstance().getConfig();
        Map<String, Object> catMap = config.getSection("categories").getAll();

        for (String key : catMap.keySet()) {
            String name = config.getString("categories." + key + ".name");
            String image = config.getString("categories." + key + ".image");
            List<Map<?, ?>> rawItems = config.getMapList("categories." + key + ".items");
            List<ShopItem> items = new ArrayList<>();

            for (Map<?, ?> rawItem : rawItems) {
                Map<String, Object> itemMap = (Map<String, Object>) rawItem;
                String itemName = (String) itemMap.get("name");
                String id = (String) itemMap.get("item");
                int meta = ((Number) itemMap.getOrDefault("meta", 0)).intValue();
                double buy = ((Number) itemMap.getOrDefault("buy", 0.0)).doubleValue();
                double sell = ((Number) itemMap.getOrDefault("sell", 0.0)).doubleValue();

                items.add(new ShopItem(itemName, id, meta, buy, sell));
            }

            categories.put(key, new ShopCategory(key, name, image, items));
        }
    }

    public Map<String, ShopCategory> getCategories() {
        return categories;
    }
}