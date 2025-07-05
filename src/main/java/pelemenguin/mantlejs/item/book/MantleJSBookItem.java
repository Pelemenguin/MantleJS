package pelemenguin.mantlejs.item.book;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import pelemenguin.mantlejs.content.book.RegisteredMantleJSBook;
import slimeknights.mantle.client.book.BookLoader;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.item.LecternBookItem;
import slimeknights.mantle.util.RegistryHelper;
import slimeknights.tconstruct.common.TinkerTags;

@ParametersAreNonnullByDefault
public class MantleJSBookItem extends LecternBookItem {

    private ResourceLocation bookDataLocation;
    private boolean allowOpenInGui = true;

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

    /** Checks if the given menu supports opening the menu */
    @SuppressWarnings("deprecation")
    public static boolean isValidContainer(AbstractContainerMenu menu) {
        // player inventory has a null type, which throws when used through the getter
        try {
            menu.getType();
        } catch (UnsupportedOperationException e) {
            return true;
        }
        // because vanilla set the throw precedent, add protection for other cases, just
        // in case
        // the try here is basically free
        try {
            return RegistryHelper.contains(BuiltInRegistries.MENU, TinkerTags.MenuTypes.TOOL_INVENTORY_REPLACEMENTS,
                    menu.getType());
        } catch (UnsupportedOperationException e) {
            return false;
        }
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

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack held, Slot slot, ClickAction action,
            Player player, SlotAccess access) {
        // on right-clicking the book with empty held, if this container allows we close
        // and reopen the book page
        if (allowOpenInGui) {
            if (action == ClickAction.SECONDARY && held.isEmpty() && slot.container == player.getInventory()
                    && slot.allowModification(player) && isValidContainer(player.containerMenu)) {
                if (player.level().isClientSide) {
                    player.containerMenu.resumeRemoteUpdates();
                    player.closeContainer();
                    this.getBookData().openGui(slot.getSlotIndex(), stack);
                }
                return true;
            }
        }
        return false;
    }

}
