# Test Documentation

This document provides information about the test classes created for the application.

## Test Classes

### 1. TestControllerTest

Located at: `src/test/java/com/fastcampus/shop/controller/TestControllerTest.java`

This class tests the `TestController` functionality. It includes:

- `testCheckDb`: Tests the controller's behavior when the database connection is successful.
- `testCheckDbWithError`: Tests the controller's behavior when the database connection returns an error.

### 2. TestMapperTest

Located at: `src/test/java/com/fastcampus/shop/mapper/TestMapperTest.java`

This class tests the `TestMapper` functionality. It includes:

- `testDirectDatabaseConnection`: Tests direct database connectivity using JDBC.
- `testSimulatedMapperBehavior`: Tests a simulated implementation of the TestMapper interface.

### 3. IntegrationTest

Located at: `src/test/java/com/fastcampus/shop/integration/IntegrationTest.java`

This class tests the integration between components. It includes:

- `testManualIntegration`: Tests the integration by manually wiring components.
- `testWithSpringContext`: Tests the integration using Spring context (commented out by default).

## How to Run the Tests

### Running TestControllerTest

1. Open a command prompt or terminal.
2. Navigate to the project root directory.
3. Run the following command:

```
java -cp target/classes;target/test-classes com.fastcampus.shop.controller.TestControllerTest
```

### Running TestMapperTest

1. Open a command prompt or terminal.
2. Navigate to the project root directory.
3. Run the following command:

```
java -cp target/classes;target/test-classes com.fastcampus.shop.dao.TestMapperTest
```

### Running IntegrationTest

1. Open a command prompt or terminal.
2. Navigate to the project root directory.
3. Run the following command:

```
java -cp target/classes;target/test-classes com.fastcampus.shop.integration.IntegrationTest
```

## Notes

- The tests are designed to be run manually without relying on a testing framework.
- The `testWithSpringContext` method in `IntegrationTest` is commented out by default. Uncomment it if you have Spring context files properly set up.
- The `testDirectDatabaseConnection` method in `TestMapperTest` requires a valid database connection. Make sure the database is running and accessible before running this test.