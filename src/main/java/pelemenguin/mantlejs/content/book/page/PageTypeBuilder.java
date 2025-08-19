package pelemenguin.mantlejs.content.book.page;

import java.util.ArrayList;
import java.util.HashMap;

import dev.latvian.mods.kubejs.KubeJS;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.data.BookDataJS;
import slimeknights.mantle.client.screen.book.element.BookElement;

public class PageTypeBuilder {

    public static final HashMap<ResourceLocation, PageTypeBuilder> PAGE_TYPE_BUILDERS = new HashMap<>();

    public String id;
    public BuildFunction function;

    @SuppressWarnings("removal")
    public PageTypeBuilder(String id) {
        this.id = KubeJS.appendModId(id);
        PAGE_TYPE_BUILDERS.put(new ResourceLocation(this.id), this);
    }

    public PageTypeBuilder buildPage(BuildFunction function) {
        this.function = function;
        return this;
    }

    @FunctionalInterface
    public interface BuildFunction {
        public void build(Object arguments, BookDataJS data, ArrayList<BookElement> elements, boolean isRightSide);
    }

}
