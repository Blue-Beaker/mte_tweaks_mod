package io.bluebeaker.mtetweaks.crop;

import ic2.api.crops.CropCard;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;

public class CropInfoHandler {
    public static void onUseCropnalyzer(EntityPlayer player, TileEntity tile){
        if(player==null) return;
        if(!(tile instanceof TileEntityCrop)) return;
        TileEntityCrop crop = (TileEntityCrop) tile;
        player.sendMessage(new TextComponentTranslation("message.mtetweaks.cropinfo.info",
                getCurrentQuality(crop),getRequiredQuality(crop),
                crop.getTerrainHumidity(),crop.getTerrainNutrients(),crop.getTerrainAirQuality()));

        float growth = getGrowthRateModifier(crop);
        if(growth>=0){
            player.sendMessage(new TextComponentTranslation("message.mtetweaks.cropinfo.modifier",
                    growth));
        }else {
            player.sendMessage(new TextComponentTranslation("message.mtetweaks.cropinfo.insufficient",
                    growth));
        }
    }

    public static int getCurrentQuality(TileEntityCrop crop){
        CropCard cropCard = crop.getCrop();
        if(cropCard==null) return 0;
        return cropCard.getWeightInfluences(crop, crop.getTerrainHumidity(), crop.getTerrainNutrients(), crop.getTerrainAirQuality()) * 5;
    }
    public static int getRequiredQuality(TileEntityCrop crop){
        CropCard cropCard = crop.getCrop();
        if(cropCard==null) return 0;
        return getRequiredQuality(cropCard.getProperties().getTier(),crop.getStatGrowth(),crop.getStatGain(),crop.getStatResistance());
    }
    public static int getRequiredQuality(int tier, int growth, int gain, int resistance){
        return (tier - 1) * 4 + growth + gain + resistance;
    }

    /**
     * @param crop TileEntityCrop
     * @return Growth rate modifier. When <0, crop has a chance to die.
     */
    public static float getGrowthRateModifier(TileEntityCrop crop){
        int required = getRequiredQuality(crop);
        int current = getCurrentQuality(crop);
        if(required<current){
            return 1.0F * (100 + current - required) / 100;
        }else {
            int aux = (required - current);
            return 1.0F * (25 - aux) / 25;
        }
    }
}
