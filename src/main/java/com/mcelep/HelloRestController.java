package com.mcelep;

import com.google.common.base.Charsets;

import com.google.common.io.CharStreams;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.InputStreamReader;

@RestController
public class HelloRestController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World " + readEnvironmentVariable() + " !";
    }

    @RequestMapping(path = "/slow", method = RequestMethod.GET)
    public String slow() throws Exception {
	Thread.sleep(15000);
        return "42";
    }
    
    @RequestMapping(path = "/xml", method = RequestMethod.GET,produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String xml() throws Exception{
        try( InputStreamReader reader = new InputStreamReader(HelloRestController.class.getResourceAsStream("/countries.xml"), Charsets.UTF_8);) {
            return CharStreams.toString(reader);
        }
    }

    private String readEnvironmentVariable(){
        String result = System.getenv("HELLO_WORLD_MSG");
        return result==null?"":result;
    }
}
