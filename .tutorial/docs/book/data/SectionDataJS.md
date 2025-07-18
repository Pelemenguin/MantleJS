# SectionDataJS

> **(property) `origin`**
>
> Original `SectionData` object.
>
> **Type**: `slimeknights.mantle.client.book.data.SectionData`

> **addPage(Consumer<PageDataJS> builder)**
>
> Add a page to the section.
>
> **Parameters**:
> - `builder` - A Consumer that accepts a [`PageDataJS`](./PageDataJS.md).
>   This `PageDataJS` object is initially a blank page.

> **getParent()**
>
> Get the parent `BookDataJS` of the section.
>
> **Returns**:
> - A [`BookDataJS`](./BookDataJS.md) representing the parent of the section.

> **getSource()**
>
> Get the source repository of the book.
>
> **Returns**:
> - A `slimeknights.mantle.client.book.repository.BookRepository` object.

> **getPages()**
>
> Get a list of pages of the section.
>
> **Returns**:
> - An `ArrayList<PageDataJS>` object representing the list of the pages.

Other methods see Mantle's [source code](https://github.com/SlimeKnights/Mantle/blob/1.20/src/main/java/slimeknights/mantle/client/book/data/SectionData.java).