$(document).ready(function () {
    $.getJSON("http://localhost:8080/books", function (data) {
        getBooksFromJson(data);
    })

    $.getJSON("http:///localhost:8080/books/categories", function (data) {
        $.each(data, function (index, value) {
            $('#categories').append(new Option(value, value))
        });
    });


    $('#categories').change(function () {
        $('#books').children().remove();
        $.getJSON("http://localhost:8080/books/categories/" + this.value, function (data) {
            getBooksFromJson(data);
        });
    });


    function convertUnixToDate(unix) {
        const dateObject = new Date(unix);
        return dateObject.toLocaleDateString();
    }

    function getBooksFromJson(data) {
        $.each(data, function (index, value) {

            $('#books').append($('<div id=' + value.isbn + '>'))

            if (value.thumbnailUrl) {
                $('#' + value.isbn).append($('<img src=' + value.thumbnailUrl + 'alt=' + value.isbn + ' width="120" height="180">'));
            }

            if (value.title) {
                $('#' + value.isbn)
                    .append($('<span class="title">' + value.title + '</span><br>'));
            }

            if (value.averageRating) {

                $('#' + value.isbn)
                    .append($('<p> Rating: </p>'))
                    .append($('<span class="averageRating">' + value.averageRating + '</span></br>'));
            }

            if (value.publishedDate) {
                $('#' + value.isbn)
                    .append($('<p> Publication date: </p>'))
                    .append($('<span class="publishedDate">' + convertUnixToDate(value.publishedDate) + '</span>'));
            }
        })
    }

    function getSpecificBookFromJson(data) {
        $('#books').append($('<div id=' + data.isbn + '>'))

        if (data.thumbnailUrl) {
            $('#' + data.isbn).append($('<img src=' + data.thumbnailUrl + 'alt=' + data.isbn + ' width="120" height="180">'));
        }

        if (data.title) {
            $('#' + data.isbn)
                .append($('<span class="title">' + data.title + '</span><br>'));
        }

        if (data.averageRating) {
            $('#' + data.isbn)
                .append($('<p> Rating: </p>'))
                .append($('<span class="averageRating">' + data.averageRating + '</span></br>'));
        }

        if (data.publisher) {
            $('#' + data.isbn)
                .append($('<p> Publisher: </p>'))
                .append($('<span class="publisher">' + data.publisher + '</span></br>'));
        }

        if (data.description) {
            $('#' + data.isbn)
                .append($('<p> Description: </p>'))
                .append($('<span class="description">' + data.description + '</span></br>'));
        }

        if (data.pageCount) {
            $('#' + data.isbn)
                .append($('<p> Page count: </p>'))
                .append($('<span class="pageCount">' + data.pageCount + '</span></br>'));
        }
    }

    $('#books').on('click', '.title, img', function () {
        $.getJSON("http://localhost:8080/books/" + this.parentNode.id, function (data) {
            $('#books').children().remove();
            getSpecificBookFromJson(data);
        });
    });
});

