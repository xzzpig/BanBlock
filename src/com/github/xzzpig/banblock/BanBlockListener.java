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
					player.sendMessage("��4[���棡]");					
					player.sendMessage("��6[Banblock]��4������˷���:��e"+ blocked.name()+"("+blocked.getId()+")");
					player.sendMessage("��6[Banblock]��4�˷����� ��6Banblock ��4�Ľ�ֹ�б���");
				}
				else{
					event.setBuild(false);
					player.sendMessage("��6[Banblock]��4�㲻�ܷ����ⷽ��:��e"+ blocked.name()+"("+blocked.getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("��4[���棡]");					
				player.sendMessage("��6[Banblock]��4�������� "+ world_s +" �������");
				player.sendMessage("��6[Banblock]��4�������� ��6Banblock ��4�Ľ����б���");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("��6[Banblock]��4�㲻�������� "+ world_s +" ����");
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
					player.sendMessage("��4[���棡]");					
					player.sendMessage("��6[Banblock]��4���ƻ��˷���:��e"+ blocked.name()+"("+blocked.getId()+")");
					player.sendMessage("��6[Banblock]��4�˷����� ��6Banblock ��4�Ľ�ֹ�б���");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("��6[Banblock]��4�㲻���ƻ��ⷽ��:��e"+ blocked.name()+"("+blocked.getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("��4[���棡]");					
				player.sendMessage("��6[Banblock]��4�������� "+ world_s +" �������");
				player.sendMessage("��6[Banblock]��4�������� ��6Banblock ��4�Ľ����б���");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("��6[Banblock]��4�㲻�������� "+ world_s +" ����");
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
					player.sendMessage("��4[���棡]");					
					player.sendMessage("��6[Banblock]��4���������Ʒ:��e"+"("+item.getType().getId()+")");
					player.sendMessage("��6[Banblock]��4����Ʒ�� ��6Banblock ��4�Ľ�ֹ�б���");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("��6[Banblock]��4�㲻�ܼ�����Ʒ:��e"+"("+item.getType().getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("��4[���棡]");					
				player.sendMessage("��6[Banblock]��4�������� "+ world_s +" �������");
				player.sendMessage("��6[Banblock]��4�������� ��6Banblock ��4�Ľ����б���");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("��6[Banblock]��4�㲻�������� "+ world_s +" ����");
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
					player.sendMessage("��4[���棡]");					
					player.sendMessage("��6[Banblock]��4��������Ʒ:��e"+"("+item.getType().getId()+")");
					player.sendMessage("��6[Banblock]��4����Ʒ�� ��6Banblock ��4�Ľ�ֹ�б���");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("��6[Banblock]��4�㲻�ܵ����Ʒ:��e"+"("+item.getType().getId()+")");
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
					player.sendMessage("��4[���棡]");					
					player.sendMessage("��6[Banblock]��4��ʹ������Ʒ:��e"+"("+item.getType().getId()+")");
					player.sendMessage("��6[Banblock]��4����Ʒ�� ��6Banblock ��4�Ľ�ֹ�б���");
				}
				else{
					event.setCancelled(true);
					player.sendMessage("��6[Banblock]��4�㲻��ʹ����Ʒ:��e"+"("+item.getType().getId()+")");
				}
		}
		//fly
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("��4[���棡]");					
				player.sendMessage("��6[Banblock]��4�������� "+ world_s +" �������");
				player.sendMessage("��6[Banblock]��4�������� ��6Banblock ��4�Ľ����б���");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("��6[Banblock]��4�㲻�������� "+ world_s +" ����");
			}
		}
	}

	@EventHandler
	private void onChangeworld(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		String world_s = player.getLocation().getWorld().getName();
		if(player.getAllowFlight()&&Vars.fly.get(world_s)){
			if (player.hasPermission("banblock.ignore.*")||player.hasPermission("banblock.ignore.all")||player.hasPermission("banblock.ignore.fly")||player.hasPermission("banblock.ignore.all."+world_s)||player.hasPermission("banblock.ignore.fly."+world_s)){
				player.sendMessage("��4[���棡]");					
				player.sendMessage("��6[Banblock]��4�������� "+ world_s +" �������");
				player.sendMessage("��6[Banblock]��4�������� ��6Banblock ��4�Ľ����б���");
			}
			else{
				player.setAllowFlight(false);
				player.sendMessage("��6[Banblock]��4�㲻�������� "+ world_s +" ����");
			}
		}
	}
}