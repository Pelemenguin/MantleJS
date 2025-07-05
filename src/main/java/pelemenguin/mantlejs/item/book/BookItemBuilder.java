package pelemenguin.mantlejs.item.book;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class BookItemBuilder extends ItemBuilder {

    public BookItemBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public Item createObject() {
        return new MantleJSBookItem(createItemProperties());
    }
    
}
