package paritygame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParityGameFactory {

    static public ParityGame getParityGame(String path)
    {
        return getParityGame(new File(path));

    }

    static public ParityGame getParityGame(File formulaFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(formulaFile));

            return parseParityGame(reader);

        } catch (IOException e) {
            System.out.println("Something went wrong reading the file");
            e.printStackTrace();
            System.out.println(e);
            return null;
        }

    }

    static private ParityGame parseParityGame(BufferedReader reader) throws IOException {
        readSkipThrowError(reader, "parity ");

        int highestIdentifier = parseNatural(reader);

        ParityGame game = new ParityGame(highestIdentifier);

        readSkipThrowError(reader, ";\n");

        ParityNode node = null;

        while(node == null || node.identifier != highestIdentifier)
        {
            node = parseParityNode(reader);
            game.setnode(node);
        }
        return game;

    }

    static private ParityNode parseParityNode(BufferedReader reader) throws IOException {

        int identifier = parseNatural(reader);

        readSkipThrowError(reader, ' ');

        int priority = parseNatural(reader);

        readSkipThrowError(reader, ' ');

        boolean owner = parseBool(reader);

        readSkipThrowError(reader, ' ');

        int[] successors = parseSuccessor(reader);

        String name = parseString(reader);

        readSkipThrowError(reader, ";\n");

        return new ParityNode(identifier,priority,owner,successors,name);
    }

    static private int[] parseSuccessor(BufferedReader reader) throws IOException {

        List<Integer> list = new ArrayList<Integer>();

        do{
            list.add(parseNatural(reader));
            reader.mark(2);
        }
        while((char) reader.read() == ',');
        reader.reset();


        int[] result = new int[list.size()];

        for (int i= 0; i<list.size();i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    static private int parseNatural(BufferedReader reader) throws IOException {
        String completeString = "";
        reader.mark(2);

        while(true) {
            char character = (char) reader.read();
            // we continue untill we come accros one of the following signs
            if (character == ',' || character == ' '|| character == ';') {

                break;
            }

            completeString += character;

            reader.mark(2);
        }
        reader.reset();
        return  Integer.parseInt(completeString);
    }

    static private boolean parseBool(BufferedReader reader) throws IOException {
        switch ((char) reader.read())
        {
            case '1':
                return true;
            case '0':
                return false;
            default:
                throw new IOException("Parsing: 1 or 0 expected but did not get it");
        }
    }

    static private String parseString(BufferedReader reader) throws IOException {

        String completeString = "";
        reader.mark(3);

        if (readSkip(reader, " \"")) {

            while (true) {
                char character = (char) reader.read();

                if (character == '"') {

                    break;
                }

                completeString += character;

            }

        }
        else
        {
            reader.reset();
        }
        return  completeString;
    }


    static private void readSkipThrowError(BufferedReader reader, String expectedValue) throws IOException {

        if(!readSkip(reader,expectedValue))
        {
            throw new IOException("Parsing: \"" + expectedValue + "\" was expected but was not found");
        }
    }

    static private boolean readSkip(BufferedReader reader, String expectedValue) throws IOException {

        String foundValue = "";

        for (int i = 0; i < expectedValue.length(); i++) {
            foundValue += (char) reader.read();
        }

        return foundValue.equals(expectedValue);
    }

    static private void readSkipThrowError(BufferedReader reader, char expectedValue) throws IOException {

        if(!readSkip(reader,expectedValue))
        {
            throw new IOException("Parsing: \"" + expectedValue + "\" was expected but was not found.");
        }
    }

    static private boolean readSkip(BufferedReader reader, char expectedValue) throws IOException {

        return (char) reader.read() == expectedValue;
    }

}
