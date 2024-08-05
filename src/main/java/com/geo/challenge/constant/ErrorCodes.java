package com.geo.challenge.constant;

public enum ErrorCodes {
    INVALID_PLAYER_ID("GEO0001", "El Player-Id ingresado es inválido"),
    ERROR_SAVING_PLAYER("GEO0002", "Error persistiendo datos del jugador"),
    ANY_PLAYER_FOUND("GEO0003", "No se encontró ningún jugador registrado"),
    PLAYER_NOT_FOUND("GEO0004", "No existe el jugador con ID: %s"),
    PLAYER_NAME_ERROR("GEO0005", "El nombre del jugador no puede ser nulo"),
    PLAYER_SKILL_ERROR("GEO0006", "La habilidad del jugador debe ser un número entero entre 0 y 100"),
    PLAYER_GENDER_ERROR("GEO0007", "El genero del jugador debe ser MALE (masculino) o FEMALE (femenino)"),
    PLAYER_REACTION_TIME_ERROR("GEO0008", "El tiempo de reacción de la jugadora debe ser un número decimal entre 0 y 1 segundo"),
    PLAYER_STRENGTH_ERROR("GEO0009", "La fuerza del jugador debe ser un número entero entre 0 y 100"),
    PLAYER_SPEED_ERROR("GEO0010", "La velocidad del jugador debe ser un número decimal entre 0 y 50 km/h"),
    ERROR_DELETING_PLAYER("GEO0011", "Error inesperado al eliminar el Jugador con ID: %s"),
    PLAYER_NAME_DUPLICATED("GEO0012", "Ya existe un jugador con el nombre: %s y no debe repetirse");

    private final String code;
    private final String description;

    private ErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
