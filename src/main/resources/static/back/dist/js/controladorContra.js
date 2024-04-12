
    // Función para alternar la visibilidad de la contraseña
    document.getElementById("verContrasena").addEventListener("click", function() {
        var nuevaContrasenaInput = document.getElementById("nueva_contrasena");
        if (nuevaContrasenaInput.type === "password") {
            nuevaContrasenaInput.type = "text";
            this.textContent = "Ocultar";
        } else {
            nuevaContrasenaInput.type = "password";
            this.textContent = "Mostrar";
        }
    });

    // Función para validar si las contraseñas nuevas coinciden
    var nuevaContrasenaInput = document.getElementById("nueva_contrasena");
    var confirmarContrasenaInput = document.getElementById("confirmar_contrasena");
    var mensajeContrasena = document.getElementById("mensajeContrasena");

    nuevaContrasenaInput.addEventListener("keyup", function() {
        validarContrasenas();
    });

    confirmarContrasenaInput.addEventListener("keyup", function() {
        validarContrasenas();
    });

    function validarContrasenas() {
        var nuevaContrasena = nuevaContrasenaInput.value;
        var confirmarContrasena = confirmarContrasenaInput.value;

        if (nuevaContrasena !== confirmarContrasena) {
            mensajeContrasena.textContent = "Las contraseñas no coinciden.";
            confirmarContrasenaInput.setCustomValidity("Las contraseñas no coinciden.");
        } else {
            mensajeContrasena.textContent = "";
            confirmarContrasenaInput.setCustomValidity("");
        }
    }

