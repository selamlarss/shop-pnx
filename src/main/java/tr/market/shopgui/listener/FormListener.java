package tr.market.shopgui.listener;

import org.powernukkitx.Player;
import org.powernukkitx.event.EventHandler;
import org.powernukkitx.event.Listener;
import org.powernukkitx.event.player.PlayerFormRespondedEvent;
import org.powernukkitx.form.response.SimpleResponse;
import org.powernukkitx.item.Item;
import tr.market.shopgui.Main;
import tr.market.shopgui.gui.MenuContext;
import tr.market.shopgui.gui.MenuType;
import tr.market.shopgui.gui.ShopGui;
import tr.market.shopgui.model.ShopCategory;
import tr.market.shopgui.model.ShopItem;

import java.util.ArrayList;

public class FormListener implements Listener {

    @EventHandler
    public void onFormResponse(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        MenuContext context = ShopGui.getSession(player);

        if (context == null) return;
        if (event.getResponse() == null || !(event.getResponse() instanceof SimpleResponse)) {
            ShopGui.removeSession(player);
            return;
        }

        SimpleResponse response = (SimpleResponse) event.getResponse();
        int clickedButtonIndex = response.buttonId();

        if (context.getType() == MenuType.MAIN) {
            ArrayList<ShopCategory> categories = new ArrayList<>(Main.getInstance().getShopManager().getCategories().values());
            if (clickedButtonIndex >= 0 && clickedButtonIndex < categories.size()) {
                ShopGui.openCategory(player, categories.get(clickedButtonIndex));
            }
        } else if (context.getType() == MenuType.CATEGORY) {
            ShopCategory category = context.getCategory();
            if (clickedButtonIndex >= 0 && clickedButtonIndex < category.getItems().size()) {
                ShopItem item = category.getItems().get(clickedButtonIndex);
                ShopGui.openConfirm(player, item);
            }
        } else if (context.getType() == MenuType.CONFIRM_BUY) {
            ShopItem item = context.getItem();
            if (clickedButtonIndex == 0) {
                if (Main.getInstance().getEconomyManager().reduceMoney(player, item.getBuyPrice())) {
                    player.getInventory().addItem(Item.get(item.getItemName(), item.getMeta()));
                    player.sendMessage("§aBaşarıyla " + item.getName() + " satın aldın!");
                } else {
                    player.sendMessage("§cYeterli paran yok!");
                }
                ShopGui.removeSession(player);
            } else if (clickedButtonIndex == 1) {
                Main.getInstance().getEconomyManager().addMoney(player, item.getSellPrice());
                player.sendMessage("§aBaşarıyla eşya sattın ve " + item.getSellPrice() + " kazandın!");
                ShopGui.removeSession(player);
            } else if (clickedButtonIndex == 2) {
                ShopGui.openCategory(player, context.getCategory());
            }
        }
    }
}