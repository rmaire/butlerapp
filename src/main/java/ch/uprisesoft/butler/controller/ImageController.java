package ch.uprisesoft.butler.controller;


import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    Map<String, String> getAllImages(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> imageMap = new HashMap<>();
        imageMap.put("zuara/jira:8.3.1", "Zuara Jira Image v. 8.3.1");

        return imageMap;
    }

}
