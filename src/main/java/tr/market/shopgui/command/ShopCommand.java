package tr.market.shopgui.command;

import org.powernukkitx.Player;
import org.powernukkitx.command.Command;
import org.powernukkitx.command.CommandExecutor;
import org.powernukkitx.command.CommandSender;
import tr.market.shopgui.Main;
import tr.market.shopgui.gui.ShopGui;

public class ShopCommand implements CommandExecutor {

    private final Main plugin;

    public ShopCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cBu komutu sadece oyuncular kullanabilir.");
            return true;
        }

        sender.sendMessage("§aMağaza açılıyor...");
        ShopGui.openMainShop((Player) sender);
        return true;
    }
}
