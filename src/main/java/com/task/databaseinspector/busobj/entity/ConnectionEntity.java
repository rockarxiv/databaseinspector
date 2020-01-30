package com.task.databaseinspector.busobj.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "connection", uniqueConstraints=
@UniqueConstraint(columnNames={"host", "port", "databaseName"}, name = "host_port_databaseName_unique") )
@NoArgsConstructor
public class ConnectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connection_id")
    @SequenceGenerator(name="connection_id", sequenceName = "connection_id", allocationSize=1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String host;
    private String port;
    private String databaseName;
    private String username;
    @ToString.Exclude
    private String password;

    public ConnectionEntity(String host, String port, String databaseName, String username, String password) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }
}
