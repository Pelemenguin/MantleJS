# MantleJS

**The mod's source code is protected under MIT License.**

Adding the mod to your modpack is currently **not** allowed,
for this mod is still working in progress.
As soon as the first release is published, whoever can use it in their modpack.

## What is this mod for?

MantleJS is a KubeJS addon that allows modpack authors to create Mantle things.

## How can I play this mod?

This mod is still developing at present.
It is not enough developed to have a release.
If you want to play this mod in advance, you can build from source.

## Tutorials

### Mantle book

#### Basics

You can use script below to register your own book.

```javascript
// Script type: STARTUP
MantleJSEvents.bookRegistry(event => {
    event.create("example_book") // Specify the book ID
        .addBookRepository("kubejs:book/example_book"); // Specify the book repository
})
```

The script above creates a book of which the ID is `kubejs:example_book`.
The `addBookRepository()` method sets the book repository of the book,
you can then put resources under the folder `kubejs/asets/kubejs/book/example_book/`.
For resources formats, see [Tinker's Construct's GitHub repository](https://github.com/SlimeKnights/TinkersConstruct) or their mod file.

> 👉 **Link**
>
> For book item creation, see section [Book Items](#book-items).

#### Transformers

You can add **Book Transformers** to further customize your book.

##### Custom transformer

Book repositories may not always satisfy your requirements.
For example, Tinker's Construct's material list is not realizable using simply book repositories,
because they can not auto detect materials registered in Tinker's Construct.
So Tinker's Construct made this by creating a Book Transformer.

We will not use a complicated example here.
For the first example, we add 5 blank pages after each section.

```javascript
// Script type: STARTUP
MantleJSEvents.transformerRegistry(event => {
    event.create('add_blank_pages')
        .transform(bookData => {
            bookData.sections.forEach(section => {
                for (let i = 0; i < 5; i++) {
                    let ContentBlank = BookPage.getClass("mantle:blank");
                    section.addPage(page => {
                        page.setType("mantle:blank");
                        page.setContent(new ContentBlank());
                    });
                }
            })
        });
})
```

Let me explain this.

`event.create()` creates a new transformer.
`add_blank_pages` is the ID for the transformer which is used when creating books.

Then we call `transform()` method.
This method is used to set the transform function.
The function accepts a `BookData` as argument.

> 💡 **Note**
> 
> `BookData` mentioned here is actually `BookDataJS` in the source code.
> `BookDataJS` is for the access of properties of Mantle `BookData` objects that is marked with `transient`.
> You can access the property `origin` of a `BookDataJS` to access Mantle `BookData` object directly if you want.

Then we iterate through all the sections of the book.
For each section, we added 5 blank pages.

We use method `BookPage.getClass()` to load the class of the Page Type `mantle:blank`.
This Page Type represents a blank page.

Then we use `new ContentBlank()` to create a new instance.

##### Add transformers via Java classes

Sometimes, other mod using Mantle will create their own transformers.
If you want to these transformers, you can manually load Java classes and add their instances into the book.

For example, use the script below to enable Tinker's Construct's `TierRangeMaterialSectionTransformer` (Tinker's Construct is required if you want to use this),
which is used in Tinker's Construct to display the material list.

```javascript
// Script type: STARTUP
const TierRangeMaterialSectionTransformer = Java.loadClass("slimeknights.tconstruct.library.client.book.sectiontransformer.materials.TierRangeMaterialSectionTransformer")
MantleJSEvents.bookRegistry(event => {
    event.create("example_book")
        .addBookRepository("kubejs:book/example_book")
        .addJavaTransformer(TierRangeMaterialSectionTransformer.INSTANCE);
})
```

You can also use `BuiltinTransformer` interface to load Mantle's built-in transformers.
For example, `.addJavaTransformer(BuiltinTransformer.contentTableTransformer())`.

#### Book Items

MantleJS added a new item type `tconstruct:book` to create.

```javascript
// Script type: STARTUP
StartupEvents.registry('item', event => {
    event.create('test_book', 'tconstruct:book')
        .setBookData('example_book');
})
```

The method `setBookData(id)` is used to set the book's data.
The ID must be the same as the one you have registered in the `MantleJSEvents.bookRegistry` event.

Thus, you can open the book via a specified item in the game.
