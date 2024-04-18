// Get the modal
var modal = document.getElementsByClassName("myModal")[0];
var modal1 = document.getElementsByClassName("myModal")[1];

// Get the button that opens the modal
var btn = document.getElementsByClassName("myBtn")[0];
var btn1 = document.getElementsByClassName("myBtn")[1];

// Get the <span> element that closes the modal

// When the user clicks on the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}
btn1.onclick = function (){
    modal1.style.display = "block";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target === modal || event.target === modal1 ) {
        modal.style.display = "none";
        modal1.style.display ="none";
    }
}

window.onload = function () {
    setTimeout(function () {
        $('.table-data').fadeOut('fast');
        $('.message').fadeOut('fast')
    }, 25000);

}

