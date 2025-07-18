[↑ Go Back](./toc.md)

# Basics

You can use `BookRegistryEventJS` to create your own book.

```javascript
// Script type: STARTUP
MantleJSEvents.bookRegistry(event => {
    event.create("example_book");
});
```

The `create` method accepts an id for the book.

In order to add contents to the book, you should specify a book repository.

```javascript
// Script type: STARTUP
MantleJSEvents.bookRegistry(event => {
    event.create("example_book")
        .addRepository("kubejs:book/example_book");
});
```

The `addRepository` method accepts a Resource Location for the resources.
In this example, resources of the book should be place under `assets/kubejs/book/example_book/`.

For format of the resources for Mantle books, see [Mantle's Github Repository](https://github.com/SlimeKnights/Mantle/tree/1.20/src/main/resources/assets/mantle/books/test).

> **Doc Links**
>
> - [`BookRegistryEventJS`](../../docs/event/BookRegistryEventJS.md) - The event mentioned for book creation.