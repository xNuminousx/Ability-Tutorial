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
	 * The event method.
	 * This specific event is looking for "PlayerAnimationEvent" which is triggered any time
	 * the server sees that the player has left-clicked. This is also triggered by other
	 * things but we are using it for the left-click function.
	 */
	@EventHandler
	public void onSwing(PlayerAnimationEvent event) {

		/*
		 * Variables to define.
		 * Here we need to create a player with ProjectKorra.
		 * We do this by grabbing whoever triggered the event, and then getting their bending details.
		 */
		Player player = event.getPlayer();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

		/*
		 * If this event has been cancelled or a player that triggered the event does not exist,
		 * then return.
		 */
		if (event.isCancelled() || bPlayer == null) {
			return;

		/*
		 * If the player exists and the event wasn't cancelled, but their bound ability is non-existent,
		 * then return;
		 * 
		 * This basically ensures that only players with bind abilities will be checked by the server.
		 */
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase(null)) {
			return;

		/*
		 * If the player's bound ability equals Tutorial, then progress AbilityClass.
		 */
		} else if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Tutorial")) {
			new AbilityClass(player);

		}
	}
}
