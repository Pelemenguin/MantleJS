# MantleJS

**The mod's source code is protected under MIT License.**

Anyone can use this in their modpack.

## What is this mod for?

MantleJS is a KubeJS addon that allows modpack authors to create Mantle things.

## How can I play this mod?

This mod is still developing at present.
It is not enough developed to have a release.
If you want to play this mod in advance, you can build from source.

## Tutorials

See `docs` branch.

<!--

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


#### Transformers

You can add **Book Transformers** to further customize your book.

##### Custom transformer

Book repositories may not always satisfy your requirements.
For example, Tinker's Construct's material list is not realizable using simply book repositories,
because they can not auto detect materials registered in Tinker's Construct.
So Tinker's Construct made this by creating a Book Transformer.

We will not use a complicated example here.
For the first example, we add 5 blank pages after each section.

###### Basic example

```javascript
// Script type: STARTUP
MantleJSEvents.transformerRegistry(event => {
    event.create('add_blank_pages')
        .transform(bookData => {
            bookData.sections.forEach(section => {
                for (let i = 0; i < 5; i++) {
                    section.addPage(page => {
                        page.setType("mantle:blank");
                        page.setContent(BookPage.ofType("mantle:blank"));
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

We use method `BookPage.ofType()` to create a page of which the type is `mantle:blank`.

###### Advanced example

Almost every Page Type except `mantle:blank` has its own data.
We need to set them before adding them into a book.

Use a consumer as the operation for the Page Content.

```javascript
// Script type: STARTUP
MantleJSEvents.transformerRegistry(event => {
    event.create('add_blank_pages')
        .transform(bookData => {
            bookData.sections.forEach(section => {
                section.addPage(page => {
                    page.setType("mantle:text");
                    page.setContent(BookPage.ofType("mantle:text"), content => {
                        content.text = [BookTextData.literal("Text test")];
                    });
                });
            });
        });
})
```

The example adds a page that writes `Text test` after each section.
The consumer `content => {}` is for Page Content's initializion.

> 💡 **Note**
>
> `TextData` is used to represent texts shown in Mantle's books.
> In KubeJS, use `BookTextData.literal()` to create it.
>
> Property `content.text` is an Array, you can not simply set it to a `TextData` object.
>
> For property lists of other Page Types, see [Mantle's Github Repository](https://github.com/SlimeKnights/Mantle/tree/1.20/src/main/java/slimeknights/mantle/client/book/data/content).

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

#### Page Types

Mantle books support custom page type.
A page type builds a page by the `build()` method.

This is an example:

```javascript
MantleJSEvents.pageTypeRegistry(event => {
    event.create("test_type")
        .buildPage((arguments, data, elements, rightSide) => {
            elements.add(BookElement.Text(0, 0, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT, BookTextData.literal(arguments.get("text"))));
        });
})
```

> 💡 **Note**
>
> The `BookElement` interface allows you to create Mantle's built-in book elements.
> Currently only `Text` is supported, others are on the to-do list.
>
> Custom book element will probably be in development.

The `create()` method accepts a `string` for id.
The `buildPage()` method specifies how the page should be built.

In this example, we added a text element onto the page.

`build()` function has 4 arguments:
 - `arguments`: Arguments declared in JSON files (mentioned [later](#json-format-for-custom-page-types)).
 - `data`: The `BookDataJS` object.
 - `elements`: A list of the page's elements. This list is empty at first, you should add your own elements here.
 - `rightSide`: this value is `true` if the page is on the right side, and `false` if the page is on the left side.

> 💡 **Note**
>
> The `arguments` argument is a Java HashMap object.
> You should use `arguments.key(string key)` to get data from it.

##### JSON Format for Custom Page Types

You should read [Mantle's official example](https://github.com/SlimeKnights/Mantle/tree/1.20/src/main/resources/assets/mantle/books/test) on how to create book repository first.

In `index.json`, you should declare the sections of the book.
For example: 

```json
[
    {
        "name": "test_section",
        "data": "sections/test.json",
        "icon":  {
            "item": "minecraft:book"
        }
    }
]
```

Then, under folder `sections`, place a `test.json`, write:

```json
[
    {
        "name": "test_page",
        "type": "kubejs:custom",
        "data": "test/test_type.json"
    },
]
```

> 💡 **Note**
>
> The `type` of the page should always be literally `kubejs:custom`.

Under folder `test`, place `test_type.json`, write:

```json
{
    "title": "Test Title",
    "type": "kubejs:test_type",
    "arguments": {
        "text": "Test Text",
        "prop": 111
    }
}
```

> 💡 **Note**
>
> The key `type` should be the id you registered in `MantleJSEvents.pageTypeRegistry`.
>
> The key `arguments` is just what we mentioned in `build()`.
> Anything you place inside can be accessed in `build()` function.

-->