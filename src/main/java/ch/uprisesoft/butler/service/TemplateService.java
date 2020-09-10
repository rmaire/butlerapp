
package ch.uprisesoft.butler.service;

import ch.uprisesoft.butler.model.Parameter;
import ch.uprisesoft.butler.model.Template;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
public class TemplateService {

    private List<Template> templates = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public TemplateService() {
        Template t1 = new Template();
        t1.setName("Test-1");
        t1.setCode("echo ${test1}\necho ${test2}");
        List<Parameter> t1Params = new ArrayList<>();
        Parameter p1 = new Parameter("test1", "");
        t1Params.add(p1);
        t1.setParameters(t1Params);
        t1.setId(counter.incrementAndGet());
        templates.add(t1);
        
        Template t2 = new Template();
        t2.setName("Test-2");
        t2.setCode("apt-get install ${package}");
        List<Parameter> t2Params = new ArrayList<>();
        t2Params.add(new Parameter("package", "apache2"));
        t2.setParameters(t2Params);
        t2.setId(counter.incrementAndGet());
        templates.add(t2);

        Template t3 = new Template();
        t3.setName("Create Confluence");
        String code = "docker run -d -p 8090 \\" + "\n";
        code += "-e \"SERVICE_TAGS=atlassian,confluence\" \\" + "\n";
        code += "-e X_PROXY_NAME='${name}.zuara.io' \\" + "\n";
        code += "-e X_PROXY_PORT='80' \\" + "\n";
        code += "-e X_PROXY_SCHEME='http' \\" + "\n";
        code += "-e X_MINIMUM_MEMORY='${xms}' \\" + "\n";
        code += "-e X_MAXIMUM_MEMORY='${xmx}' \\" + "\n";
        code += "--hostname ${name} \\" + "\n";
        code += "--name ${name} zuara/confluence:6.15.7";
        t3.setCode(code);
        List<Parameter> t3Params = new ArrayList<>();
        t3Params.add(new Parameter("name", ""));
        t3Params.add(new Parameter("xms", "1024m"));
        t3Params.add(new Parameter("xmx", "2048m"));
        t3.setParameters(t3Params);
        t3.setId(counter.incrementAndGet());
        templates.add(t3);

        Template t4 = new Template();
        t4.setName("Delete Confluence");
        t4.setCode("docker rm -f ${name}");
        List<Parameter> t4Params = new ArrayList<>();
        t4Params.add(new Parameter("name", ""));
        t4.setParameters(t4Params);
        t4.setId(counter.incrementAndGet());
        templates.add(t4);
    }

    public List<Template> getTemplates() {
        return templates;
    }
    
    public Template getTemplate(String name) {
    
        for(Template t: templates) {
            if(t.getName().equals(name)) {
                return t;
            }
        }
        return new Template();
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
    
    public Template addTemplate(Template template){
        template.setId(counter.incrementAndGet());
        templates.add(template);
        return template;
    }
    
    public Template updateTemplate(Template template){
        for(int i = 0; i < templates.size(); i++) {
            if(templates.get(i).getId().equals(template.getId())) {
                templates.set(i, template);
            }
        }
        return template;
    }

}
