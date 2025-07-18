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
        this.origin = origin;
        this.parent = new BookDataJS(this.origin.parent);
    }
    protected SectionDataJS(SectionData origin, BookDataJS parent) {
        this.origin = origin;
        this.parent = parent;
    }

    // MantleJS Custom

    public void addPage(Consumer<PageDataJS> builder) {
        PageDataJS raw = new PageDataJS(new PageData(false), this);
        raw.setType(ContentBlank.ID);
        builder.accept(raw);
        this.origin.pages.add(raw.origin);
    }

    @Nullable
    @Info("Get the parent book of the section.")
    public BookDataJS getParent() {
        if (this.origin.parent == null) {
            return null;
        }
        if (this.parent == null) {
            this.parent = new BookDataJS(this.origin.parent);
        }
        else if (!this.parent.origin.equals(this.origin.parent)) {
            this.parent.origin = this.origin.parent;
        }
        return this.parent;
    }

    @Info("Get the repository source of the sections.")
    public BookRepository getSource() {
        return this.origin.source;
    }

    @Info("Get a list of pages of the section")
    public ArrayList<PageDataJS> getPages() {
        ArrayList<PageDataJS> result = new ArrayList<>();
        for (PageData p : this.origin.pages) {
            result.add(new PageDataJS(p, this));
        }
        return result;
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
