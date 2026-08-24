package tr.market.shopgui.manager;

import cn.nukkit.Player;
// LlamaEconomy eklentisinin paket yolu (Sunucudaki sürümüne göre değişebilir ama genelde bu şekildedir)
import me.llama.economy.LlamaEconomy; 

public class EconomyManager {

    public double getBalance(Player player) {
        // LlamaEconomy ile bakiye sorgulama
        try {
            return LlamaEconomy.getInstance().getMoney(player.getName());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public boolean reduceMoney(Player player, double amount) {
        String playerName = player.getName();
        if (LlamaEconomy.getInstance().getMoney(playerName) >= amount) {
            LlamaEconomy.getInstance().reduceMoney(playerName, amount);
            return true;
        }
        return false;
    }

    public void addMoney(Player player, double amount) {
        LlamaEconomy.getInstance().addMoney(player.getName(), amount);
    }
}