let currentIndex = 1;
let isSliding = false;
const slideBox = document.querySelector(".slide-box");
const slides = document.querySelectorAll(".slide");
const lastIndex = slides.length - 1;


const getInterval = () => {
    moveSlide();
    return setInterval(moveSlide, 3000);
};
function moveSlide() {
    isSliding = true;
    setTimeout(() => { isSliding = false; }, 500);
    
    const bannerWidth = document.documentElement.clientWidth

    currentIndex++;
    slideBox.style.transform = `translate(-${bannerWidth * currentIndex}px, 0px`;
    
    if (currentIndex <= 0) {
        setTimeout(() => {
            slideBox.style.transitionDuration = "0ms";
            slideBox.style.transform = `translate(${bannerWidth * (lastIndex - 1)}px, 0px`;
            setTimeout(() => slideBox.style.transitionDuration = "400ms", 50);
            currentIndex = lastIndex - 1;
        }, 400);
    }
    else if (currentIndex >= lastIndex) {
        setTimeout(() => {
            slideBox.style.transitionDuration = "0ms";
            slideBox.style.transform = `translate(-${bannerWidth}px, 0px`;
            setTimeout(() => slideBox.style.transitionDuration = "400ms", 50);
            currentIndex = 1;
        }, 400);
    }
}
let slideInterval = getInterval(); // interval 등록
