package br.com.football.transfers;

import org.cassandraunit.spring.CassandraDataSet;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CassandraDataSet(keyspace = "football_transfers")
public abstract class SuccessReadBase extends BaseTests {

    public SuccessReadBase() {
        super(SuccessReadBase.shouldCreatePlayerFields());
    }

    public SuccessReadBase(Map<String, List<FieldDescriptor>> fieldDescriptors) {
        super(fieldDescriptors);
    }

    private static Map<String, List<FieldDescriptor>> shouldCreatePlayerFields() {

        Map<String, List<FieldDescriptor>> values = new HashMap<>();

        return values;
    }

}
