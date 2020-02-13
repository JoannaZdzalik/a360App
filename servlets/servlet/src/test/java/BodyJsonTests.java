import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.dto.QuestionDto;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class BodyJsonTests {
    @Test
    public void aCarObjectGoesIntoTheGarage() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("answerText", "odpowiedz");
        jsonAsMap.put("questionId", "1");
        jsonAsMap.put("participantUId", "12345");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                post("http://127.0.0.1:8080/servlet/A360/answers/create").
                then().
                statusCode(200);
    }
}