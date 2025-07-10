package pelemenguin.mantlejs.content.book;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.NativeJavaClass;
import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.MantleJS;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.content.PageContent;

public interface BookPageInterface {

    @Info("Get Java classes of a specified id.")
    public static NativeJavaClass getClass(ResourceLocation type) {
        var clazz = BookLoader.getPageType(type);
        if (clazz == null) {
            return null;
        }
        return KubeJS.getStartupScriptManager().loadJavaClass(clazz.getName(), false);
    }

    @Info("Create an instance of Page Content.")
    public static PageContent ofType(ResourceLocation type) {
        return ofType(type, (content) -> {});
    }
    @Info("Create an instance of Page Content.\n\nSecond parameter accepts a consumer. You can initialize or add properties you want here.")
    public static PageContent ofType(ResourceLocation type, Consumer<PageContent> operation) {
        try {
            var clazz = BookLoader.getPageType(type);
            if (clazz == null) {
                return null;
            }
            var instance = clazz.getConstructor().newInstance();
            operation.accept(instance);
            return clazz.cast(instance);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e) {
            MantleJS.LOGGER.error("Fail to create Page Content", e);
            e.printStackTrace();
        }
        return null;
    }

}
