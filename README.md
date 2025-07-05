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
        .addBookRepository("kubejs:book/example_book") // Specify the book repository
})
```

The script above creates a book of which the ID is `kubejs:example_book`.
The `addBookRepository()` method sets the book repository of the book,
you can then put resources under the folder `kubejs/asets/kubejs/book/example_book/`.
For resources formats, see [Tinker's Construct's GitHub repository](https://github.com/SlimeKnights/TinkersConstruct) or their mod file.

#### Transformers

You can add Book Transformers to further customize your book.

For example, use the script below to enable Tinker's Construct's `TierRangeMaterialSectionTransformer` (Tinker's Construct is required if you want to use this),
which is used in Tinker's Construct to display the material list.

```javascript
// Script type: STARTUP
const TierRangeMaterialSectionTransformer = Java.loadClass("slimeknights.tconstruct.library.client.book.sectiontransformer.materials.TierRangeMaterialSectionTransformer")
MantleJSEvents.bookRegistry(event => {
    event.create("example_book")
        .addBookRepository("kubejs:book/example_book")
        .addTransformer(TierRangeMaterialSectionTransformer.INSTANCE)
})
```

Further customization of `BookTransformer` is still developing.

#### Book Items

MantleJS added a new item type `tconstruct:book` to create.

```javascript
StartupEvents.registry('item', event => {
    event.create('test_book', 'tconstruct:book')
        .setBookData('example_book')
})
```

The method `setBookData(id)` is used to set the book's data.
The ID must be the same as the one you have registered in the `MantleJSEvents.bookRegistry` event.

Thus, you can open the book via a specified item in the game.
