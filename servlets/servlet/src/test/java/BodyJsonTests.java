import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.SessionService;
import org.apache.http.HttpStatus;

import org.junit.Test;
import org.mockito.InjectMocks;


import javax.inject.Inject;

import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

//public class BodyJsonTests {



/*    @Test
    public void givenMovieId_whenMakingGetRequestToMovieEndpoint_thenReturnMovie() {

Session session = new Session();



        when(sessionService.createSession(any(SessionDto.class),any())).thenReturn(any(Status.class));

        get(uri + "/movie/" + testMovie.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(testMovie.getId()))
                .body("name", equalTo(testMovie.getName()))
                .body("synopsis", notNullValue());
    }*/
/*@Test
public void givenMovie_whenMakingPostRequestToMovieEndpoint_thenCorrect() {
    Map<String, String> request = new HashMap<>();
    request.put("id", "11");
    request.put("name", "movie1");
    request.put("synopsis", "summary1");

    int movieId = given().contentType("application/json")
            .body(request)
            .when()
            .post(uri + "/movie")
            .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .path("id");
    assertThat(movieId).isEqualTo(11);

}*/
