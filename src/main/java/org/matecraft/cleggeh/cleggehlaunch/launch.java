package org.matecraft.cleggeh.cleggehlaunch;
 
import java.util.ArrayList;
 
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
 
public class launch extends JavaPlugin implements Listener {
 
        private final ArrayList<Player> jumpers = new ArrayList<>();
 
        @Override
        public void onEnable() {
                Bukkit.getServer().getPluginManager().registerEvents(this, this);
        }
       
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent e) {
                if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.TNT) {
                        e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(10));
                        e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.5D, e.getPlayer().getVelocity().getZ()));
                        jumpers.add(e.getPlayer());
                }
        }
       
        @EventHandler
        public void onPlayerDamage(EntityDamageEvent e) {
                if (e.getEntity() instanceof Player) {
                        Player p = (Player) e.getEntity();
                        if (e.getCause() == DamageCause.FALL && jumpers.contains(p)) {
                                e.setDamage(0.0);
                                jumpers.remove(p);
                        }
                }
        }
}
