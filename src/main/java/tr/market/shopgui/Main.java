package tr.market.shopgui;

import org.powernukkitx.plugin.PluginBase;
import org.powernukkitx.utils.TextFormat;
import tr.market.shopgui.command.ShopCommand;
import tr.market.shopgui.manager.EconomyManager;
import tr.market.shopgui.manager.ShopManager;
import tr.market.shopgui.listener.FormListener;

public class Main extends PluginBase {

    private static Main instance;
    private ShopManager shopManager;
    private EconomyManager economyManager;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();

        this.economyManager = new EconomyManager();
        this.shopManager = new ShopManager();
        this.shopManager.loadShops();

        this.getServer().getPluginManager().registerEvents(new FormListener(), this);
        this.getPluginCommand("shop").setExecutor(new ShopCommand(this));
        this.getLogger().info(TextFormat.GREEN + "ShopGUI-PNX başarıyla aktif edildi!");
        this.getLogger().info(TextFormat.YELLOW + "Yüklenen kategori sayısı: " + this.shopManager.getCategories().size());
    }

    @Override
    public void onDisable() {
        this.getLogger().info(TextFormat.RED + "ShopGUI-PNX deaktif edildi.");
    }

    public static Main getInstance() {
        return instance;
    }

    public ShopManager getShopManager() {
        return shopManager;
    }

    public EconomyManager getEconomyManager() {
        return economyManager;
    }
}