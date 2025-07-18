[↑ Go Back](./toc.md)

# Transformers

You can add **Book Transformers** to further customize your book.

## Custom transformer

Book repositories may not always satisfy your requirements.
For example, Tinker's Construct's material list is not realizable using simply book repositories,
because they can not auto detect materials registered in Tinker's Construct.
So Tinker's Construct made this by creating a Book Transformer.

### Basic example

For the first example, we add 5 blank pages after each section.

```javascript
// Script type: STARTUP
MantleJSEvents.transformerRegistry(event => {
    event.create('add_blank_pages')
        .transform(bookData => {
            bookData.sections.forEach(section => {
                for (let i = 0; i < 5; i++) {
                    section.addPage(page => {
                        page.setType("mantle:blank");
                    });
                }
            });
        });
});
```

Let me explain this.

`event.create()` creates a new transformer.
`add_blank_pages` is the ID for the transformer which is used when creating books.

Then we call `transform()` method.
This method is used to set the transform function.
The function accepts a `BookData` as argument.

In `forEach`, we iter through each section of the book.
For each section, we added 5 pages.

`addPage` of a SectionDataJS required another Consumer.
Argument `page` of the consumer is initially a blank page.
But as a example, we set the type here.

### Adding contents

```javascript
section.addPage(page => {
    page.setType("mantle:blank");
});
```

In this part, we added a blank page.
But not every page type does not contain any data.

You can add another argument (a Consumer) after the Resource Location of the page type.

For example:

```javascript
section.addPage(page => {
    page.setType("mantle:text", content => {
        content.text = [BookTextData.literal("Text test")];
    });
});
```

This set the `text` of the content of the page to the given text.

> **Note**
>
> The `text` property of page type `mantle:text` is an array.
>
> `BookTextData` is a MantleJS's built-in interface to create Text Data.

## Add transformers to your book

Use `addTransformer` to add the transformer you've created above.

```javascript
MantleJSEvents.bookRegistry(event => {
    event.create("test")
        .addBookRepository("kubejs:book/test")
        .addTransformer("test_transformer");
});
```

Use `addJavaTransformer` to manually load a transformer from Java classes.

For example, if you have Tinker's Construct installed, use scripts below to add Tinker's Construct's built-in `TierRangeMaterialSectionTransformer`.

```javascript
const TierRangeMaterialSectionTransformer = Java.loadClass("slimeknights.tconstruct.library.client.book.sectiontransformer.materials.TierRangeMaterialSectionTransformer");
MantleJSEvents.bookRegistry(event => {
    event.create("test")
        .addBookRepository("kubejs:book/test")
        .addJavaTransformer(TierRangeMaterialSectionTransformer.INSTANCE)
        .addTransformer("test_transformer");
})
```

> **Doc Links**
> - [TransformerRegistryEvent](../../docs/event/TransformerRegistryEventJS.md)
> - [BookDataJS](../../docs/book/data/BookDataJS.md)
> - [SectionDataJS](../../docs/book/data/SectionDataJS.md)