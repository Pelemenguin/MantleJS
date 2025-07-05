package pelemenguin.mantlejs.content.book.transformer;

import slimeknights.mantle.client.book.transformer.BookTransformer;

public class BuiltinTransformer {
    
    public static BookTransformer indexTranformer() {
        return BookTransformer.indexTranformer();
    }

    public static BookTransformer contentTableTransformer() {
        return BookTransformer.contentTableTransformer();
    }

    public static BookTransformer contentTableTransformerForSection(String sectionName) {
        return BookTransformer.contentTableTransformerForSection(sectionName);
    }

    public static BookTransformer paddingTransformer() {
        return BookTransformer.paddingTransformer();
    }

}
