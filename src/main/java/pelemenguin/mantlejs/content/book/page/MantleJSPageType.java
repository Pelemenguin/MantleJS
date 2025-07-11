package pelemenguin.mantlejs.content.book.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import pelemenguin.mantlejs.content.book.data.BookDataJS;
import pelemenguin.mantlejs.content.book.page.PageTypeBuilder.BuildFunction;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.mantle.client.screen.book.element.BookElement;

@ParametersAreNonnullByDefault
public class MantleJSPageType extends PageContent {
    
    public static final ResourceLocation ID = new ResourceLocation(KubeJS.MOD_ID, "custom");
    public static final HashMap<ResourceLocation, BuildFunction> BUILD_FUNCTIONS = new HashMap<>();
    
    public String type;
    // public Map<String, Object> arguments;

    public MantleJSPageType() {
    }
    public MantleJSPageType(String id) {
        this.type = id;
    }

    @Override
    public void build(BookData data, ArrayList<BookElement> elements, boolean isRightSide) {
        try {
            BUILD_FUNCTIONS.get(ResourceLocation.parse(KubeJS.appendModId(this.type))).build(new BookDataJS(data), elements, isRightSide);
        } catch (NullPointerException e) {
            MantleJS.LOGGER.error("No such page type: "+this.type.toString(), e);
            ConsoleJS.STARTUP.error("No such page type: "+this.type.toString(), e);
        }
    }

}
