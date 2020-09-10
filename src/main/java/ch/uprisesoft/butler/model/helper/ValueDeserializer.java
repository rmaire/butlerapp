/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.uprisesoft.butler.model.helper;

import ch.uprisesoft.butler.api.model.values.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ValueDeserializer extends JsonDeserializer<Value>{

//    public ValueDeserializer() {
//        this(null);
//    }
//
//    public ValueDeserializer(Class<?> vc) {
//        super(vc);
//    }

    @Override
    public Value deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        return parse(node);
    }

    private Value parse(JsonNode node) {
        Value result = null;

        String type = node.get("type").asText();
        String name = node.get("name").asText();
        switch (type) {
            case ("BooleanValue"):
                Boolean booleanValue = node.get("value").asBoolean();
                result = Value.of(name, booleanValue);
                break;
            case ("DoubleValue"):
                Double doubleValue = node.get("value").asDouble();
                result = Value.of(name, doubleValue);
                break;
            case ("IntegerValue"):
                Integer intValue = node.get("value").asInt();
                result = Value.of(name, intValue);
                break;
            case ("StringValue"):
                String stringValue = node.get("value").asText();
                result = Value.of(name, stringValue);
                break;
            case ("ListValue"):
                Iterator<JsonNode> listElementsIterator = node.get("value").iterator();
                List<Value> listElements = new ArrayList<>();
                while (listElementsIterator.hasNext()) {
                    JsonNode element = listElementsIterator.next();
                    listElements.add(parse(element));
                }
                result = Value.of(name, listElements);
                break;
            case ("MapValue"):
                Iterator<Entry<String,JsonNode>> mapElementsNode = node.get("value").fields();
                Map<String, Value> mapElements = new HashMap<>();
                while (mapElementsNode.hasNext()) {
                    Entry<String,JsonNode> element = mapElementsNode.next();
                    String mapElementName = element.getKey();
                    Value mapElement = parse(element.getValue());
                    mapElements.put(mapElementName, mapElement);
                }
                result = Value.of(name, mapElements);
                break;
        }
        return result;
    }
}
