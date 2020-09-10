
package ch.uprisesoft.butler.model;

import java.util.ArrayList;
import java.util.List;

public class Template {
    private Long id;
    private String code;
    private String name;
    private List<Parameter> parameters;

    public Template(Long id, String code, String name, List<Parameter> parameters) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parameters = parameters;
    }

    public Template() {
        this(null, "", "", new ArrayList<>());
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
