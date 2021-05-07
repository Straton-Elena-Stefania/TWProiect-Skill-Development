let favoriteButton = document.getElementsByClassName('heartSkillButton');
let floatingTexts = document.getElementsByClassName('floatingFavoritedText');

for (let i = 0; i < favoriteButton.length; i++) {
    favoriteButton[i].addEventListener("click", function () {
        floatingTexts[i].style.display = "block";
        window.setTimeout(function () { stopFavoritedText(i); }, 2500);

        console.log(i);
        let name = '';
        if (favoriteButton[i].id === 'cookingFavoriteButton') {
            name = 'Cooking';
        } else if (favoriteButton[i].id === 'timeTravelFavoriteButton') {
            name = 'Time travelling';
        } else if (favoriteButton[i].id === 'firstAidFavoriteButton') {
            name = 'First aid';
        }
        const XHR = new XMLHttpRequest();
        XHR.open('POST', 'favourite');
        XHR.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        XHR.send('skill' + '=' + encodeURIComponent(name));
    }, false);
}


function stopFavoritedText(i) {
    floatingTexts[i].style.display = " none";
}