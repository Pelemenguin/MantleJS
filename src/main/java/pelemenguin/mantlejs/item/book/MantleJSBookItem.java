package pelemenguin.mantlejs.item.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import pelemenguin.mantlejs.content.book.RegisteredMantleJSBook;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.item.LecternBookItem;

@ParametersAreNonnullByDefault
public class MantleJSBookItem extends LecternBookItem {

    private ResourceLocation bookDataLocation;

    public MantleJSBookItem(Properties properties, ResourceLocation bookDataLocation) {
        super(properties);
        this.bookDataLocation = bookDataLocation;
    }

    public ResourceLocation getBookDataLocation() {
        return this.bookDataLocation;
    }

    public BookData getBookData() {
        // return bookData == null ?
        // (BookInitialization.MANTLEJS_BOOKS.containsKey(this.bookDataLocation) ?
        // BookInitialization.MANTLEJS_BOOKS.get(this.bookDataLocation) : ) :
        return RegisteredMantleJSBook.MANTLEJS_BOOKS.containsKey(this.bookDataLocation)
                ? RegisteredMantleJSBook.MANTLEJS_BOOKS.get(this.bookDataLocation)
                : BookLoader.registerBook(this.bookDataLocation);
    }

    @Override
    public void openLecternScreenClient(BlockPos pos, ItemStack stack) {
        this.getBookData().openGui(pos, stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (world.isClientSide) {
            this.getBookData().openGui(hand, stack);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

}
