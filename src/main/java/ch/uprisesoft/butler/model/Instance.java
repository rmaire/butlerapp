package ch.uprisesoft.butler.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Instance {

    private final String name;
    private final int memory; //In MB
    private final String image;
    private final Host host;

    public Instance(@JsonProperty("name") String name,
            @JsonProperty("memory") int memory,
            @JsonProperty("image") String image,
            @JsonProperty("host") Host host) {
        this.name = name;
        this.memory = memory;
        this.image = image;
        this.host = host;
    }
    
    public String getName() {
        return name;
    }

    public int getMemory() {
        return memory;
    }

    public String getImage() {
        return image;
    }

    public Host getHost() {
        return host;
    }

}
