package pelemenguin.mantlejs.content.book.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.data.content.ContentBlank;
import slimeknights.mantle.client.book.data.element.ImageData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.mantle.client.screen.book.BookScreen;

@ParametersAreNonnullByDefault
public class SectionDataJS {

    public SectionData origin;
    private BookDataJS parent;

    public SectionDataJS(SectionData origin) {
        this(origin, null);
    }
    public SectionDataJS(SectionData origin, @Nullable BookDataJS parent) {
        this.origin = origin;
        this.parent = parent;
    }

    // MantleJS Custom

    public void addPage(Consumer<PageDataJS> builder) {
        PageDataJS raw = new PageDataJS(new PageData(false), this);
        raw.setContent(new ContentBlank());
        builder.accept(raw);
        this.origin.pages.add(raw.origin);
    }

    @Nullable
    @Info("Get the parent book of the section.")
    public BookDataJS getParent() {
        if (this.parent == null) {
            this.parent = this.origin.parent == null ? null : new BookDataJS(this.origin.parent);
        }
        return this.parent;
    }

    @Info("Get the repository source of the sections.")
    public BookRepository getSource() {
        return this.origin.source;
    }

    @Info("Get a list of pages of the section")
    public ArrayList<PageData> getPages() {
        return this.origin.pages;
    }

    // Mantle's built-in methods

    public String translate(String string) {
        return this.origin.translate(string);
    }

    public void load() {
        this.origin.load();
    }

    public void update(@Nullable BookScreen.AdvancementCache advancementCache) {
        this.origin.update(advancementCache);
    }

    public String getTitle() {
        return this.origin.getTitle();
    }

    public int getPageCount() {
        return this.origin.getPageCount();
    }

    public boolean isUnlocked(@Nullable BookScreen.AdvancementCache advancementCache) {
        return this.origin.isUnlocked(advancementCache);
    }

    public static boolean requirementSatisfied(String requirement, @Nullable BookScreen.AdvancementCache advancementCache) {
        return SectionData.requirementSatisfied(requirement, advancementCache);
    }

    public boolean isConditionMet() {
        return this.origin.isConditionMet();
    }

    // Getters

    @Nullable
    public String getName() {
        return this.origin.name;
    }
    public void setName(String name) {
        this.origin.name = name;
    }

    public ImageData getIcon() {
        return this.origin.icon;
    }
    // TODO: Image Data customization
    public void setIcon(ImageData icon) {
        this.origin.icon = icon;
    } 

    public boolean isHideWhenUnlocked() {
        return this.origin.hideWhenLocked;
    }
    public void setHideWhenUnlocked(boolean hideWhenLocked) {
        this.origin.hideWhenLocked = hideWhenLocked;
    }

    public String getData() {
        return this.origin.data;
    }
    public void setData(String data) {
        this.origin.data = data;
    }

    public ICondition getCondition() {
        return this.origin.condition;
    }
    public void getCondition(ICondition condition) {
        this.origin.condition = condition;
    }

    public Map<ResourceLocation, JsonElement> getExtraData() {
        return this.origin.extraData;
    }

}
