package org.insmp.iNSMPCore.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.insmp.iNSMPCore.INSMPCore;

public class BlockInteractListener implements Listener {
    private final INSMPCore plugin;

    public BlockInteractListener(INSMPCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerUseBlock(PlayerInteractEvent e) {
        if (!this.plugin.getConfig().getBoolean("disable-ender-chests")) {
            return;
        }

        Player player = e.getPlayer();

        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.ENDER_CHEST && e.getAction().isRightClick()) {
            e.setCancelled(true);

            player.getScheduler().run(this.plugin, (task) -> {
                player.sendMessage(Component.text(this.plugin.getConfig().getString("disable-ender-chests-message", "Sorry! The use of ender chests are banned.")).color(NamedTextColor.RED));
            }, null);
        }
    }
}
