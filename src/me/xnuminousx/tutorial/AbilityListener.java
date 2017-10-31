package me.xnuminousx.tutorial;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import com.projectkorra.projectkorra.BendingPlayer;

/*
 * Implements Listener so that the server knows this is checking for events.
 */
public class AbilityListener implements Listener {

	/*
	 * Method that is called any time the given player left-clicks.
	 */
	@EventHandler
	public void onSwing(PlayerAnimationEvent event) {

		/*
		 * Variables to define.
		 * Grabbing the player by getting the player who triggered the event.
		 */
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		/*
		 * If this event has been cancelled or a player triggering the event does not exist, stop checking code.
		 */
		if (event.isCancelled() || bPlayer == null) {
			return;

		/*
		 * If the player exists and the event wasn't cancelled, but their bound ability is non-existent, stop checking code.	
		 */
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

		/*
		 * If the player's bound ability equals Tutorial, then run the code in the AbilityClass.	
		 */
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Tutorial")) {
			new AbilityClass(player);

		}
	}
}
