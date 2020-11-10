/* Scroll to top javascript
/* Written by Simon St-Andre
 */
/*-----------------------------------------------------------------------*/
//Get the button:
document.addEventListener("DOMContentLoaded", function(event) {
    var goToTopButton = document.getElementById("goToTopButton");

    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function() {appearFunction()};

    // Function to make the button appear
    function appearFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            // Shows the button
            goToTopButton.style.display = "block";
        } else {
            // Hides the button
            goToTopButton.style.display = "none";
        }
    }
});

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    // Both functions to have every web browser be all right
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

/*-----------------------------------------------------------------------*/
