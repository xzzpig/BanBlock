package com.github.xzzpig.banblock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class BanBlockListener implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event){
		//place
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		Material blocked = event.getBlock().getType();
		if(Vars.all.get(world_s).contains(blocked.getId())||Vars.place.get(world_s).contains(blocked.getId())){
				if (player.hasPermission("banblock.ignore.*")||
					player.hasPermission("banblock.ignore.all")||
					player.hasPermission("banblock.ignore.place")||
					player.hasPermission("banblock.ignore.all."+world_s)||
					player.hasPermission("banblock.ignore.place."+world_s))
				{
					player.sendMessage("§4[警告！]");					
					player.sendMessage("§6[Banblock]§4你放置了方块:§e"+ blocked.name()+"("+blocked.getId()+")");
					player.sendMessage("§6[Banblock]§4此方块在 §6Banblock §4的禁止列表中");
				}
				else{
					event.setBuild(false);
					player.sendMessage("§6[Banblock]§4你不能放置这方块:§e"+ blocked.name()+"("+blocked.getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("§4[警告！]");					
				player.sendMessage("§6[Banblock]§4你在世界 "+ world_s +" 允许飞行");
				player.sendMessage("§6[Banblock]§4此世界在 §6Banblock §4的禁飞列表中");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("§6[Banblock]§4你不能在世界 "+ world_s +" 飞行");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreakBlock(BlockBreakEvent event){
		//break
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		Material blocked = event.getBlock().getType();
		if(Vars.all.get(world_s).contains(blocked.getId())||Vars.breaks.get(world_s).contains(blocked.getId())){
				if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.breaks")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.breaks."+world_s)){
					player.sendMessage("§4[警告！]");					
					player.sendMessage("§6[Banblock]§4你破坏了方块:§e"+ blocked.name()+"("+blocked.getId()+")");
					player.sendMessage("§6[Banblock]§4此方块在 §6Banblock §4的禁止列表中");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("§6[Banblock]§4你不能破坏这方块:§e"+ blocked.name()+"("+blocked.getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("§4[警告！]");					
				player.sendMessage("§6[Banblock]§4你在世界 "+ world_s +" 允许飞行");
				player.sendMessage("§6[Banblock]§4此世界在 §6Banblock §4的禁飞列表中");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("§6[Banblock]§4你不能在世界 "+ world_s +" 飞行");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	private void onPickup(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		ItemStack item = event.getItem().getItemStack();
		if(Vars.all.get(world_s).contains(item.getType().getId())||Vars.pickup.get(world_s).contains(item.getType().getId())){
				if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.pickup")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.pickup."+world_s)){
					player.sendMessage("§4[警告！]");					
					player.sendMessage("§6[Banblock]§4你捡起了物品:§e"+"("+item.getType().getId()+")");
					player.sendMessage("§6[Banblock]§4此物品在 §6Banblock §4的禁止列表中");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("§6[Banblock]§4你不能捡起物品:§e"+"("+item.getType().getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("§4[警告！]");					
				player.sendMessage("§6[Banblock]§4你在世界 "+ world_s +" 允许飞行");
				player.sendMessage("§6[Banblock]§4此世界在 §6Banblock §4的禁飞列表中");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("§6[Banblock]§4你不能在世界 "+ world_s +" 飞行");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void onclick(InventoryClickEvent event) {
		Player player = Bukkit.getPlayer(event.getWhoClicked().getName());
		String world_s = player.getLocation().getWorld().getName();
		ItemStack item = event.getCurrentItem();
		if(Vars.all.get(world_s).contains(item.getType().getId())||Vars.click.get(world_s).contains(item.getType().getId())){
				if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.click")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.click."+world_s)){
					player.sendMessage("§4[警告！]");					
					player.sendMessage("§6[Banblock]§4你点击了物品:§e"+"("+item.getType().getId()+")");
					player.sendMessage("§6[Banblock]§4此物品在 §6Banblock §4的禁止列表中");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("§6[Banblock]§4你不能点击物品:§e"+"("+item.getType().getId()+")");
				}
		}
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	private void onInteraction(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		ItemStack item ;
		if( player.getItemInHand().getTypeId()== 0)
			return;
		else
			item = event.getItem();
		if(Vars.all.get(world_s).contains(item.getType().getId())||Vars.pickup.get(world_s).contains(item.getType().getId())){
				if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.interaction")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.interaction."+world_s)){
					player.sendMessage("§4[警告！]");					
					player.sendMessage("§6[Banblock]§4你使用了物品:§e"+"("+item.getType().getId()+")");
					player.sendMessage("§6[Banblock]§4此物品在 §6Banblock §4的禁止列表中");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("§6[Banblock]§4你不能使用物品:§e"+"("+item.getType().getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("§4[警告！]");					
				player.sendMessage("§6[Banblock]§4你在世界 "+ world_s +" 允许飞行");
				player.sendMessage("§6[Banblock]§4此世界在 §6Banblock §4的禁飞列表中");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("§6[Banblock]§4你不能在世界 "+ world_s +" 飞行");
			}
		}
	}

	@EventHandler
	private void onChangeworld(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("§4[警告！]");					
				player.sendMessage("§6[Banblock]§4你在世界 "+ world_s +" 允许飞行");
				player.sendMessage("§6[Banblock]§4此世界在 §6Banblock §4的禁飞列表中");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("§6[Banblock]§4你不能在世界 "+ world_s +" 飞行");
			}
		}
	}
}