package pelemenguin.mantlejs.content.book.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.resources.ResourceLocation;
import pelemenguin.mantlejs.content.book.transformer.data.BookDataHelper;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.transformer.BookTransformer;

@ParametersAreNonnullByDefault
public class MantleJSTransformer extends BookTransformer {

    protected static final Map<ResourceLocation, Consumer<BookDataHelper>> TRANSFORM_FUNCTIONS = new HashMap<>();

    public ResourceLocation id;

    public MantleJSTransformer(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public void transform(BookData data) {
        BookDataHelper helper = new BookDataHelper(data);
        TRANSFORM_FUNCTIONS.get(this.id).accept(helper);
    }

}
