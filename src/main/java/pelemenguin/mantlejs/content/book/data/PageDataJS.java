package pelemenguin.mantlejs.content.book.data;

import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.KubeJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import pelemenguin.mantlejs.content.book.BookPageInterface;
import pelemenguin.mantlejs.content.book.page.MantleJSPageType;
import slimeknights.mantle.Mantle;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.data.content.ContentBlank;
import slimeknights.mantle.client.book.data.content.ContentError;
import slimeknights.mantle.client.book.data.content.PageContent;
import slimeknights.mantle.client.book.repository.BookRepository;

@ParametersAreNonnullByDefault
public class PageDataJS {

    public PageData origin;
    private SectionDataJS parent;

    public PageDataJS(PageData origin) {
        this(origin, origin.parent);
    }
    protected PageDataJS(PageData origin, @Nullable SectionData parent) {
        this.origin = origin;
        if (parent == null) {
            this.parent = null;
        } else {
            this.parent = new SectionDataJS(parent);
        }
    }
    protected PageDataJS(PageData origin, SectionDataJS parent) {
        this.origin = origin;
        this.parent = parent;
    }

    public static PageDataJS createNew(ResourceLocation type, Consumer<PageContent> operation) {
        PageDataJS result = new PageDataJS(new PageData(true));
        result.setType(type, operation);
        return result;
    }
    public static PageDataJS createNew(ResourceLocation type) {
        PageDataJS result = new PageDataJS(new PageData(true));
        result.setType(type);
        return result;
    }

    public static PageDataJS createNewCustom(String type, Object arguments) {
        PageDataJS result = new PageDataJS(new PageData(true));
        result.setCustomType(type, arguments);
        return result;
    }
    public static PageDataJS createNewCustom(String type) {
        return createNewCustom(type, new Object());
    }

    public static PageDataJS createEmpty() {
        PageDataJS result = new PageDataJS(new PageData(true));
        result.setType(ContentBlank.ID);
        return result;
    }

    public void setType(ResourceLocation type) {
        if (type == MantleJSPageType.ID) {
            throw new UnsupportedOperationException("You can not directly set the type to MantleJS's internal Page Type");
        }
        this.origin.type = type;
        this.origin.content = BookPageInterface.ofType(type.toString());
    }
    public void setType(ResourceLocation type, Consumer<PageContent> operation) {
        if (type == MantleJSPageType.ID) {
            throw new UnsupportedOperationException("You can not directly set the type to MantleJS's internal Page Type");
        }
        this.origin.type = type;
        this.origin.content = BookPageInterface.ofType(type.toString(), operation);
    }

    public void setCustomType(String type) {
        this.setCustomType(type, new Object());
    }
    @SuppressWarnings("removal")
    public void setCustomType(String type, Object arguments) {
        ResourceLocation rtype = new ResourceLocation(KubeJS.appendModId(type));
        if (!MantleJSPageType.BUILD_FUNCTIONS.containsKey(rtype)) {
            this.origin.type = Mantle.getResource("error");
            this.origin.content = new ContentError("No such custom page type: "+type);
            return;
        }
        this.origin.type = MantleJSPageType.ID;
        MantleJSPageType content = new MantleJSPageType(type);
        content.arguments = arguments;
        this.origin.content = content;
    }

    @Nullable
    @Info("Get the parent section of the page.")
    public SectionDataJS getParent() {
        if (this.origin.parent == null) {
            return null;
        }
        if (this.parent == null) {
            this.parent = new SectionDataJS(this.origin.parent);
        }
        else if (!this.parent.origin.equals(this.origin.parent)) {
            this.parent.origin = this.origin.parent;
        }
        return this.parent;
    }

    public void setParent(SectionDataJS parent) {
        this.origin.parent = parent.origin;
        this.parent = parent;
    }

    @Info("Get the source repository of the page.")
    public BookRepository getSource() {
        return this.origin.source;
    }

    public void setSource(BookRepository source) {
        this.origin.source = source;
    }

    @Info("Get the content of the page.")
    public PageContent getContent() {
        return this.origin.content;
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
