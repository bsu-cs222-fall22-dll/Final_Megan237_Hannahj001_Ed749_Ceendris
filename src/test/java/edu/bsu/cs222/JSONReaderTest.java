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


public class JSONReaderTest {
    @Test
    public void getObject() throws FileNotFoundException, URISyntaxException, ParseException {
        JSONReader JSONFileReader = new JSONReader();
        Object result = JSONFileReader.getJsonObject("User1");
        String actual = "[[[[[{\"Roommate\":\"hjjohnston@bsuedu\",\"Email\":\"ceendris@bsu.edu\",\"User\":\"Clare\",\"PhoneNumber\":\"3172585282\",\"Roommate2\":\"email2\",\"Avatar\":\"https:\\/\\/website.com\\/headshot.jpg\",\"Password\":\"1234\",\"Classes\":[{\"Meetings\":[{\"Start\":\"11:00\",\"End\":\"11:50\",\"Day\":\"M\"},{\"Start\":\"11:00\",\"End\":\"11:50\",\"Day\":\"W\"},{\"Start\":\"11:00\",\"End\":\"11:50\",\"Day\":\"F\"}],\"ID:\":\"CS 222\",\"Name\":\"Advanced Programming\"},{\"Meetings\":[{\"Start\":\"15:00\",\"End\":\"15:50\",\"Day\":\"M\"},{\"Start\":\"15:00\",\"End\":\"15:50\",\"Day\":\"W\"},{\"Start\":\"15:00\",\"End\":\"15:50\",\"Day\":\"F\"}],\"ID:\":\"AHS 210\",\"Name\":\"Contemporary Art History\"},{\"Meetings\":[{\"Start\":\"11:00\",\"End\":\"12:15\",\"Day\":\"T\"},{\"Start\":\"11:00\",\"End\":\"12:15\",\"Day\":\"R\"}],\"ID:\":\"MKG 400\",\"Name\":\"Product Management\"},{\"Meetings\":[{\"Start\":\"12:30\",\"End\":\"15:15\",\"Day\":\"T\"},{\"Start\":\"12:30\",\"End\":\"15:15\",\"Day\":\"R\"}],\"ID:\":\"ADS 441\",\"Name\":\"Research and Profession Practices\"},{\"Meetings\":[{\"Start\":\"15:30\",\"End\":\"18:15\",\"Day\":\"T\"},{\"Start\":\"15:00\",\"End\":\"18:15\",\"Day\":\"R\"}],\"ID:\":\"AFA 272\",\"Name\":\"Printmaking II\"}]}]]]],{\"Event\":[{\"EndTime\":\"12:50\",\"Days0\":[\"monday\",\"wednesday\",\"thursday\"],\"StartTime\":\"11:00\",\"EventName\":\"test\"}]}]";
        Assertions.assertEquals(actual, result.toString());
    }

    @Test
    public void readName() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


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
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


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
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


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
    public void getPassword() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = JSONFileReader.getPassword("User1");
        Assertions.assertEquals("1234", result);
    }

    @Test
    public void readRoommates() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.parseRoommates(object));


        Assertions.assertEquals("[hjjohnston@bsuedu]", result);
    }

    @Test
    public void getRoommates() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        System.out.println(JSONFileReader.getRoommates("User1"));
        String result = String.valueOf(JSONFileReader.getRoommates("User1"));
        Assertions.assertEquals("[hjjohnston@bsuedu]", result);
    }

    @Test
    public void readEvents() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.parseEvent(object));


        Assertions.assertEquals("[[test], [], [test], [test], [], [11:00, 12:50], [], [11:00, 12:50], [11:00, 12:50], []]", result);
    }

    @Test
    public void getEvent() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.getEvent("User1"));
        Assertions.assertEquals("[[test], [], [test], [test], [], [11:00, 12:50], [], [11:00, 12:50], [11:00, 12:50], []]", result);
    }

    @Test
    public void readStartTimes() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.parseStartTime(object));


        Assertions.assertEquals("[\"11:00\"]", result);
    }

    @Test
    public void getStartTimes() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.getStartTime("User1"));
        Assertions.assertEquals("[\"11:00\"]", result);
    }

    @Test
    public void readEndTimes() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.parseEndTime(object));


        Assertions.assertEquals("[\"12:50\"]", result);
    }

    @Test
    public void getEndTimes() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.getEndTime("User1"));
        Assertions.assertEquals("[\"12:50\"]", result);
    }

    @Test
    public void readEventNames() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.eventNames(object));


        Assertions.assertEquals("[\"test\"]", result);
    }

    @Test
    public void getEventNames() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String result = String.valueOf(JSONFileReader.getEventNames("User1"));
        Assertions.assertEquals("[\"test\"]", result);
    }

    @Test
    public void readDaysBasedOnEventName() throws URISyntaxException, FileNotFoundException, ParseException {
        JSONParser jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


        URL resource = getClass().getClassLoader().getResource("User1.json");
        assert resource != null;
        FileReader reader = new FileReader(new File(resource.toURI()));
        Object object = jsonParser.parse(reader);
        JSONReader JSONFileReader = new JSONReader();
        String event = String.valueOf(JSONFileReader.getEvent("User1"));
        String email = JSONFileReader.getEmail("User1");
        String result = String.valueOf(JSONFileReader.parseDaysBasedOnEventName(object, event, email));


        Assertions.assertEquals("[]", result);
    }

    @Test
    public void getNamesBasedOnEventName() throws FileNotFoundException, ParseException, URISyntaxException {
        JSONReader JSONFileReader = new JSONReader();
        String event = String.valueOf(JSONFileReader.getEvent("User1"));
        String email = JSONFileReader.getEmail("User1");
        String result = String.valueOf(JSONFileReader.getDaysBasesOnEventName(email, event));
        Assertions.assertEquals("[]", result);
    }
}
