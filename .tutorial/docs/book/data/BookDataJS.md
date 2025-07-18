# BookDataJS

A helper class to access `BookData`

> **(property) `origin`**
>
> Original `BookData` object.
>
> **Type**: `slimeknights.mantle.client.book.data.BookData`

> **`ArrayList<SectionDataJS> getSections()`**
>
> Get all the sections from the book.
>
> **Returns**:
> - The list of `SectionDataJS` representing the sections of the book.

> **`getAppearance()`**
>
> Get the appearance data of the book.
>
> **Returns**:
> - A `slimeknights.mantle.client.book.data.AppearanceData` object representing the appearance of the book.

> **`getFontRenderer()`**
>
> Get the font renderer of the book.
>
> **Returns**:
> - A `Font` object used in the book.

> **`getStrings()`**
>
> Get the translate string of the book.
>
> **Returns**:
> - A `HashMap<String, String>` object.

> **`addRepositoy(BookRepository repository)`**
>
> Adds a new repository to the book.
> 
> **Parameters**:
> - `repository` - **Nullable** - The repository to add.
>

> **`addTransformer(BookTransformer transformer)`**
>
> Adds a transformer to the book.
>
> **Parameters**:
> - `transformer` - **Nullable** - The transformer to add.

Other methods see Mantle's [source code](https://github.com/SlimeKnights/Mantle/blob/1.20/src/main/java/slimeknights/mantle/client/book/data/BookData.java).