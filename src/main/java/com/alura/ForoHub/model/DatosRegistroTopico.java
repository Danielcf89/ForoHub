


//package com.alura.ForoHub.model;
//
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class DatosRegistroTopico {
//    @NotNull
//    private String titulo;
//    @NotNull
//    private String mensaje;
//    @NotNull
//    private String autor;
//    @NotNull
//    private String curso;
//}

package com.alura.ForoHub.model;

public class DatosRegistroTopico {
    private String titulo;
    private String mensaje;
    private String status;
    private String curso;

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}


