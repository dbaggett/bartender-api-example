package io.github.dbaggett.functional.bartender.api.jooby

import io.github.dbaggett.functional.bartender.api.common.domain.model.Beer
import io.github.dbaggett.functional.bartender.api.endpoint.AddBeerRequest
import io.github.dbaggett.functional.bartender.api.renderer.CollectionResponse
import io.github.dbaggett.functional.bartender.api.server.app
import io.restassured.RestAssured.*
import io.restassured.http.ContentType
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.amshove.kluent.shouldBeEmpty
import org.jooby.Status

object InventoryFeatureTest : Spek({
    jooby(app()) {
        describe("Getting beers") {
            given("that no beers exist") {
                it("should return empty list") {
                    val response = get("/inventory/beers").then().statusCode(Status.OK).As<CollectionResponse>()
                    val beers = response.results as List<Beer>
                    beers.shouldBeEmpty()
                }
            }
        }

        describe("Adding a beer") {
            given("a valid beer is added") {
                it("should return a 200") {
                    given()
                            .contentType(ContentType.JSON)
                            .body(AddBeerRequest("test", "lager", "pilsner", 4.5F))
                            .post("/inventory/beers")
                            .then()
                            .statusCode(Status.OK)
                }
            }
        }
    }
})