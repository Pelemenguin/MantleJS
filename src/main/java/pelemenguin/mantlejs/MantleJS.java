package pelemenguin.mantlejs;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pelemenguin.mantlejs.event.book.BookRegistryListener;

@SuppressWarnings("removal")
@Mod(MantleJS.MODID)
public class MantleJS {

    public static final String MODID = "mantlejs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MantleJS() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(BookRegistryListener.class);
    }

    public static ResourceLocation createLocation(String id) {
        return new ResourceLocation(MantleJS.MODID, id);
    }

    public static ResourceLocation createKubeJSLocation(String id) {
        return new ResourceLocation(KubeJS.MOD_ID, id);
    }

}