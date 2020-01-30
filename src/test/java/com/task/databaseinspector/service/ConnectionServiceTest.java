package com.task.databaseinspector.service;

import com.task.databaseinspector.busobj.dto.ConnectionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ConnectionServiceTest {

    private static final String TEST_HOST = "testHost";
    private static final String TEST_PORT = "5543";
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String TEST_DATABASE_NAME = "testDatabaseName";
    @Autowired
    private ConnectionService service;

    @Test
    public void testCreate() {
        ConnectionDto connectionDto = service.create(buildTestConnectionInstance());
        validate(connectionDto);

    }

    @Test
    public void testGet() {
        Long id = service.create(buildTestConnectionInstance()).getId();
        ConnectionDto connectionDto = service.get(id);
        validate(connectionDto);
        assertThat(connectionDto.getId()).describedAs("Connection id").isEqualTo(id);
    }

    @Test
    public void testDelete() {
        Long id = service.create(buildTestConnectionInstance()).getId();
        service.delete(id);
        assertThat(service.get(id)).isNull();
    }

    @Test
    public void testUpdate() {
        ConnectionDto connectionDto = service.create(buildTestConnectionInstance());
        connectionDto.setDatabaseName(null);
        connectionDto.setPassword(null);
        connectionDto.setHost(TEST_HOST+"1");
        connectionDto.setPort(null);
        connectionDto.setUsername(TEST_USERNAME+"1");
        service.update(connectionDto);
        connectionDto = service.get(connectionDto.getId());
        assertThat(connectionDto.getDatabaseName())
                .isEqualTo(TEST_DATABASE_NAME);
        assertThat(connectionDto.getHost())
                .isEqualTo(TEST_HOST+"1");
        assertThat(connectionDto.getPort())
                .isEqualTo(TEST_PORT);
        assertThat(connectionDto.getUsername())
                .isEqualTo(TEST_USERNAME+"1");
        assertThat(connectionDto.getPassword())
                .isEqualTo(TEST_PASSWORD);

    }

    private ConnectionDto buildTestConnectionInstance() {
        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setHost(TEST_HOST);
        connectionDto.setPort(TEST_PORT);
        connectionDto.setUsername(TEST_USERNAME);
        connectionDto.setPassword(TEST_PASSWORD);
        connectionDto.setDatabaseName(TEST_DATABASE_NAME);
        return connectionDto;
    }

    private void validate(ConnectionDto connectionDto) {
        assertThat(connectionDto).describedAs("Connection").isNotNull();
        assertThat(connectionDto.getId()).describedAs("Connection id").isNotNull();
        assertThat(connectionDto.getDatabaseName())
                .isEqualTo(TEST_DATABASE_NAME);
        assertThat(connectionDto.getHost())
                .isEqualTo(TEST_HOST);
        assertThat(connectionDto.getPort())
                .isEqualTo(TEST_PORT);
        assertThat(connectionDto.getUsername())
                .isEqualTo(TEST_USERNAME);
        assertThat(connectionDto.getPassword())
                .isEqualTo(TEST_PASSWORD);
    }
}
