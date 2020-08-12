package listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import serverOneController.WarpController;
import serverOneController.WarpPoint;
import util.ServerOneConfig;

public class VoidFallListener implements Listener{
	@EventHandler
	public void damageListener(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) return;
		if(!(event.getCause().equals(DamageCause.VOID))) return;
		Player player = (Player) event.getEntity();
		String world = new ServerOneConfig("config.yml").getString("Worlds.build");
		if(!player.getWorld().getName().equals(world)) return;
		player.sendMessage("§cUiuiui, da wärst du fast gestorben");
		player.setFallDistance(0);
		new WarpController(player).ToWarpPoint(WarpPoint.Spawn);
	}
}
