package me.xnuminousx.tutorial;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.FireAbility;
import com.projectkorra.projectkorra.util.DamageHandler;
import com.projectkorra.projectkorra.util.ParticleEffect;

/*
 * Extends FireAbility to define the element that the ability is for.
 * Implement AddonAbility to define this as an AddonAbility as opposed to ComboAbility or PassiveAbility.
 */
public class AbilityClass extends FireAbility implements AddonAbility {

	/*
	 * When creating a variable, put it here.
	 */
	private Location location;
	private Location origin;
	private Vector direction;

	/*
	 * The constructor used to determine who the player is and to start the ability.
	 */
	public AbilityClass(Player player) {
		super(player);
		
		/*
		 * Doesn't allow the ability to progress if it's on cooldown.
		 */
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		/*
		 * Method containing different variables set in the start of the ability.
		 */
		setFields();
		
		/*
		 * Starts the ability.
		 */
		start();
		
		/*
		 * Puts the ability on cooldown as soon as it starts.
		 */
		bPlayer.addCooldown(this);
	}

	private void setFields() {
		/*
		 * Place to define variables at the start of an ability.
		 */
		this.origin = player.getLocation().clone().add(0, 1, 0);
		this.location = origin.clone();
		this.direction = player.getLocation().getDirection();
	}

	@Override
	public void progress() {
		/*
		 * If the player dies or leaves the server in the middle of an ability it will stop.
		 */
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		/*
		 * If the ability's location is greater than 20 blocks from the origin it will stop.
		 */
		if (origin.distance(location) > 20) {
			remove();
			return;
		}
		
		/*
		 * Updates the location every tick to go forward in the direction the player is looking, with a speed of 1.
		 */
		location.add(direction.multiply(1));
		
		/*
		 * Defines the particle effect that displays at every location point.
		 */
		ParticleEffect.FLAME.display(location, 0, 0, 0, 0, 1);
		
		/*
		 * If the ability's location is equal to that of a block it will stop.
		 */
		if (GeneralMethods.isSolid(location.getBlock())) {
			remove();
			return;
		}
		
		/*
		 * Checks for any entities around the location of the entity.
		 */
		for (Entity entity : GeneralMethods.getEntitiesAroundPoint(location, 1)) {
			/*
			 * If there is an entity and it's living and it's not the player, damage that entity and stop.
			 */
			if ((entity instanceof LivingEntity) && entity.getUniqueId() != player.getUniqueId()) {
				DamageHandler.damageEntity(entity, 2, this);
				remove();
				return;
			}
		}
	}

	/*
	 * The cooldown duration.
	 */
	@Override
	public long getCooldown() {
		return 1000;
	}

	/*
	 * The location.
	 */
	@Override
	public Location getLocation() {
		return null;
	}

	/*
	 * The name of the ability.
	 */
	@Override
	public String getName() {
		return "Tutorial";
	}
	
	/*
	 * The description for the ability.
	 */
	@Override
	public String getDescription() {
		return "A tutorial to help beginners learn to make ProjectKorra abilities!";
	}
	
	/*
	 * The instruction for the ability.
	 */
	@Override
	public String getInstructions() {
		return "Check out the tutorial on the forums!";
	}

	/*
	 * The author of the ability.
	 */
	@Override
	public String getAuthor() {
		return "xNuminousx";
	}

	/*
	 * The version of the ability.
	 */
	@Override
	public String getVersion() {
		return "1.0";
	}

	/*
	 * Does this ability harm things?
	 */
	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	/*
	 * Do you need to sneak for the ability (shift)?
	 */
	@Override
	public boolean isSneakAbility() {
		return false;
	}

	/*
	 * This method is run whenever the ability is loaded into a server.
	 * Restart/reload
	 */
	@Override
	public void load() {
		/*
		 * Grabs information from the AbilityListener class so it knows when to start.
		 */
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new AbilityListener(), ProjectKorra.plugin);
		
		/*
		 * Log message that appears when the ability is loaded.
		 */
		ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor());
	}

	/*
	 * This method is run whenever the ability is disabled from a server.
	 * Stop/reload
	 */
	@Override
	public void stop() {
		/*
		 * Log message that appears when the ability is disabled.
		 */
		ProjectKorra.log.info("Successfully disabled " + getName() + " by " + getAuthor());
		
		/*
		 * When the server stops or reloads, the ability will stop what it's doing and remove.
		 */
		super.remove();
	}

}
