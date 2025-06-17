package com.fastcampus.shop.controller;

import com.fastcampus.shop.mapper.TestMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Simple test class for TestController
 * This class doesn't use any testing framework and can be run manually
 */
public class TestControllerTest {

    public static void main(String[] args) {
        try {
            TestControllerTest test = new TestControllerTest();
            test.testCheckDb();
            test.testCheckDbWithError();
            System.out.println("All tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void testCheckDb() throws Exception {
        System.out.println("Running testCheckDb...");

        // Create a mock TestMapper q
        TestMapper mockMapper = new TestMapper() {
            @Override
            public String testConnection() {
                return "OK";
            }
        };

        // Create the controller and set the mock mapper
        TestController controller = new TestController();
        // Use reflection to set the private field
        java.lang.reflect.Field field = TestController.class.getDeclaredField("testMapper");
        field.setAccessible(true);
        field.set(controller, mockMapper);

        // Create a mock response
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the method
        controller.checkDb(response);

        // Verify the response
        assertContentType("text/plain;charset=UTF-8", response.getContentType());
        assertContent("DB 연결 결과: OK", response.getContent());

        System.out.println("testCheckDb passed!");
    }

    public void testCheckDbWithError() throws Exception {
        System.out.println("Running testCheckDbWithError...");

        // Create a mock TestMapper that returns an error
        TestMapper mockMapper = new TestMapper() {
            @Override
            public String testConnection() {
                return "ERROR";
            }
        };

        // Create the controller and set the mock mapper
        TestController controller = new TestController();
        // Use reflection to set the private field
        java.lang.reflect.Field field = TestController.class.getDeclaredField("testMapper");
        field.setAccessible(true);
        field.set(controller, mockMapper);

        // Create a mock response
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Call the method
        controller.checkDb(response);

        // Verify the response
        assertContentType("text/plain;charset=UTF-8", response.getContentType());
        assertContent("DB 연결 결과: ERROR", response.getContent());

        System.out.println("testCheckDbWithError passed!");
    }

    // Simple assertion methods
    private void assertContentType(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected content type: " + expected + ", but was: " + actual);
        }
    }

    private void assertContent(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected content: " + expected + ", but was: " + actual);
        }
    }

    // Simple mock HttpServletResponse implementation
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
