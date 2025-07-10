package pelemenguin.mantlejs.content.book;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.NativeJavaClass;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.BookLoader;

public interface BookPageInterface {

    @Info("Get Java classes of a specified id.")
    public static NativeJavaClass getClass(ResourceLocation type) {
        var clazz = BookLoader.getPageType(type);
        if (clazz == null) {
            return null;
        }
        return KubeJS.getStartupScriptManager().loadJavaClass(clazz.getName(), false);
    }

}
