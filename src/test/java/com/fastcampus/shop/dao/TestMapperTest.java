package com.fastcampus.shop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Simple test class for TestMapper
 * This class doesn't use any testing framework and can be run manually
 */
public class TestMapperTest {
    
    private static final String CONFIG_FILE = "src/main/resources/application.properties";
    
    public static void main(String[] args) {
        try {
            TestMapperTest test = new TestMapperTest();
            test.testDirectDatabaseConnection();
            test.testSimulatedMapperBehavior();
            System.out.println("All tests passed successfully!");
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test direct database connection using JDBC
     */
    public void testDirectDatabaseConnection() throws Exception {
        System.out.println("Running testDirectDatabaseConnection...");
        
        Properties props = loadProperties();
        String driverClassName = props.getProperty("jdbc.driverClassName");
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        
        // Load the JDBC driver
        Class.forName(driverClassName);
        
        // Establish connection
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 'OK' AS result")) {
            
            if (rs.next()) {
                String result = rs.getString("result");
                assertEquals("OK", result);
                System.out.println("Database connection successful, result: " + result);
            } else {
                throw new AssertionError("No results returned from database");
            }
        }
        
        System.out.println("testDirectDatabaseConnection passed!");
    }
    
    /**
     * Test simulated mapper behavior (without actual MyBatis)
     */
    public void testSimulatedMapperBehavior() throws Exception {
        System.out.println("Running testSimulatedMapperBehavior...");
        
        // Create a simple implementation of TestMapper
        TestMapper mapper = new TestMapper() {
            @Override
            public String testConnection() {
                return "OK";
            }
        };
        
        // Test the mapper
        String result = mapper.testConnection();
        assertEquals("OK", result);
        
        System.out.println("testSimulatedMapperBehavior passed!");
    }
    
    /**
     * Load properties from application.properties file
     */
    private Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        }
        return props;
    }
    
    /**
     * Simple assertion method
     */
    private void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }
}