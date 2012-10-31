<!DOCTYPE html>
<html>

    <head>
    <#if book??>
        <title>ReaderFeeder - ${book.title}</title>
    <#else>
        <title>ReaderFeeder</title>
    </#if>
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/common.css"/>
    </head>


    <body>

        <div class="container">

            <#if book??>
                <div class="book-head">
                    <div class="book-cover">
                        <img class="book-img" src="${book.image}" alt="Picture not available"/>
                    </div>
                    <div class="book-info">
                        <h1 class="title">${book.title}</h1>
                        <h2 class="author">${book.author}</h2>
                        <div class="isbn">
                            <#if book.ISBN10??>
                                ISBN-10:${book.ISBN10}
                            </#if>
                            <br>
                            <#if book.ISBN13??>
                                ISBN-13:${book.ISBN13}
                            </#if>
                        </div>
                    </div>
                </div>

                <div class="description">
                    <hr>
                    ${book.description}
                </div>

                <#else>
                    <p><h1 class="title">${bookNotFound}</h1></p>
             </#if>
        </div>

    </body>
</html>