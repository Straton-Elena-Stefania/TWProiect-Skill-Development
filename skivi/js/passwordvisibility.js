document.getElementById("eye").addEventListener("click", function () {
    let passwordField = document.getElementById("password")
    if (passwordField.type == 'text') {
        passwordField.type = 'password';
    }
    else {
        passwordField.type = 'text';
    }
}, false);