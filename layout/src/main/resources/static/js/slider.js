$(document).ready(function() {
    const $slider = $('.slider');
    const $cards = $('.card');
    let index = 0;

    // 카드가 겹치도록 설정
    function updateSlider() {
        const cardWidth = $cards.outerWidth(true); // 카드의 전체 너비
        $slider.css('transform', `translateX(-${index * cardWidth}px)`); // 슬라이더 이동
    }

    // 슬라이더 자동 업데이트 (optional)
    setInterval(function() {
        index = (index + 1) % $cards.length; // 인덱스 증가
        updateSlider();
    }, 3000); // 3초마다 이동
});
