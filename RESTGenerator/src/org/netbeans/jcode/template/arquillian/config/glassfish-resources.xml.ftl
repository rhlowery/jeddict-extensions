<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE resources PUBLIC
    "-//GlassFish.org//DTD GlassFish Application Server 3.1 Resource Definitions//EN"
    "http://glassfish.org/dtds/glassfish-resources_1_5.dtd">
<resources>
    <jdbc-resource pool-name="ArquillianEmbeddedDerbyPool" jndi-name="jdbc/arquillian"/>
    <jdbc-connection-pool name="ArquillianEmbeddedDerbyPool" res-type="javax.sql.DataSource"
                          datasource-classname="org.apache.derby.jdbc.EmbeddedDataSource">
        <property name="databaseName" value="target/databases/derby"/>
        <property name="createDatabase" value="create"/>
    </jdbc-connection-pool>
    
    
    <!--    <jdbc-resource pool-name="ArquillianEmbeddedH2Pool" jndi-name="jdbc/payaraEmbedded"/>
    <jdbc-connection-pool name="ArquillianEmbeddedH2Pool" res-type="javax.sql.DataSource"
                          datasource-classname="org.h2.jdbcx.JdbcDataSource">
        <property name="user" value="sa"/>
        <property name="password" value=""/>
        <property name="url" value="jdbc:h2:mem:payaraEmbedded"/>
    </jdbc-connection-pool>-->
</resources>

<!--This is ignored now and JavaEE 7 default datasource is used-->
