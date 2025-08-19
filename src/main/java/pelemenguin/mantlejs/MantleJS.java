package pelemenguin.mantlejs;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import pelemenguin.mantlejs.event.book.BookRegistryHandler;

@SuppressWarnings("removal")
@Mod(MantleJS.MODID)
public class MantleJS {

    public static final String MODID = "mantlejs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MantleJS() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.register(BookRegistryHandler.class);
        }
    }

}