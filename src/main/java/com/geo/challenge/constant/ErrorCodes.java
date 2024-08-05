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
    PLAYER_NAME_DUPLICATED("GEO0012", "Ya existe un jugador con el nombre: %s y no debe repetirse"),
    TOURNAMENT_NAME_ERROR("GEO0013", "El nombre del torneo no puede ser nulo"),
    TOURNAMENT_TYPE_ERROR("GEO0014", "El tipo de torneo debe ser MALE (masculino) o FEMALE (femenino)"),
    TOURNAMENT_DATE_ERROR("GEO0015", "La fecha del torneo debe tener un formato DD/MM/YYYY válido"),
    TOURNAMENT_COMPETITORS_ERROR("GEO0016", "El número de competidores debe ser un número entero y potencia de 2"),
    INSUFFICIENT_PARAMETERS("GEO0017", "Para consultar un torneo debe indicar el Nombre o la Fecha minimamente"),
    TOURNAMENT_NAME_DUPLICATED("GEO0018", "Ya existe un torneo con el nombre: %s y no debe repetirse"),
    MULTIPLE_RESULT_ERROR("GEO0019", "Existe más de un torneo para esta Fecha, por favor ingrese el Nombre y/o el Tipo"),
    TOURNAMENT_NOT_FOUND("GEO0020", "No se encontró un torneo con los parámetros especificados"),
    TOURNAMENT_NOT_ENOUGH_DATA("GEO0021", "No hay suficientes jugadores en la base de datos"),
    EMPTY_PLAYER_LIST("GEO0022", "Debe indicar que jugadores participarán del torneo (la cantidad debe ser potencia de 2)"),
    PARTIAL_PLAYER_DATA("GEO0023", "Algunos de los ID de jugadores ingresados no se encontraron en la base de datos"),
    UNEXPECTED_ERROR("GEO0024", "Ocurrió un error inesperado procesando los datos del torneo, por favor intentelo nuevamente");

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
