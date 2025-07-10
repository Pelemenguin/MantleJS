package pelemenguin.mantlejs;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pelemenguin.mantlejs.event.book.BookRegistryHandler;

@SuppressWarnings("removal")
@Mod(MantleJS.MODID)
public class MantleJS {

    public static final String MODID = "mantlejs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MantleJS() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(BookRegistryHandler.class);
    }

    public static ResourceLocation createLocation(String id) {
        return new ResourceLocation(MantleJS.MODID, id);
    }

    @SubscribeEvent
    public static void registerListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new BookRegistryHandler());
    }

}