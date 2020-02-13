



import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ResponseTests {
    @Test
    public void shouldResponseOKwhenParticipantExist()
            throws ClientProtocolException, IOException {

        // Given
        String participant = "145";
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/servlet/A360/participants/" + participant );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }
    @Test
    public void shouldResponseBadRequestWhenParticipantNotExist()
            throws ClientProtocolException, IOException {

        // Given
        String participant = "145555";
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/servlet/A360/participants/" + participant );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    public void responseOKwhenFoundQuestionFromSessionId()
            throws ClientProtocolException, IOException {

        // Given
        long sessionId =1;
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/servlet/A360/questions/" + sessionId );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_OK));
    }
    @Test
    public void responseBadRequestWhenNotFoundQuestionFromSessionId()
            throws ClientProtocolException, IOException {

        // Given
        long sessionId =6;
        HttpUriRequest request = new HttpGet( "http://127.0.0.1:8080/servlet/A360/questions/" + sessionId );

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_BAD_REQUEST));
    }
}
