/**
 * Created by Randi on 28.10.2014.
 */
$(".show").click(function () {
    $('.hide').toggle();
    $(this).toggle();
    $('.storeComments:last').eq(0).toggle();
});

$('.hide').click(function () {
    $('.show').toggle();
    $(this).toggle();
    $('.storeComments:last').eq(0).toggle();
    $('.storeComments:last').empty();
});
function showBox() {
    $('.submitButton').toggle();
    $('.textForComment').toggle();
}
function getComments(input) {

    $.ajax ( {
        type : 'GET',
        url : "/getComments/"+ input,
        dataType : 'json',

        success : function ( data ) {
            console.log(data);


            for(i = 0; i<data.length; i++) {
                console.log();
                $('.storeComments:last').append((data[i].username + " said: " + data[i].comment) + "<br>");
            }

        }
    } );
}

function addComment(user, text) {
    $.ajax ({
        type: 'POST',
        url: "/addComment/" + user + "/" + text,
        success: success,
        dataType: 'json'
    });
}

function submitComment() {
    var text = "Could not be done";
    console.log(text);
}
