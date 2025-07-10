package pelemenguin.mantlejs.content.book.transformer.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.client.book.data.AppearanceData;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.book.transformer.BookTransformer;
import slimeknights.mantle.client.screen.book.BookScreen;

@ParametersAreNonnullByDefault
public class BookDataHelper {

    @Info("Access original `BookData` object instead of this helper.\n\nNot suggested unless you do want further customization.")
    public BookData origin;

    public BookDataHelper(BookData origin) {
        this.origin = origin;
    }

    @Info("Get the sections of the book.")
    public ArrayList<SectionDataHelper> getSections() {
        ArrayList<SectionDataHelper> result = new ArrayList<>();
        for (SectionData s : this.origin.sections) {
            result.add(new SectionDataHelper(s, this));
        }
        return result;
    }

    @Info("Get Appearance Data of the book.")
    public AppearanceData getAppearance() {
        return this.origin.appearance;
    }

    @Info("Get the Font Renderer of the book.")
    public Font getFontRenderer() {
        return this.origin.fontRenderer;
    }

    @Info("Get the translate strings of the book.")
    public HashMap<String, String> getStrings() {
        return this.origin.strings;
    }

    public void reset() {
        this.origin.reset();
    }

    public void load() {
        this.origin.load();
    }

    @Info("Finds the section with the given name, ignoring advancements")
    @Nullable
    public SectionDataHelper findSection(String name) {
        return this.findSection(name, null);
    }

    @Info("Finds the section with the given name, advancement sensitive")
    @Nullable
    public SectionDataHelper findSection(String name, @Nullable BookScreen.AdvancementCache advancementCache) {
        SectionData found = this.origin.findSection(name, advancementCache);
        return found == null ? null : new SectionDataHelper(found, this);
    }

    @Info("Gets the number corresponding to the first page")
    public int getFirstPageNumber(SectionData section, @Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.getFirstPageNumber(section, advancementCache);
    }

    @Info("Gets the page data for the given page number")
    @Nullable
    public PageData findPage(int number, @Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.findPage(number, advancementCache);
    }
    @Info("Gets the page data for the given location")
    @Nullable
    public PageData findPage(String location, @Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.findPage(location, advancementCache);
    }

    @Info("Gets the number of individual pages")
    public int getPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.getPageCount(advancementCache);
    }

    @Info("Gets the number of pages the book can be on, effectively half the individual page count")
    public int getFullPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.getFullPageCount(advancementCache);
    }

    @Info("Gets a list of all visible sections, sensitive to current advancements")
    public List<SectionData> getVisibleSections(@Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.getVisibleSections(advancementCache);
    }

    @Info("Translates the given string using the book language")
    public String translate(String string) {
        return this.origin.translate(string);
    }

    @Info("Generic method to open the book GUI")
    public void openGui(Component title, String page, @Nullable Consumer<String> pageUpdater) {
        this.origin.openGui(title, page, pageUpdater);
    }
    @Info("Generic method to open the book GUI in a situation when the book can be picked up (i.e. lectern)")
    public void openGui(Component title, String page, @Nullable Consumer<String> pageUpdater, @Nullable Consumer<?> bookPickup) {
        this.openGui(title, page, pageUpdater, bookPickup);
    }
    @Info("Opens the GUI for a held book")
    public void openGui(InteractionHand hand, ItemStack stack) {
        this.openGui(hand, stack);
    }
    @Info("Opens the GUI for a held book")
    public void openGui(int slot, ItemStack stack) {
        this.openGui(slot, stack);
    }
    @Info("Opens the GUI for a lectern containing the book")
    public void openGui(BlockPos pos, ItemStack stack) {
        this.openGui(pos, stack);
    }

    @Info("Adds a new repository to the book")
    public void addRepository(@Nullable BookRepository repository) {
        this.origin.addRepository(repository);
    }

    @Info("Adds a new transformer to the book")
    public void addTransformer(@Nullable BookTransformer transformer) {
        this.origin.addTransformer(transformer);
    }

}