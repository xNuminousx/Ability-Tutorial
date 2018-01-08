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
 * extends FireAbility
 * This defines what element the addon will go under. There's also ChiAbility and AvatarAbility
 * aside from each of the 4 elements and their subelements.
 * 
 * implements AddonAbility
 * This is telling projectkorra that the this is an AddonAbility as opposed to
 * ComboAbility, PassiveAbility and SubAbility.
 * Notice: You should always implement AddonAbility, unless it's not an ability of course.
 * Example: For a ComboAbility you'd use "ComboAbility, AddonAbility"
 */
public class AbilityClass extends FireAbility implements AddonAbility {

	/*
	 * Variables you create can go here for organization.
	 * If you have your own way for organiztion, then use that.
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
		 * Custom method that we will define later.
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

	/*
	 * Place to define variables at the start of an ability.
	 * Notice the "setFields()" included here and in the constructor.
	 * You create variables above the constructor and here is where you define them.
	 */
	private void setFields() {
		/*
		 * We want to get a location that represents the start of the ability and use it for later.
		 */
		this.origin = player.getLocation().clone().add(0, 1, 0);
		
		/*
		 * Then we use another location variable so that we can tell the ability what to do.
		 */
		this.location = origin.clone();
		
		/*
		 * Since this is a "blast" ability we're going to get the players direction
		 * so that we tell the ability which direction to go in.
		 */
		this.direction = player.getLocation().getDirection();
	}

	/*
	 * Method that controls what the abilities does.
	 */
	@Override
	public void progress() {
		/*
		 * Makes sure the ability doesn't progress when there's no player. You could also make sure
		 * they don't switch worlds.
		 * English: If the player is dead or the player in not online, stop.
		 */
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		/*
		 * If the ability progresses beyond 20 blocks it will stop and be put on cooldown.
		 * English: If our "origin" variable is more than 20 blocks from our "location" variable, stop.
		 */
		if (origin.distance(location) > 20) {
			remove();
			return;
		}
		
		/*
		 * Updates the location every tick to go in the direction the player is looking, with a speed of 1.
		 */
		location.add(direction.multiply(1));
		
		/*
		 * Defines the particle effect that displays at every location point.
		 * Depending on your IDE, you should be able to hover over "display" to see
		 * what each of the variabels in the paranthesis mean.
		 */
		ParticleEffect.FLAME.display(location, 0, 0, 0, 0, 1);
		
		/*
		 * Stops the ability if it hits a block.
		 * English: If the location of the ability is equal to that of a block, stop.
		 */
		if (GeneralMethods.isSolid(location.getBlock())) {
			remove();
			return;
		}
		
		/*
		 * Loop that checks for entities wherever our "location" variable is.
		 * English: If there is ever an entity around the variable "location" call it "entity"
		 */
		for (Entity entity : GeneralMethods.getEntitiesAroundPoint(location, 1)) {
			/*
			 * The effects we apply to the "entity"
			 * English: If there is an entity which is living and is not equal to the player using the ability, 
			 * the entity will take damage and the ability will stop progressing.
			 */
			if ((entity instanceof LivingEntity) && entity.getUniqueId() != player.getUniqueId()) {
				DamageHandler.damageEntity(entity, 2, this);
				remove();
				return;
			}
		}
	}

	/*
	 * The duration of the cooldown.
	 */
	@Override
	public long getCooldown() {
		return 1000;
	}

	/*
	 * The location. Because this isn't necessary, it returns "null"
	 */
	@Override
	public Location getLocation() {
		return null;
	}

	/*
	 * The name of the ability.
	 * This will appear when using the /bending display commands, /bending who commands, and in a BendingBoard plugin
	 * you may or may not have.
	 */
	@Override
	public String getName() {
		return "Tutorial";
	}
	
	/*
	 * The description for the ability.
	 * Displays in /b h [abilityname]
	 */
	@Override
	public String getDescription() {
		return "A tutorial to help beginners learn to make ProjectKorra abilities!";
	}
	
	/*
	 * The instruction for the ability.
	 * Displays in /b h [abilityname]
	 */
	@Override
	public String getInstructions() {
		return "Check out the tutorial on the forums!";
	}

	/*
	 * The author of the ability.
	 * Displays in /b h [abilityname] in more recent versions of ProjectKorra.
	 * Also useful for putting credit in the getDescription() method or the load() method if you so choose.
	 */
	@Override
	public String getAuthor() {
		return "xNuminousx";
	}

	/*
	 * The version of the ability.
	 * Displays in /b h [abilityname] in more recent versions of ProjectKorra.
	 */
	@Override
	public String getVersion() {
		return "1.0";
	}

	/*
	 * Does this ability harm things?
	 * This is not necessary.
	 */
	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	/*
	 * Do you need to sneak for the ability (shift)?
	 * This is not necessary.
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
	 * Restart/reload
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
