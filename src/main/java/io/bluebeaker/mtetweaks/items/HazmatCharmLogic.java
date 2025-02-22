package io.bluebeaker.mtetweaks.items;

import cofh.core.util.helpers.BaublesHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HazmatCharmLogic {
    public static HashMap<EntityLivingBase,Integer> protectedEntities = new HashMap<EntityLivingBase,Integer>();

    public static void onServerStop(FMLServerStoppingEvent event){
        protectedEntities.clear();
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event){
        if(event.side!= Side.SERVER || event.phase!=TickEvent.Phase.START)
            return;
        if(!protectedEntities.containsKey(event.player))
            return;
        int value = protectedEntities.get(event.player);
        if(value==0)
            protectedEntities.remove(event.player);
        else
            protectedEntities.put(event.player,value-1);
    }

    public static boolean onRadiation(EntityLivingBase entity){
        boolean isProtected = isEntityProtected(entity);
        if(!isProtected){
            isCharmActiveForEntity(entity);
        }
        isProtected = isEntityProtected(entity);
        return isProtected;
    }
    public static ItemStack getFirstCharm(EntityLivingBase entity){
        List<ItemStack> items = new ArrayList<ItemStack>();
        // Check baubles at first
        BaublesHelper.getBaubles(entity).forEach(items::add);
        // Then check equipment
        entity.getEquipmentAndArmor().forEach(items::add);
        // For players, check inventory
        if(entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            items.addAll(player.inventory.mainInventory);
            items.addAll(player.inventory.offHandInventory);
        }

        for(ItemStack stack:items){
            if(stack.getItem() instanceof ItemHazmatCharm)
                return stack;
        }

        return ItemStack.EMPTY;
    }

    public static void isCharmActiveForEntity(EntityLivingBase entity){
        ItemStack stack = getFirstCharm(entity);
        if(stack==null) return;
        if(stack.getItem() instanceof ItemHazmatCharm){
            if(MTETweaksItems.HAZMAT_CHARM.canUse(stack)){
                protectedEntities.put(entity,20);
                MTETweaksItems.HAZMAT_CHARM.tickPassive(stack);
            }
        }
    }

    public static boolean isEntityProtected(EntityLivingBase entity){
        return protectedEntities.containsKey(entity) && protectedEntities.get(entity)>0;
    }
}
