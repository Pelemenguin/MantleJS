package pelemenguin.mantlejs.content.book.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.client.book.data.AppearanceData;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.transformer.BookTransformer;
import slimeknights.mantle.client.screen.book.BookScreen;

@ParametersAreNonnullByDefault
public class MantleJSTransformer extends BookTransformer {

    protected static final Map<ResourceLocation, Consumer<BookDataHelper>> TRANSFORM_FUNCTIONS = new HashMap<>();

    public ResourceLocation id;

    public MantleJSTransformer(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public void transform(BookData data) {
        BookDataHelper helper = new BookDataHelper(data.sections, data.appearance, data.fontRenderer, data.strings);
        TRANSFORM_FUNCTIONS.get(this.id).accept(helper);
        data.sections = helper.sections;
        data.appearance = helper.appearance;
        data.fontRenderer = helper.fontRenderer;
        data.strings = helper.strings;
    }

    @SuppressWarnings("unused")
    public class BookDataHelper {

        public ArrayList<SectionData> sections;
        public AppearanceData appearance;
        public Font fontRenderer;
        public HashMap<String, String> strings;

        public BookDataHelper(ArrayList<SectionData> sections, AppearanceData appearance, Font fontRenderer, HashMap<String, String> strings) {
            this.sections = sections;
            this.appearance = appearance;
            this.fontRenderer = fontRenderer;
            this.strings = strings;
        }

        @Nullable
        @Info("Finds the section with the given name, ignoring advancements")
        public SectionData findSection(String name) {
            return this.findSection(name, null);
        }

        @Nullable
        @Info("Finds the section with the given name, ignoring advancements")
        public SectionData findSection(String name, @Nullable BookScreen.AdvancementCache advancementCache) {
            for (SectionData section : this.sections) {
                section.update(advancementCache);

                if (section.name.equals(name.toLowerCase())) {
                    return section.isUnlocked(advancementCache) ? section : null;
                }
            }

            return null;
        }

        @Info("Gets the number corresponding to the first page")
        public int getFirstPageNumber(SectionData section, @Nullable BookScreen.AdvancementCache advancementCache) {
            int pages = 0;

            for (SectionData sect : this.sections) {
                sect.update(advancementCache);

                if (section == sect) {
                    return section.isUnlocked(advancementCache) ? pages + 1 : -1;
                }

                if (!sect.isUnlocked(advancementCache)) {
                    continue;
                }

                pages += sect.getPageCount();
            }

            return -1;
        }

        @Nullable
        public PageData findPage(int number, @Nullable BookScreen.AdvancementCache advancementCache) {
            if (number < 0) {
                return null;
            }

            int pages = 0;

            for (SectionData section : this.sections) {
                section.update(advancementCache);

                if (!section.isUnlocked(advancementCache)) {
                    continue;
                }

                if (pages + section.getPageCount() > number) {
                    return section.pages.get(number - pages);
                } else {
                    pages += section.getPageCount();
                }
            }

            return null;
        }
        @Nullable
        public PageData findPage(String location, @Nullable BookScreen.AdvancementCache advancementCache) {
            return this.findPage(this.findPageNumber(location, advancementCache), advancementCache);
        }

        /** Gets the page number for the given location, ignoring advancements */
        public int findPageNumber(String location) {
            return this.findPageNumber(location, null);
        }
        /** Gets the page number for the given location, advancement sensitive */
        public int findPageNumber(String location, @Nullable BookScreen.AdvancementCache advancementCache) {
            location = location.toLowerCase();

            int pages = 0;

            if (!location.contains(".")) {
                return -1;
            }

            String sectionName = location.substring(0, location.indexOf('.'));
            String pageName = location.substring(location.indexOf('.') + 1);

            for (SectionData section : this.sections) {
                section.update(advancementCache);

                if (!section.isUnlocked(advancementCache)) {
                    continue;
                }

                if (!sectionName.equals(section.name)) {
                    pages += section.getPageCount();
                    continue;
                }

                for (PageData page : section.pages) {
                    if (!pageName.equals(page.name)) {
                        pages++;
                        continue;
                    }

                    return pages + 1;
                }
            }

            return -1;
        }

        /** Gets the number of individual pages */
        public int getPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
            int pages = 0;
            for (SectionData section : this.sections) {
                section.update(advancementCache);

                pages += section.isUnlocked(advancementCache) ? section.getPageCount() : 0;
            }
            return pages;
        }
        /**
         * Gets the number of pages the book can be on, effectively half the individual
         * page count
         */
        public int getFullPageCount(@Nullable BookScreen.AdvancementCache advancementCache) {
            return (int) Math.ceil((this.getPageCount(advancementCache) - 1) / 2F) + 1;
        }

        /** Gets a list of all visible sections, sensitive to current advancements */
        public List<SectionData> getVisibleSections(@Nullable BookScreen.AdvancementCache advancementCache) {
            List<SectionData> visible = new ArrayList<>();

            for (SectionData section : this.sections) {
                if (section.isUnlocked(advancementCache) || !section.hideWhenLocked) {
                    visible.add(section);
                }
            }

            return visible;
        }

        /** Translates the given string using the book language */
        public String translate(String string) {
            String out = this.strings.get(string);
            return out != null ? out : string;
        }

    }

}
