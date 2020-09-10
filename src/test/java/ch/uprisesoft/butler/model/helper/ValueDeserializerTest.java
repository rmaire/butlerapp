/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model.helper;

import ch.uprisesoft.butler.plugin.api.model.values.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author rma
 */
public class ValueDeserializerTest {

    ObjectMapper mapper;

    public ValueDeserializerTest() {
    }

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Value.class, new ValueDeserializer());
        mapper.registerModule(module);
    }

    @Test
    public void testBooleanValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"booleansample\",\n"
                + "            \"value\": true,\n"
                + "            \"type\": \"BooleanValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("BooleanValue"));
        assertThat(val.asBoolean().getName(), is("booleansample"));
        assertThat(val.asBoolean().getValue(), is(true));
    }

    @Test
    public void testDoubleValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"doublesample\",\n"
                + "            \"value\": 42.23,\n"
                + "            \"type\": \"DoubleValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("DoubleValue"));
        assertThat(val.asDouble().getName(), is("doublesample"));
        assertThat(val.asDouble().getValue(), is(42.23));
    }

    @Test
    public void testIntegerValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"integersample\",\n"
                + "            \"value\": 42,\n"
                + "            \"type\": \"IntegerValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("IntegerValue"));
        assertThat(val.asInteger().getName(), is("integersample"));
        assertThat(val.asInteger().getValue(), is(42));
    }

    @Test
    public void testStringValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"stringsample\",\n"
                + "            \"value\": \"Hello world\",\n"
                + "            \"type\": \"StringValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("StringValue"));
        assertThat(val.asString().getName(), is("stringsample"));
        assertThat(val.asString().getValue(), is("Hello world"));
    }

    @Test
    public void testListValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"listsample\",\n"
                + "            \"value\": [\n"
                + "                {\n"
                + "                    \"name\": \"\",\n"
                + "                    \"value\": \"sample1\",\n"
                + "                    \"type\": \"StringValue\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"name\": \"sample2\",\n"
                + "                    \"value\": 99,\n"
                + "                    \"type\": \"IntegerValue\"\n"
                + "                }\n"
                + "            ],\n"
                + "            \"type\": \"ListValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("ListValue"));
        assertThat(val.asList().getName(), is("listsample"));

        List<Value> elements = val.asList().getValue();

        assertThat(elements.size(), is(2));
        assertThat(elements.get(0).getType(), is("StringValue"));
        assertThat(elements.get(0).asString().getName(), is(""));
        assertThat(elements.get(0).asString().getValue(), is("sample1"));

        assertThat(elements.get(1).getType(), is("IntegerValue"));
        assertThat(elements.get(1).asInteger().getName(), is("sample2"));
        assertThat(elements.get(1).asInteger().getValue(), is(99));
    }

    @Test
    public void testMapValueDeserializing() throws IOException {
        String json = "{\n"
                + "            \"name\": \"mapsample\",\n"
                + "            \"value\": {\n"
                + "                \"mapsample2\": {\n"
                + "                    \"name\": \"sample2\",\n"
                + "                    \"value\": 99,\n"
                + "                    \"type\": \"IntegerValue\"\n"
                + "                },\n"
                + "                \"mapsample1\": {\n"
                + "                    \"name\": \"\",\n"
                + "                    \"value\": \"sample1\",\n"
                + "                    \"type\": \"StringValue\"\n"
                + "                }\n"
                + "            },\n"
                + "            \"type\": \"MapValue\"\n"
                + "        }";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("MapValue"));
        assertThat(val.asMap().getName(), is("mapsample"));

        Map<String, Value> elements = val.asMap().getValue();

        assertThat(elements.size(), is(2));
        assertThat(elements.get("mapsample1").getType(), is("StringValue"));
        assertThat(elements.get("mapsample1").asString().getName(), is(""));
        assertThat(elements.get("mapsample1").asString().getValue(), is("sample1"));

        assertThat(elements.get("mapsample2").getType(), is("IntegerValue"));
        assertThat(elements.get("mapsample2").asInteger().getName(), is("sample2"));
        assertThat(elements.get("mapsample2").asInteger().getValue(), is(99));
    }

    @Test
    public void testNestedMapValueDeserializing() throws IOException {
        String json = "{\n"
                + "    \"name\": \"\",\n"
                + "    \"value\": [\n"
                + "        {\n"
                + "            \"name\": \"mapsample\",\n"
                + "            \"value\": {\n"
                + "                \"mapsample2\": {\n"
                + "                    \"name\": \"\",\n"
                + "                    \"value\": 99,\n"
                + "                    \"type\": \"IntegerValue\"\n"
                + "                }\n"
                + "            },\n"
                + "            \"type\": \"MapValue\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"type\": \"ListValue\"\n"
                + "}";
        Value val = mapper.readValue(json, Value.class);

        assertThat(val.getType(), is("ListValue"));
        assertThat(val.asList().getName(), is(""));

        Map<String, Value> elements = val.asList().getValue().get(0).asMap().getValue();

        assertThat(elements.size(), is(1));
        assertThat(elements.get("mapsample2").getType(), is("IntegerValue"));
        assertThat(elements.get("mapsample2").asInteger().getName(), is(""));
        assertThat(elements.get("mapsample2").asInteger().getValue(), is(99));
    }
}
