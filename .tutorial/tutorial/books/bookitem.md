[↑ Go Back](./toc.md)

# Book Items

MantleJS added a new kind of item builder.
Use `'tconstruct:book'` to access this builder.

```javascript
// Script type: STARTUP
StartupEvents.registry('item', event => {
    event.create('test_book', 'tconstruct:book')
        .setBookData('example_book');
});
```

`setBookData` set the data of the book.
You should write exacly the same of the one you created when [creating books](./basics.md).

> **Doc Links**
>
> None.

<!-- WIP -->