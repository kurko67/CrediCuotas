document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll(".carousel img");
    let currentIndex = 0;

    function showImage(index) {
        images[currentIndex].style.display = "none";
        currentIndex = index;
        images[currentIndex].style.display = "block";
    }

    function nextImage() {
        let newIndex = currentIndex + 1;
        if (newIndex >= images.length) {
            newIndex = 0;
        }
        showImage(newIndex);
    }

    setInterval(nextImage, 3000); // Cambia la imagen cada 3 segundos
    showImage(currentIndex); // Mostrar la primera imagen al cargar la página
});
