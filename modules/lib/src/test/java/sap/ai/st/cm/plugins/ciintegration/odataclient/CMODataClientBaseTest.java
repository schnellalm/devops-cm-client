package sap.ai.st.cm.plugins.ciintegration.odataclient;

import java.lang.reflect.Field;
import java.net.URI;

import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicStatusLine;
import org.apache.olingo.client.api.ODataClient;
import org.easymock.Capture;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class CMODataClientBaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected static class StatusLines {
        private final static ProtocolVersion HTTP_1_1 = new ProtocolVersion("HTTP", 1, 1);
        protected final static BasicStatusLine BAD_REQUEST = new BasicStatusLine(HTTP_1_1, 400, "Bad Request");
        protected final static BasicStatusLine UNAUTHORIZED = new BasicStatusLine(HTTP_1_1, 401, "Unauthorized");
        protected final static BasicStatusLine NOT_FOUND = new BasicStatusLine(HTTP_1_1, 404, "Not Found.");
    }
    protected Capture<URI> address;

    protected CMODataClient examinee;

    protected CMODataClientBaseTest() {
    }

    protected void setup() throws Exception {

        address = Capture.newInstance();

        examinee = new CMODataClient(
                "https://example.org/endpoint",
                "john.doe",
                "openSesame");

    }

    protected void tearDown() throws Exception {
        examinee = null;
        address = null;
    }

    protected static void setMock(CMODataClient examinee, ODataClient mock) throws Exception {
        Field client = CMODataClient.class.getDeclaredField("client");
        client.setAccessible(true);
        client.set(examinee, mock);
        client.setAccessible(false);
    }


}