package pelemenguin.mantlejs.content.book.page;

import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.BookLoader;

public class PageTypeInitialization {

    public static void initPageTypes() {
        for (ResourceLocation l : PageTypeBuilder.PAGE_TYPE_BUILDERS.keySet()) {
            MantleJSPageType.BUILD_FUNCTIONS.put(l, PageTypeBuilder.PAGE_TYPE_BUILDERS.get(l).function);
            MantleJS.LOGGER.debug("Page Type registered: "+l.toString());
        }
        
        try {
            BookLoader.registerPageType(ResourceLocation.fromNamespaceAndPath(KubeJS.MOD_ID, "custom"), MantleJSPageType.class);
        } catch (IllegalArgumentException e) {}
    }

}
