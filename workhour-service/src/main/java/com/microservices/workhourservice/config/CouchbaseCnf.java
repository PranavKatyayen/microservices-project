package com.microservices.workhourservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseCnf extends AbstractCouchbaseConfiguration {

    @Autowired
    private Environment env;

    @Override
    public String getConnectionString() {
        return env.getProperty("app.couchbase.host");
    }

    @Override
    public String getUserName() {
        return env.getProperty("app.couchbase.username");
    }

    @Override
    public String getPassword() {
        return env.getProperty("app.couchbase.password");
    }

    @Override
    public String getBucketName() {
        return env.getProperty("app.couchbase.bucket-employeeLeave");
    }

}
