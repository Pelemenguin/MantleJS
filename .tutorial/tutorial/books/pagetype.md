[↑ Go Back](./toc.md)

# Page Types

Page types defines what format should a page be built in.

## Custom page types

A page type builds a page by the `build()` method.

This is an example:

```javascript
MantleJSEvents.pageTypeRegistry(event => {
    event.create("test_type")
        .buildPage((args, data, elements, rightSide) => {
            elements.add(BookElement.Text(0, 0, BookScreen.PAGE_WIDTH, BookScreen.PAGE_HEIGHT, BookTextData.literal(args.get("text"))));
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

## JSON Format for Custom Page Types

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
> You can access anything under the key `arguments` in `build()` function via the first parameter.