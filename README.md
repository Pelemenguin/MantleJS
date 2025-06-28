# MantleJS

**The mod's source code is protected under MIT License.**

**When using the mod's source code, include a copy of [the license](./LICENSE).**

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
MantleJSEvents.bookRegistry(event => {
    event.create("example_book") // Specify the book ID
        .addBookRepository("kubejs:book/example_book") // Specify the book repository
        .build()
})
```

The script above creates a book of which the ID is `kubejs:example_book`.
The `addBookRepository` method sets the book repository of the book,
you can then put resources under the folder `kubejs/asets/kubejs/book/example`.
For resources formats, see [Tinker's Construct's GitHub repository](https://github.com/SlimeKnights/TinkersConstruct) or their mod file.

#### Transformers

You can add Book Transformers to further customize your book.


For example, use the script below to enable Tinker's Construct's `TierRangeMaterialSectionTransformer`,
which is used in Tinker's Construct to display the material list.

```javascript
const TierRangeMaterialSectionTransformer = Java.loadClass("slimeknights.tconstruct.library.client.book.sectiontransformer.materials.TierRangeMaterialSectionTransformer")
MantleJSEvents.bookRegistry(event => {
    event.create("example_book")
        .addBookRepository("kubejs:book/example_book")
        .addTransformer(TierRangeMaterialSectionTransformer.INSTANCE)
        .build()
})
```

Further customization of `BookTransformer` is still developing.