package com.josue.kodeur.xtremanalyse.application.utils;

/**
 * @author JosueKodeur
 */

public class Constants {
    public static String FORBIDDEN_MESSAGE = "Votre session est terminée, veillez vous connecter";
    public static String ACCESS_DENIED_MESSAGE = "Vous n' êtes pas autorisé à effectuer cette action ";
    public static String APPLICATION_NAME = "xtrem_analyse";

    public static String IMAGE_EXTENSION = "jpg";
    public static String BASE_FOLDER = "/support/accidents/";
    public static String SERVER_FOLDER = "user.home";
    public static String ACCIDENT_IMAGE_FOLDER = "/accident/images/";

    public static String JWT_HEADER = "jwt-Header";
    public static String JWT_SECRET="IhGm9dEL@ohXn4GB";
    public static String TOKEN_PREFIX="Bearer ";

    public static String AUTHORITIES="authorities";
    public static int JWT_EXPIRATION= 5 * 60 * 60 * 1000;

    public static String[] PUBLIC_URL= {"/api/v1/user/login/**", "/api/v1/user/register/**", "/swagger-ui/**"};

}
