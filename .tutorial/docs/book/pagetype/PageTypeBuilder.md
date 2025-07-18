# PageTypeBuilder

> **`buildPage(BuildFunction function)`**
>
> Set the function of the page type.
>
> **Parameters**:
> - `function` - A `BuildFunction` for page building on Java side.
> For Java Script side users, use a Consumer. For example:
> `(args, data, elements, rightSide) => { /* Do something */ };`
>
> **Returns**:
> - The `PageTypeBuilder` itself.

## BuildFunction

> **`build(Object arguments, BookDataJS data, ArrayList<BookElement> elements, boolean isOnRightSide)`**
>
> A function for page building.
>
> **Abstract**
>
> **Parameters**:
> - `arguments` - The extra arguments read from JSON files of the page data.
> - `data` - A `BookDataJS` object that represents the book's data.
> - `elements` - A `ArrayList` containing all of the book elements on the page.
>   The list is empty at first, add elements into the list to draw them on the page.