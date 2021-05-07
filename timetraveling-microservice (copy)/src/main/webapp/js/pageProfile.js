const skillAddButton = document.getElementById('addSkill');
const achievementReference = document.getElementById('achievementButton')
skillAddButton.addEventListener('click', (event) => {
    event.preventDefault();
    event.stopPropagation();
    const list = document.getElementById('skillList');
    const skillInput = document.getElementById('skillInput');
    const skill = skillInput.value;
    const XHR = new XMLHttpRequest();
    const data = new FormData(document.getElementById('skillForm'));
    XHR.addEventListener('load', () => {
        if (XHR.status === 200) {
            const newSKill = document.createElement('li');
            newSKill.appendChild(document.createTextNode(skill));
            list.appendChild(newSKill);
            skillInput.value = '';
        } else {
            alert('aaaa');
        }
    });
    XHR.open('POST', 'add-skill');
    XHR.send(data);
})
const settingsBar = document.getElementById('settingsBar');
settingsBar.addEventListener('click', (event) => {
    event.preventDefault();
    event.stopPropagation();
    if (event.target.tagName === 'A') {
        const lastActiveButton = document.getElementsByClassName('active')[0];
        const currentActiveButton = event.target.parentNode;

        lastActiveButton.classList.remove('active');
        currentActiveButton.classList.add('active');

        const settingsContainerElements = Array.from(document.getElementsByClassName('tab'));
        if (event.target.innerText === "Info") {

            settingsContainerElements.map(tab => {
                if (tab.id === "infoTab") {
                    tab.classList.remove('hideTab');
                } else {
                    tab.classList.add('hideTab')
                }
            })
        } else if (event.target.innerText === "🏆 Achievements") {
            settingsContainerElements.map(tab => {
                if (tab.id === "scoresTab") {
                    tab.classList.remove('hideTab');
                } else {
                    tab.classList.add('hideTab')
                }
            })
        }
    }
});
const profilePictureInput = document.getElementById('profilePictureInput');
profilePictureInput.addEventListener('change', () => {
   if (profilePictureInput.files.length > 0) {
       const XHR = new XMLHttpRequest();
       const data = new FormData(document.getElementById('profilePictureForm'));
       XHR.addEventListener('load', () => {
           if (XHR.status === 200) {
               const newSKill = document.createElement('li');
               newSKill.appendChild(document.createTextNode(skill));
               list.appendChild(newSKill);
               skillInput.value = '';
           } else {
               alert('aaaa');
           }
       });
       XHR.open('POST', 'update-profile-picture');
       XHR.send(data);
   }
});