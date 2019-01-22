/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.disney.booking.BookingApplication;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void testGetFoods() {
        Response response = RestAssured.when().get("/calTracking/public/getFoods");

        assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
    }

    @Test
    public void testGetFoodsWithRange() {
        Response response = RestAssured.when().get("/calTracking/public/getFoods/50/100");

        assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
    }

    @Test
    public void testGetFoodsWithRangeNotFound() {
        Response response = RestAssured.when().get("/calTracking/public/getfoods/1000/2000");

        assertEquals("404 must be returned", HttpStatus.NOT_FOUND.value(), response.statusCode());
    }

    @Test
    public void testAddFood() {
        Headers headers = populateAdminHeaders();
        Response response = RestAssured.given()
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(this.createMockFood())
                .when().post("/calTracking/admin/addFood");

        assertEquals("200 must be returned", HttpStatus.CREATED.value(), response.statusCode());
    }

    @Test
    public void testAddFoodWithoutConsumerKey() {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(this.createMockFood())
                .when().post("/calTracking/admin/addFood");

        assertEquals("200 must be returned", HttpStatus.UNAUTHORIZED.value(), response.statusCode());
    }

    /**
     *
     * @return
     */
    private Headers populateAdminHeaders() {
        return new Headers(new Header("consumer-key", "admin"));
    }

    private Object createMockFood() {
        //return new Food(Long.valueOf(1000), "Banana", Long.valueOf(2000));
        return null;
    }
}