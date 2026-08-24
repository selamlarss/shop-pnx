package tr.market.shopgui.gui;

import org.powernukkitx.Player;
import org.powernukkitx.form.element.simple.ButtonImage;
import org.powernukkitx.form.element.simple.ElementButton;
import org.powernukkitx.form.window.SimpleForm;
import tr.market.shopgui.Main;
import tr.market.shopgui.model.ShopCategory;
import tr.market.shopgui.model.ShopItem;

import java.util.HashMap;
import java.util.Map;

public class ShopGui {
    private static final Map<Player, MenuContext> sessions = new HashMap<>();

    public static void openMainShop(Player player) {
        SimpleForm window = new SimpleForm("Sunucu Mağazası", "Lütfen bir kategori seçin:");
        MenuContext context = new MenuContext(MenuType.MAIN);

        for (ShopCategory category : Main.getInstance().getShopManager().getCategories().values()) {
            if (category.getImage() != null && !category.getImage().isEmpty()) {
                window.addButton(category.getName(), new ButtonImage(ButtonImage.Type.PATH, category.getImage()));
            } else {
                window.addButton(category.getName());
            }
        }

        sessions.put(player, context);
        window.send(player);
    }

    public static void openCategory(Player player, ShopCategory category) {
        SimpleForm window = new SimpleForm(category.getName(), "Almak veya satmak istediğiniz eşyayı seçin:");
        MenuContext context = new MenuContext(MenuType.CATEGORY);
        context.setCategory(category);

        for (ShopItem item : category.getItems()) {
            window.addButton(item.getName() + "\n§8Al: " + item.getBuyPrice() + " | Sat: " + item.getSellPrice());
        }

        sessions.put(player, context);
        window.send(player);
    }

    public static void openConfirm(Player player, ShopItem item) {
        SimpleForm window = new SimpleForm(item.getName(), "Ne yapmak istiyorsun?\n\nFiyat - Alış: " + item.getBuyPrice() + ", Satış: " + item.getSellPrice());
        window.addButton("Satın Al");
        window.addButton("Eşya Sat");
        window.addButton("Geri Dön");

        MenuContext context = new MenuContext(MenuType.CONFIRM_BUY);
        context.setItem(item);
        sessions.put(player, context);

        window.send(player);
    }

    public static MenuContext getSession(Player player) {
        return sessions.get(player);
    }

    public static void removeSession(Player player) {
        sessions.remove(player);
    }
}