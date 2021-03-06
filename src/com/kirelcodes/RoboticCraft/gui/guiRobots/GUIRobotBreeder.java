package com.kirelcodes.RoboticCraft.gui.guiRobots;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kirelcodes.RoboticCraft.configs.ConfigManager;
import com.kirelcodes.RoboticCraft.gui.GUI;
import com.kirelcodes.RoboticCraft.robot.RobotBreeder;
import com.kirelcodes.RoboticCraft.utils.ItemStackUtils;

public class GUIRobotBreeder extends GUIRobotBasic {

	private RobotBreeder robot;
	private ItemStack itemBreed, itemNoBreed;

	@Override
	public RobotBreeder getRobot() {
		return robot;
	}

	public GUIRobotBreeder(RobotBreeder robot) {
		super(robot);
		setSize(27);
		setTitle(ConfigManager.getLang("BreederGUIT"));
		instalizeInventory();
		fillInventory();
		this.robot = robot;
		itemBreed = ItemStackUtils.createItem(Material.WHEAT, "&aBreed");
		itemNoBreed = ItemStackUtils.createItem(Material.WHEAT, "&cStop Breed");

		getGUIAction().add(new GUIAction(itemBreed) {

			@Override
			public void actionNow(GUI gui, Player player) {
				((GUIRobotBreeder) gui).Breed();
			}
		});
		getGUIAction().add(new GUIAction(itemNoBreed) {

			@Override
			public void actionNow(GUI gui, Player player) {
				((GUIRobotBreeder) gui).noBreed();
			}
		});
		setRemovePos(3);
		setChestPos(13);
		setFollowPos(12);
		getInventory().setItem(14, (robot.isBreading()) ? itemNoBreed : itemBreed);
	}

	public void Breed() {
		robot.setBreeding(true);
		if (robot.isFollowing())
			noFollow(robot.getFollowTarget());
		getInventory().setItem(14, itemNoBreed);
	}

	@Override
	public void follow(Entity p) {
		super.follow(p);
		noBreed();
	}

	public void noBreed() {
		robot.setBreeding(false);
		getInventory().setItem(14, itemBreed);
	}

}
