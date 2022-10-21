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
    public void getName() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.getName("User1");
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
    public void getEmail() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.getEmail("User1");
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
    public void getPhoneNumber() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.getPhoneNumber("User1");
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

    @Test
    public void getListOfClasses() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.getClasses("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Advanced Programming"); testResult.add("Contemporary Art History");
        testResult.add("Product Management"); testResult.add("Research and Profession Practices");
        testResult.add("Printmaking II");
        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void readListOfDays() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.parseDays(object);

        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("M"); testResult.add("W"); testResult.add("F"); testResult.add("M"); testResult.add("W"); testResult.add("F");
        testResult.add("T"); testResult.add("R"); testResult.add("T"); testResult.add("R"); testResult.add("T"); testResult.add("R");

        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void getListOfDays() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.getDays("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("M"); testResult.add("W"); testResult.add("F"); testResult.add("M"); testResult.add("W"); testResult.add("F");
        testResult.add("T"); testResult.add("R"); testResult.add("T"); testResult.add("R"); testResult.add("T"); testResult.add("R");
        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void getMondayArray() throws FileNotFoundException, URISyntaxException, ParseException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.mondayArray("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Advanced Programming"); testResult.add("Contemporary Art History");
        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void getTuesdayArray() throws FileNotFoundException, URISyntaxException, ParseException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.tuesdayArray("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Product Management"); testResult.add("Research and Profession Practices"); testResult.add("Printmaking II");
        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void getWednesdayArray() throws FileNotFoundException, URISyntaxException, ParseException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.wednesdayArray("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Advanced Programming"); testResult.add("Contemporary Art History");
        Assertions.assertEquals(testResult, result);
    }

    @Test
    public void getThursdayArray() throws FileNotFoundException, URISyntaxException, ParseException {
        JSONReader JSONFileReader = new JSONReader();
        ArrayList<String> result = JSONFileReader.thursdayArray("User1");
        ArrayList<String> testResult = new ArrayList<>();
        testResult.add("Product Management"); testResult.add("Research and Profession Practices"); testResult.add("Printmaking II");
        Assertions.assertEquals(testResult, result);
    }
}
