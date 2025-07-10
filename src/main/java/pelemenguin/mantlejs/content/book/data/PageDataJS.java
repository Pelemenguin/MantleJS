package pelemenguin.mantlejs.content.book.data;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.mantle.client.book.repository.BookRepository;

@ParametersAreNonnullByDefault
public class PageDataJS {

    public PageData origin;
    private SectionDataJS parent;

    public PageDataJS(PageData origin, @Nullable SectionDataJS parent) {
        this.origin = origin;
        this.parent = parent;
    }

    public PageDataJS(PageData origin) {
        this(origin, null);
    }

    @Nullable
    @Info("Get the parent section of the page.")
    public SectionDataJS getParent() {
        if (this.parent == null) {
            this.parent = this.origin.parent == null ? null : new SectionDataJS(this.origin.parent);
        }
        return this.parent;
    }

    @Info("Get the source repository of the page.")
    public BookRepository getSource() {
        return this.origin.source;
    }

    @Info("Get the content of the page.")
    public PageContent getContent() {
        return this.origin.content;
    }
    public void setContent(PageContent content) {
        this.origin.content = content;
    }

    public String translate(String string) {
        return this.origin.translate(string);
    }

    public void load() {
        this.origin.load();
    }

    public String getTitle() {
        return this.origin.getTitle();
    }

    public boolean isConditionMet() {
        return this.isConditionMet();
    }

    @Nullable
    public String getName() {
        return this.origin.name;
    }
    public void setName(String name) {
        this.origin.name = name;
    }

    public ResourceLocation getType() {
        return this.origin.type;
    }
    public void setType(ResourceLocation type) {
        this.origin.type = type;
    }

    public String getData() {
        return this.origin.data;
    }
    public void setData(String data) {
        this.origin.data = data;
    }

    public float getScale() {
        return this.origin.scale;
    }
    public void setScale(float scale) {
        this.origin.scale = scale;
    }

    public ICondition getCondition() {
        return this.origin.condition;
    }
    public void setCondition(ICondition condition) {
        this.origin.condition = condition;
    }

}
