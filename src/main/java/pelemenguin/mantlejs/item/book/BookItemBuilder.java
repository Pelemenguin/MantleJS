package pelemenguin.mantlejs.item.book;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import pelemenguin.mantlejs.MantleJS;

public class BookItemBuilder extends ItemBuilder {

    private ResourceLocation bookDataLocation;

    public BookItemBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public Item createObject() {
        if (bookDataLocation == null) {
            this.bookDataLocation = MantleJS.createLocation("undefined");
        }
        return new MantleJSBookItem(createItemProperties(), this.bookDataLocation);
    }

    @Info("Set the book data of the book. This method is **necessary**.\n\nAccepts an id. This id should be the same as the one you have created in `MantleJSEvents.bookRegistry` event.")
    public BookItemBuilder setBookData(String id) {
        this.bookDataLocation = ResourceLocation.parse(KubeJS.appendModId(id));
        return this;
    }
    
}
