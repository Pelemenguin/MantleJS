package pelemenguin.mantlejs.item.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import pelemenguin.mantlejs.content.book.BookInitialization;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.item.LecternBookItem;

@ParametersAreNonnullByDefault
public class MantleJSBookItem extends LecternBookItem {

    private BookData bookData;

    public MantleJSBookItem(Properties properties) {
        super(properties);
    }

    public BookData getBookData() {
        if (this.bookData == null) {
            this.bookData = BookInitialization.BOOK_ITEMS.get(ForgeRegistries.ITEMS.getKey(this));
        }
        return this.bookData;
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
