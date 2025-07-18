package pelemenguin.mantlejs.content.book;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.NativeJavaClass;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.page.MantleJSPageType;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.content.ContentError;
import slimeknights.mantle.client.book.data.content.PageContent;

public interface BookPageInterface {

    @Info("Get a Java class of a specified id.")
    public static NativeJavaClass getClass(ResourceLocation type) {
        var clazz = BookLoader.getPageType(type);
        if (clazz == null) {
            return null;
        }
        return KubeJS.getStartupScriptManager().loadJavaClass(clazz.getName(), false);
    }

    @Info("Create an instance of Page Content.")
    public static PageContent ofType(String type) {
        return ofType(type, (content) -> {});
    }
    @Info("Create an instance of Page Content.\n\nSecond parameter accepts a consumer. You can initialize or add properties you want here.")
    public static PageContent ofType(String pageType, Consumer<PageContent> operation) {
        ResourceLocation type = ResourceLocation.parse(KubeJS.appendModId(pageType));
        try {
            var clazz = BookLoader.getPageType(type);
            if (clazz == null) {
                // MantleJS.LOGGER.error("No such Page Type: "+type.toString());
                ConsoleJS.STARTUP.error("No such Page Type: "+type.toString());
                return createError("No such Page Type: "+type.toString());
            }
            var instance = clazz.getConstructor().newInstance();
            if (clazz == MantleJSPageType.class) {
                ((MantleJSPageType) instance).type = pageType;
            }
            operation.accept(instance);
            return instance;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e) {
            // MantleJS.LOGGER.error("Fail to create Page Content", e);
            ConsoleJS.STARTUP.error("Fail to create Page Content", e);
            return createError(e.toString());
        }
    }

    private static ContentError createError(String e) {
        return new ContentError(e);
    }

}
