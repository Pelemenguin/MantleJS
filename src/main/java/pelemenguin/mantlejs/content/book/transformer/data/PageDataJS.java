package pelemenguin.mantlejs.content.book.transformer.data;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.mantle.client.book.data.PageData;

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
