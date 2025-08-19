package pelemenguin.mantlejs.item.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import pelemenguin.mantlejs.content.book.RegisteredMantleJSBook;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.BookScreenOpener;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.item.AbstractBookItem;

@ParametersAreNonnullByDefault
public class MantleJSBookItem extends AbstractBookItem {

    private ResourceLocation bookDataLocation;

    public MantleJSBookItem(Properties properties, ResourceLocation bookDataLocation) {
        super(properties);
        this.bookDataLocation = bookDataLocation;
    }

    public ResourceLocation getBookDataLocation() {
        return this.bookDataLocation;
    }

    public BookData getBookData() {
        return RegisteredMantleJSBook.MANTLEJS_BOOKS.containsKey(this.bookDataLocation)
                ? RegisteredMantleJSBook.MANTLEJS_BOOKS.get(this.bookDataLocation)
                : BookLoader.registerBook(this.bookDataLocation);
    }

    @Override
    public BookScreenOpener getBook(ItemStack arg0) {
        return RegisteredMantleJSBook.MANTLEJS_BOOKS.get(this.bookDataLocation);
    }

}
