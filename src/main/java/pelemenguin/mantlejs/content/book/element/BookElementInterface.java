package pelemenguin.mantlejs.content.book.element;

import java.util.List;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.data.element.ImageData;
import slimeknights.mantle.client.book.data.element.TextComponentData;
import slimeknights.mantle.client.book.data.element.TextData;
import slimeknights.mantle.client.screen.book.ArrowButton;
import slimeknights.mantle.client.screen.book.element.AnimationToggleElement;
import slimeknights.mantle.client.screen.book.element.ArrowElement;
import slimeknights.mantle.client.screen.book.element.ImageElement;
import slimeknights.mantle.client.screen.book.element.ItemElement;
import slimeknights.mantle.client.screen.book.element.ListingLeftElement;
import slimeknights.mantle.client.screen.book.element.PageIconLinkElement;
import slimeknights.mantle.client.screen.book.element.SelectionElement;
import slimeknights.mantle.client.screen.book.element.SizedBookElement;
import slimeknights.mantle.client.screen.book.element.StructureElement;
import slimeknights.mantle.client.screen.book.element.TextComponentElement;
import slimeknights.mantle.client.screen.book.element.TextElement;
import slimeknights.mantle.client.screen.book.element.TooltipElement;

@ParametersAreNonnullByDefault
public class BookElementInterface {

    // TODO: ArrowType access
    public static AnimationToggleElement animationToggle(int x, int y, ArrowButton.ArrowType arrowType, int arrowColor, int arrowColorHover, int arrowColorActive, StructureElement structureElement) {
        return new AnimationToggleElement(x, y, arrowType, arrowColor, arrowColorHover, arrowColorActive, structureElement);
    }

    public static ArrowElement arrow(int x, int y, ArrowButton.ArrowType arrowType, int arrowColor, int arrowColorHover, OnPress iPressable) {
        return new ArrowElement(x, y, arrowType, arrowColor, arrowColorHover, iPressable);
    }

    public static ImageElement image(ImageData image) {
        return new ImageElement(image);
    }
    public static ImageElement image(ImageData image, int colorMultiplier) {
        return new ImageElement(image, colorMultiplier);
    }
    public static ImageElement image(int x, int y, int width, int height, ImageData image) {
        return new ImageElement(x, y, width, height, image);
    }
    public static ImageElement image(int x, int y, int width, int height, ImageData image, int colorMultiplier) {
        return new ImageElement(x, y, width, height, image, colorMultiplier);
    }

    public static ItemElement item(int x, int y, float scale, ItemStack... itemCycle) {
        return new ItemElement(x, y, scale, itemCycle);
    }
    public static ItemElement item(int x, int y, float scale, ItemStack[] itemCycle, @Nullable String action) {
        return new ItemElement(x, y, scale, itemCycle, action);
    }

    public static ListingLeftElement listingLeft(int x, int y, int width, int height, boolean subSection, TextData... text) {
        return new ListingLeftElement(x, y, width, height, subSection, text);
    }

    public static PageIconLinkElement pageIconLink(int x, int y, SizedBookElement displayElement, Component name, PageData pageData) {
        return new PageIconLinkElement(x, y, displayElement, name, pageData);
    }
    public static PageIconLinkElement pageIconLink(int x, int y, int w, int h, SizedBookElement displayElement, Component name, PageData pageData) {
        return new PageIconLinkElement(x, y, w, h, displayElement, name, pageData);
    }

    public static SelectionElement selection(int x, int y, SectionData section) {
        return new SelectionElement(x, y, section);
    }

    // TODO: StructureTemplate access
    public static StructureElement structure(int x, int y, int width, int height, StructureTemplate template, List<StructureTemplate.StructureBlockInfo> structure) {
        return new StructureElement(x, y, width, height, template, structure);
    }

    public static TextComponentElement textComponent(int x, int y, int width, int height, TextComponentData... text) {
        return new TextComponentElement(x, y, width, height, text);
    }

    public static TextElement text(int x, int y, int width, int height, TextData... text) {
        return new TextElement(x, y, width, height, text);
    }

    public static TooltipElement tooltip(List<Component> tooltip, int x, int y, int width, int height) {
        return new TooltipElement(tooltip, x, y, width, height);
    }

}
