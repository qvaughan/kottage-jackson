package kottage.jackson

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import kottage.core.Body
import kottage.core.ResponseBuilder

/**
 * @author Michael Vaughan
 */

object Json : ObjectMapper()

fun <T> Body.fromJson(clazz: Class<T>) : T {
    return Json.readValue(this.asString(), clazz)
}

fun Body.asJson() : JsonNode {
    return Json.readTree(this.asString())
}

/**
 * Sets the body to the @body and adds a Content-Type header for application/json.
 * @return This ResponseBuilder
 */
fun ResponseBuilder.body(body: JsonNode) : ResponseBuilder {
    this.header("Content-Type" to "application/json")
    this.body(Json.writeValueAsBytes(body))
    return this;
}