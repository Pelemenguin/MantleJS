package pelemenguin.mantlejs.content.book;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.data.BookData;

public interface RegisteredMantleJSBook {
    // List of all registered book from KubeJS
    public static final Map<ResourceLocation, BookData> MANTLEJS_BOOKS = new HashMap<>();
}
