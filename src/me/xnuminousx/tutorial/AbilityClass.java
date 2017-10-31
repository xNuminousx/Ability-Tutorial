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

public class AbilityClass extends FireAbility implements AddonAbility {

	private Location location;
	private Location origin;
	private Vector direction;

	public AbilityClass(Player player) {
		super(player);
		
		if (bPlayer.isOnCooldown(this)) {
			return;
		}
		
		setFields();
		start();
		
		bPlayer.addCooldown(this);
	}

	private void setFields() {
		this.origin = player.getLocation().clone().add(0, 1, 0);
		this.location = origin.clone();
		this.direction = player.getLocation().getDirection();
	}

	@Override
	public void progress() {
		if (player.isDead() || !player.isOnline()) {
			remove();
			return;
		}
		
		if (origin.distance(location) > 20) {
			remove();
			return;
		}
		
		location.add(direction.multiply(1));
		ParticleEffect.FLAME.display(location, 0, 0, 0, 0, 1);
		
		if (GeneralMethods.isSolid(location.getBlock())) {
			remove();
			return;
		}
		
		for (Entity entity : GeneralMethods.getEntitiesAroundPoint(location, 1)) {
			if ((entity instanceof LivingEntity) && entity.getUniqueId() != player.getUniqueId()) {
				DamageHandler.damageEntity(entity, 2, this);
				remove();
				return;
			}
		}
	}

	@Override
	public long getCooldown() {
		return 1000;
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public String getName() {
		return "Tutorial";
	}
	
	@Override
	public String getDescription() {
		return "A tutorial to help beginners learn to make ProjectKorra abilities!";
	}
	
	@Override
	public String getInstructions() {
		return "Check out the tutorial on the forums!";
	}

	@Override
	public String getAuthor() {
		return "xNuminousx";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		return false;
	}

	@Override
	public void load() {
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new AbilityListener(), ProjectKorra.plugin);
		ProjectKorra.log.info("Successfully enabled " + getName() + " by " + getAuthor());
	}

	@Override
	public void stop() {
		ProjectKorra.log.info("Successfully disabled " + getName() + " by " + getAuthor());
		super.remove();
	}

}
