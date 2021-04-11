function showSettingsEmailError() {
    if (email.validity.valueMissing) {
        emailError.textContent = 'You need to enter an e-mail address.';
    } else if (email.validity.typeMismatch) {
        emailError.textContent = 'Entered value needs to be an e-mail address.';
    }

    emailError.className = 'error active';
    emailError.style.backgroundColor = "rgb(114, 173, 149)";
    emailError.style.right = "10em";
    emailError.style.top = "-1em";
    emailError.style.borderRadius = "1em 1em 0em 1em";
}

function showSettingsUsernameError() {
    if (!usernameRegex.test(username)) {
        //console.log(":):)");
        usernameError.textContent = 'Username contains invalid characters.';
    }

    usernameError.className = 'error active';
}

function showSettingsPasswordError() {
    /**
     * Daca nu avem nimic in casuta avem eroare
     */
    if (password.validity.valueMissing) {
        passwordError.textContent = 'You need to enter your password.';
    } else if (!passwordRegex.test(password.value)) {
        passwordError = "Invalid character detected in password.";
    } else if (password.validity.tooShort) {
        passwordError.textContent = `Password should be at least ${password.minLength} characters; you entered ${password.value.length}.`;
    }

    /**
     * Modificam clasa pentru mesajul de eroare pentru a-l afisa
     */
    passwordError.className = 'error active';
    
    passwordError.style.backgroundColor = "rgb(220, 127, 200)";
    passwordError.style.borderRadius = "0em 1em 1em 1em";
    passwordError.style.top = "4em";
}