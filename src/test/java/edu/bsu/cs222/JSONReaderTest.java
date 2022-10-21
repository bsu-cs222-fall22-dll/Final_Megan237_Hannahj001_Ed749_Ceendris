package edu.bsu.cs222;


import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class JSONReaderTest {
    @Test
    public void readName() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.parseName(object);


        Assertions.assertEquals("Clare", result);
    }

    @Test
    public void readEmail() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.parseEmail(object);


        Assertions.assertEquals("ceendris@bsu.edu", result);
    }

    @Test
    public void readPhoneNumber() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.parsePhoneNumber(object);


        Assertions.assertEquals("3172585282", result);
    }

    @Test
    public void readListOfClasses() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.parseClasses(object);

        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Advanced Programming"); testResult.add("Contemporary Art History");
        testResult.add("Product Management"); testResult.add("Research and Profession Practices");
        testResult.add("Printmaking II");

        Assertions.assertEquals(testResult, result);
    }
}
