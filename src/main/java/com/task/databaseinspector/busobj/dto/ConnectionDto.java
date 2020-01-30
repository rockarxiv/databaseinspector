package com.task.databaseinspector.busobj.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
public class ConnectionDto {
    @NotNull(groups = Update.class, message = "{connection.id.null}")
    @Null(groups = New.class, message = "{connection.id.must.be.null}")
    private Long id;

    @NotNull(groups = New.class, message = "{connection.host.null}")
    private String host;

    @NotNull(groups = New.class, message = "{connection.port.null}")
    private String port;

    @NotNull(groups = New.class, message = "{connection.database.null}")
    private String databaseName;

    @NotNull(groups = New.class, message = "{connection.username.null}")
    private String username;

    @ToString.Exclude
    @JsonIgnore
    private String password;


    public interface Update {
    }

    public interface New {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectionDto)) return false;
        ConnectionDto that = (ConnectionDto) o;
        return Objects.equal(host, that.host) &&
                Objects.equal(port, that.port) &&
                Objects.equal(databaseName, that.databaseName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(host, port, databaseName);
    }
}
