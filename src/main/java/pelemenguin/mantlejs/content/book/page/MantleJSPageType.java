package pelemenguin.mantlejs.content.book.page;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.JsonIO;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.data.BookDataJS;
import pelemenguin.mantlejs.content.book.page.PageTypeBuilder.BuildFunction;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.mantle.client.screen.book.element.BookElement;

@ParametersAreNonnullByDefault
public class MantleJSPageType extends PageContent {
    
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(KubeJS.MOD_ID, "custom");
    public static final HashMap<ResourceLocation, BuildFunction> BUILD_FUNCTIONS = new HashMap<>();
    
    public String type;
    public JsonObject arguments;

    public MantleJSPageType() {
    }
    public MantleJSPageType(String id) {
        this.type = id;
    }

    @Override
    public void build(BookData data, ArrayList<BookElement> elements, boolean isRightSide) {
        try {
            BuildFunction func = BUILD_FUNCTIONS.get(ResourceLocation.parse(KubeJS.appendModId(this.type)));
            // MantleJS.LOGGER.debug("Build page with function: "+func);
            if (func == null) {
                ConsoleJS.STARTUP.error("No such type: "+type);
                return;
            }
            // MantleJS.LOGGER.debug("Function hash code: "+func.hashCode());
            func.build(JsonIO.toObject(this.arguments), new BookDataJS(data), elements, isRightSide);
        } catch (Exception e) {
            ConsoleJS.STARTUP.error("Fail to build page: "+this.type.toString(), e);
        }
    }

}
