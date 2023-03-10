package com.restAssured.testes;

import com.restAssured.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UsuarioTeste {

    @BeforeClass
    public static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testeListaDadosUsuarios() {
        given().
             params("page", "2").
        when().
             get("/users").
        then().
             statusCode(HttpStatus.SC_OK).
             body("page",is(2)).
             body("data",is(notNullValue()));
    }

    @Test
    public void testeCriarUsuarioComSucesso(){
        Usuario usuario = new Usuario("ana", "eng");
        given().
              contentType(ContentType.JSON).
              body(usuario).
        when().
              post("/users").
        then().
              statusCode(HttpStatus.SC_CREATED).
              body("name", is("ana"));
    }
}
