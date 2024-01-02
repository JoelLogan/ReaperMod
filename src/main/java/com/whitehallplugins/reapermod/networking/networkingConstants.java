package com.whitehallplugins.reapermod.networking;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.NoSuchElementException;

public class networkingConstants {

    public static final Identifier MOD_PACKET_ID = new Identifier("reapermod", "existing");
    public static final String MOD_ID = "reapermod";

    public static int[] modVersion() {
        try {
            if (FabricLoader.getInstance().getModContainer(MOD_ID).isPresent()) {
                String[] versionArrayString = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata()
                        .getVersion().getFriendlyString().split("\\.");
                int[] versionArrayInt = new int[versionArrayString.length];
                for (int i = 0; i < versionArrayString.length; i++) {
                    versionArrayInt[i] = Integer.parseInt(versionArrayString[i]);
                }
                return versionArrayInt;
            }
        } catch (NoSuchElementException e) {
            System.out.println(Text.translatable("reapermod.error.modversion"));
        }
        return null;
    }

}
