let currentCardIndex = 0;
let leftBtn = document.getElementById("leftBtn");
let rightBtn = document.getElementById("rightBtn");


leftBtn.addEventListener('click', () => goToCard(-1));
rightBtn.addEventListener("click", () => goToCard(1));

handleActiveButtons();

function goToCard(direction /* 1 | -1 */) {
  let cards = document.getElementsByClassName("carousel__card");

  currentCardIndex += direction;

  for (let i = 0; i < cards.length; i++) {
    cards[i].style.transform = `translateX(-${currentCardIndex * 100}%)`;
  }

  handleActiveButtons();

  return undefined;
}

// function goToNextCard() {
//   let cards = document.getElementsByClassName("carousel__card");

//   currentCardIndex++;

//   for (let i = 0; i < cards.length; i++) {
//     cards[i].style.transform = `translateX(-${currentCardIndex * 100}%)`;
//   }

//   handleActiveButtons();
// }

// function goToPrevCard() {
//   let cards = document.getElementsByClassName("carousel__card");

//   currentCardIndex--;

//   for (let i = 0; i < cards.length; i++) {
//     cards[i].style.transform = `translateX(-${currentCardIndex * 100}%)`;
//   }

//   handleActiveButtons();
// }

function handleActiveButtons() {
    let cards = document.getElementsByClassName("carousel__card");

    leftBtn.disabled = currentCardIndex === 0;
    rightBtn.disabled = currentCardIndex === (cards.length - 1);
}
