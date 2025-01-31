package com.rndunel.jumpfeather.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.List;

public class FlyCommand  implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
    Player target = null; 
    if (args.length>0) {
      target = Bukkit.getPlayer(args[0]);
        if (target == null) {
          sender.sendMessage("Are you sure that the player is online? ");
          return false;

        }
      if (args.length>1) {
        return false;
      }

    }
    if (target==null) {
    if (!(sender instanceof Player player)) {

      sender.sendMessage("Sorry, but you must be a player to run this command");
        return true;
    }
    target = (Player) sender;
  }

  
   //  Player player = (Player) sender;
    boolean canFly = target.getAllowFlight();
    target.setAllowFlight(!canFly);
    sender.sendMessage(ChatColor.GREEN + "Flight " + (canFly ? "disabled" : "enabled") + ".");
   // if (!target.equals(sender)){target.sendMessage("Flight "+ ())}

    
    return true;
    
  }
}

