# BookBuilder

> **`addRepository(ResourceLocation repo)`**
>
> Add a specified path as the books repository.
>
> **Parameters**:
> - `repo` - The resource path of the repository.
>   The namespace `kubejs` will **not** be automatically added.
>
> **Returns**:
> - The `BookBuilder` itself.
>
> Related tutorials:
> - [Mantle Books/Basics](./../../tutorial/books/basics.md#basics)

> **`addTransformer(string transformerId)`**
>
> Add a transformer to the book.
> Not to be confused with `addJavaTransformer`.
>
> **Parameters**:
> - `transformerId` - The id you've used in `MantleJSEvents.transformerRegister`.
>
> **Returns**:
> - The `BookBuilder` itself.

<!-- WIP -->