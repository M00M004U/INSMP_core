package org.insmp.iNSMPCore.listeners;

import io.papermc.paper.event.entity.EntityPortalReadyEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.PortalType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.insmp.iNSMPCore.INSMPCore;

public class PortalListener implements Listener {
    private final INSMPCore plugin;

    public PortalListener(INSMPCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPortal (PlayerPortalEvent e) {
        PlayerTeleportEvent.TeleportCause cause = e.getCause();
        plugin.getLogger().info("Player Portal Event");

        boolean disableNether = cause == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL
                && this.plugin.getConfig().getBoolean("disable-nether");
        boolean disableEnd = cause == PlayerTeleportEvent.TeleportCause.END_PORTAL
                && this.plugin.getConfig().getBoolean("disable-end");

        if (disableNether || disableEnd) {
            e.setCancelled(true);
            e.setCanCreatePortal(false);

            Player player = e.getPlayer();

            player.getScheduler().run(this.plugin, (task) -> {
                player.sendMessage(Component.text("Sorry! You cannot travel there.").color(NamedTextColor.RED));
            }, null);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPortalReady(EntityPortalReadyEvent e) {
        plugin.getLogger().info("Entity Portal Ready Event");

        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        PortalType type = e.getPortalType();

        boolean disableNether = type == PortalType.NETHER
                && this.plugin.getConfig().getBoolean("disable-nether");
        boolean disableEnd = type == PortalType.ENDER
                && this.plugin.getConfig().getBoolean("disable-end");

        if (disableNether || disableEnd) {
            e.setCancelled(true);


            player.getScheduler().run(this.plugin, (task) -> {
                player.sendMessage(Component.text("Sorry! You cannot travel there.").color(NamedTextColor.RED));
            }, null);
        }
    }
}
