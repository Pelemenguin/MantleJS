package pelemenguin.mantlejs.content.book.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.data.BookDataJS;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.transformer.BookTransformer;

@ParametersAreNonnullByDefault
public class MantleJSTransformer extends BookTransformer {

    protected static final Map<ResourceLocation, Consumer<BookDataJS>> TRANSFORM_FUNCTIONS = new HashMap<>();

    public ResourceLocation id;

    public MantleJSTransformer(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public void transform(BookData data) {
        BookDataJS helper = new BookDataJS(data);
        TRANSFORM_FUNCTIONS.get(this.id).accept(helper);
    }

}
