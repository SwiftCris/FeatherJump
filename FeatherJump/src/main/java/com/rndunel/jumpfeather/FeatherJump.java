package com.rndunel.jumpfeather;

import com.rndunel.jumpfeather.commands.FlyCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Entity;


import java.util.List;
//oolean noFallDamage = false;
public class FeatherJump extends JavaPlugin implements Listener {
private boolean noFallDamage = false; 

      @Override
          public void onEnable() {
         //  private boolean noFallDamage = false;
            
            ItemStack featherRecipe = new ItemStack(Material.FEATHER, 1);
            ItemMeta featherRecipeMeta = featherRecipe.getItemMeta();
            featherRecipeMeta.setDisplayName(ChatColor.GREEN + "Jump Feather");
            featherRecipeMeta.lore(List.of(
                  Component.text("This is the mythical feather"),
                  Component.text("which makes you jump")
                  ));
            featherRecipe.setItemMeta(featherRecipeMeta);
            

          
            ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "jump_feather"), featherRecipe);
            //public void createSpecialFeather() {
            recipe.shape("J","F");
            recipe.setIngredient('F',Material.FEATHER);
            recipe.setIngredient('J',Material.GLOWSTONE);
            if (Bukkit.getRecipe(recipe.getKey()) == null) {
            Bukkit.addRecipe(recipe);
            }
           // this.getCommand("fly").setExecutor(new FlyCommand())


            ItemStack blockoffeather = new ItemStack(Material.WHITE_WOOL, 1);
            ItemMeta blockoffeatherMeta = blockoffeather.getItemMeta();
            blockoffeatherMeta.setDisplayName(ChatColor.AQUA+"Block of Feather");
           /* blockoffeatherMeta.lore(Listof **/ 
                 /* Component.text("This is the mythical feather"),
                  Component.text("which makes you jump")
                  ));*/
            blockoffeather.setItemMeta(blockoffeatherMeta);
            

          
            ShapedRecipe recipeBlockOfFeather = new ShapedRecipe(new NamespacedKey(this, "block_of_feather"), blockoffeather);
            //public void createSpecialFeather() {
          recipeBlockOfFeather.shape("FFF","FFF","FFF");
          recipeBlockOfFeather.setIngredient('F',Material.FEATHER);
         //   recipe.setIngredient('J',Material.GLOWSTONE);
          if (Bukkit.getRecipe(recipeBlockOfFeather.getKey())==null) {
            Bukkit.addRecipe(recipeBlockOfFeather);
          }
            this.getCommand("fly").setExecutor(new FlyCommand());







       //     }

                    Bukkit.getPluginManager().registerEvents(this, this);
                        }

          @EventHandler
              public void onPlayerJoin(PlayerJoinEvent event) {
                        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
                            }

          @EventHandler
          public void onRightClick(PlayerInteractEvent event) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
              noFallDamage = true;
              ItemStack item = event.getItem();
              if (item != null && item.getType() == Material.FEATHER)  {
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() &&
                    item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Jump Feather")) {
                  Vector currentVelocity = event.getPlayer().getVelocity();
                  Vector velocity = new Vector(currentVelocity.getX(),5,currentVelocity.getZ());

                    event.getPlayer().setVelocity(velocity);
                    }
                    @ec

              }
            }

            }
          @EventHandler
          public void onPlayerDamage(EntityDamageEvent event) {
            Entity entity = event.getEntity();
            if (event.getCause() == DamageCause.FALL && (entity instanceof Player player) && noFallDamage) {
              noFallDamage = false;
              event.setCancelled(true);
            }
          }

      

          

         



}
              
          

