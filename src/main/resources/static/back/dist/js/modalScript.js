// Función para abrir el modal
function abrirModal() {
    var modal = document.getElementById("miModal");
    modal.style.display = "block";
}

// Función para cerrar el modal
function cerrarModal() {
    var modal = document.getElementById("miModal");
    modal.style.display = "none";
}

// Event listeners para abrir y cerrar el modal
document.getElementById("abrirModal").addEventListener("click", abrirModal);
document.getElementById("cerrarModal").addEventListener("click", cerrarModal);

// Cerrar el modal si se hace clic fuera de él
window.addEventListener("click", function(event) {
    var modal = document.getElementById("miModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
});