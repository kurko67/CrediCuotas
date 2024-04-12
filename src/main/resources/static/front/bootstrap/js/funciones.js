function handleSubmit() {
    // Aquí puedes realizar el procesamiento del formulario, como enviar datos a un servidor.

    // Muestra el mensaje de envío correcto
    const mensajeEnvio = document.getElementById("mensaje-envio");
    mensajeEnvio.style.display = "block";
    mensajeEnvio.textContent = "Formulario enviado correctamente.";

    // Evita que el formulario se recargue la página
    return false;
}
