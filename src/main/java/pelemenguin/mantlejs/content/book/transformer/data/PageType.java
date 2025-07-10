package pelemenguin.mantlejs.content.book.transformer.data;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.data.content.PageContent;

public interface PageType {
    
    public static final Map<ResourceLocation, Class<? extends PageContent>> AVAILABLE_PAGE_TYPES = new HashMap<>();

    public static void addPageType(ResourceLocation represetation, Class<? extends PageContent> pageTypeClass) {
        AVAILABLE_PAGE_TYPES.put(represetation, pageTypeClass);
    }

    public static Class<? extends PageContent> byId(ResourceLocation id) {
        return AVAILABLE_PAGE_TYPES.get(id);
    }

    public static void reset() {
        AVAILABLE_PAGE_TYPES.clear();
    }

}
