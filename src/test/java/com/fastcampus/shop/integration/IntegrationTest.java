package com.fastcampus.shop.integration;

import com.fastcampus.shop.controller.TestController;
import com.fastcampus.shop.dao.TestMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Integration test for the entire application flow
 * This test verifies that the controller can interact with the mapper correctly
 */
public class IntegrationTest {

    public static void main(String[] args) {
        try {
            IntegrationTest test = new IntegrationTest();
            test.testManualIntegration();
            // Uncomment the following line if you have Spring context files properly set up
            // test.testWithSpringContext();
            System.out.println("All integration tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Integration test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test integration by manually wiring components
     */
    public void testManualIntegration() throws Exception {
        System.out.println("Running testManualIntegration...");

        // Create a simple implementation of TestMapper
        TestMapper mockMapper = new TestMapper() {
            @Override
            public String testConnection() {
                return "OK";
            }
        };

        // Create the controller
        TestController controller = new TestController();

        // Manually inject the mapper into the controller
        java.lang.reflect.Field field = TestController.class.getDeclaredField("testMapper");
        field.setAccessible(true);
        field.set(controller, mockMapper);

        // Create a mock response
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the controller method
        controller.checkDb(response);

        // Verify the response
        String expectedContentType = "text/plain;charset=UTF-8";
        String expectedContent = "DB 연결 결과: OK";

        if (!expectedContentType.equals(response.getContentType())) {
            throw new AssertionError("Expected content type: " + expectedContentType + 
                                    ", but was: " + response.getContentType());
        }

        if (!expectedContent.equals(response.getContent())) {
            throw new AssertionError("Expected content: " + expectedContent + 
                                    ", but was: " + response.getContent());
        }

        System.out.println("testManualIntegration passed!");
    }

    /**
     * Test integration using Spring context
     * Note: This test requires proper Spring context configuration
     */
    public void testWithSpringContext() throws Exception {
        System.out.println("Running testWithSpringContext...");

        // Load Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:WEB-INF/spring/applicationContext.xml",
                "classpath:WEB-INF/spring/servlet-context.xml");

        // Get controller bean
        TestController controller = context.getBean(TestController.class);

        // Create a mock response
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the controller method
        controller.checkDb(response);

        // Verify the response
        String expectedContentType = "text/plain;charset=UTF-8";
        String actualContentType = response.getContentType();
        
        if (actualContentType == null || !expectedContentType.equals(actualContentType)) {
            throw new AssertionError("Expected content type: " + expectedContentType + 
                                    ", but was: " + actualContentType);
        }

        String content = response.getContent();
        if (!content.contains("DB 연결 결과:")) {
            throw new AssertionError("Expected content to contain 'DB 연결 결과:', but was: " + content);
        }

        System.out.println("testWithSpringContext passed!");
    }

    /**
     * Simple mock HttpServletResponse implementation
     */
    private static class MockHttpServletResponse implements HttpServletResponse {
        private String contentType;
        private StringWriter writer = new StringWriter();
        private PrintWriter printWriter = new PrintWriter(writer);

        public String getContentType() {
            return contentType;
        }

        public String getContent() {
            return writer.toString();
        }

        @Override
        public void setContentType(String type) {
            this.contentType = type;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return printWriter;
        }

        // Other required methods with empty implementations
        @Override public void addCookie(javax.servlet.http.Cookie cookie) {}
        @Override public boolean containsHeader(String name) { return false; }
        @Override public String encodeURL(String url) { return url; }
        @Override public String encodeRedirectURL(String url) { return url; }
        @Override public String encodeUrl(String url) { return url; }
        @Override public String encodeRedirectUrl(String url) { return url; }
        @Override public void sendError(int sc, String msg) throws IOException {}
        @Override public void sendError(int sc) throws IOException {}
        @Override public void sendRedirect(String location) throws IOException {}
        @Override public void setDateHeader(String name, long date) {}
        @Override public void addDateHeader(String name, long date) {}
        @Override public void setHeader(String name, String value) {}
        @Override public void addHeader(String name, String value) {}
        @Override public void setIntHeader(String name, int value) {}
        @Override public void addIntHeader(String name, int value) {}
        @Override public void setStatus(int sc) {}
        @Override public void setStatus(int sc, String sm) {}
        @Override public int getStatus() { return 200; }
        @Override public String getHeader(String name) { return null; }
        @Override public java.util.Collection<String> getHeaders(String name) { return null; }
        @Override public java.util.Collection<String> getHeaderNames() { return null; }
        @Override public String getCharacterEncoding() { return "UTF-8"; }
        @Override public void setCharacterEncoding(String charset) {}
        @Override public java.util.Locale getLocale() { return null; }
        @Override public void setLocale(java.util.Locale loc) {}
        @Override public int getBufferSize() { return 0; }
        @Override public void setBufferSize(int size) {}
        @Override public void flushBuffer() throws IOException {}
        @Override public void resetBuffer() {}
        @Override public boolean isCommitted() { return false; }
        @Override public void reset() {}
        @Override public javax.servlet.ServletOutputStream getOutputStream() throws IOException { return null; }
        @Override public void setContentLength(int len) {}
        @Override public void setContentLengthLong(long len) {}
    }
}