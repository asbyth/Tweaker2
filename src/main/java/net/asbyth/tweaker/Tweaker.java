package net.asbyth.tweaker;

import net.asbyth.tweaker.command.TweakerCommand;
import net.asbyth.tweaker.gui.TweaksGui;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.File;

import static net.asbyth.tweaker.config.Settings.*;

@Mod(modid = Tweaker.MODID, name = Tweaker.NAME, version = Tweaker.VERSION)
public class Tweaker {

    static final String MODID = "tweaker_mod";
    static final String NAME = "Tweaker";
    static final String VERSION = "2.2";

    private boolean gui;

    private File configFile;

    @Mod.Instance(MODID)
    public static Tweaker INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TweakerCommand());
        configFile = new File(Minecraft.getMinecraft().mcDataDir, "config/tweaker.config");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        loadConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void saveConfig() {
        Configuration configuration = new Configuration(configFile);
        updateConfig(configuration, false);
        configuration.save();
    }

    private void loadConfig() {
        Configuration configuration = new Configuration(configFile);
        configuration.load();
        updateConfig(configuration, true);
    }

    private void updateConfig(Configuration configuration, boolean load) {
        Property prop = configuration.get("General", "nauseaEffect", false);
        if (load) {
            NAUSEAEFFECT = prop.getBoolean();
        } else {
            prop.setValue(NAUSEAEFFECT);
        }

        prop = configuration.get("General", "respawnButton", true);
        if (load) {
            RESPAWNBUTTON = prop.getBoolean();
        } else {
            prop.setValue(RESPAWNBUTTON);
        }

        prop = configuration.get("General", "armPosition", false);
        if (load) {
            ARMPOSITION = prop.getBoolean();
        } else {
            prop.setValue(ARMPOSITION);
        }

        prop = configuration.get("General", "instantWorldSwitching", true);
        if (load) {
            INSTANTWORLDSWITCHING = prop.getBoolean();
        } else {
            prop.setValue(INSTANTWORLDSWITCHING);
        }

        prop = configuration.get("General", "windowedFullscreen", false);
        if (load) {
            WINDOWEDFULLSCREEN = prop.getBoolean();
        } else {
            prop.setValue(WINDOWEDFULLSCREEN);
        }

        prop = configuration.get("General", "mouseDelayFix", false);
        if (load) {
            MOUSEDELAYFIX = prop.getBoolean();
        } else {
            prop.setValue(MOUSEDELAYFIX);
        }

        prop = configuration.get("General", "noCloseMyChat", false);
        if (load) {
            NOCLOSEMYCHAT = prop.getBoolean();
        } else {
            prop.setValue(NOCLOSEMYCHAT);
        }

        prop = configuration.get("General", "scoreboardCrash", true);
        if (load) {
            SCOREBOARDCRASH = prop.getBoolean();
        } else {
            prop.setValue(SCOREBOARDCRASH);
        }

        prop = configuration.get("General", "asyncScreenshots", false);
        if (load) {
            ASYNCSCREENSHOTS = prop.getBoolean();
        } else {
            prop.setValue(ASYNCSCREENSHOTS);
        }

        prop = configuration.get("General", "fullbright", false);
        if (load) {
            FULLBRIGHT = prop.getBoolean();
        } else {
            prop.setValue(FULLBRIGHT);
        }

        prop = configuration.get("General", "voidFlickerFix", false);
        if (load) {
            VOIDFLICKERFIX = prop.getBoolean();
        } else {
            prop.setValue(VOIDFLICKERFIX);
        }

        prop = configuration.get("General", "inventoryPotionEffectPosition", false);
        if (load) {
            INVENTORYPOTIONPOSITION = prop.getBoolean();
        } else {
            prop.setValue(INVENTORYPOTIONPOSITION);
        }

        prop = configuration.get("General", "raytraceBlocks", false);
        if (load) {
            RAYTRACEBLOCKS = prop.getBoolean();
        } else {
            prop.setValue(RAYTRACEBLOCKS);
        }
    }

    @SubscribeEvent
    public void render(TickEvent.RenderTickEvent event) {
        if (gui) {
            Minecraft.getMinecraft().displayGuiScreen(new TweaksGui());
            gui = false;
        }
    }

    public void gui() {
        gui = true;
    }
}
