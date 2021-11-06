package seedu.duke.command;

import seedu.duke.ItemList;
import seedu.duke.UI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCommand extends Command {

    public ReadCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void run(ItemList itemList) {

        itemList.items.clear();
        ItemList saveItemList = itemList;

        try {
            FileReader fileReader = new FileReader("./file/expenses.txt");

            Scanner lineReader = new Scanner(fileReader);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MMM-dd");

            while (lineReader.hasNext()) {
                String lineData = lineReader.nextLine();
                String[] splitString = lineData.split("\\s\\r?\\n");

                for (int i = 0; i < splitString.length; i++) {
                    String item = splitString[i].split(" ")[0];
                    String description = splitString[i].split(" ")[1];
                    String category = splitString[i].split(" ")[2];
                    String amount = splitString[i].split(" ")[3];
                    String date = splitString[i].split(" ")[4];

                    String descCatAmountAndDate = item + " " + category + " " + description + " " + amount + " " + date;

                    final String regex = "\\[(\\w+)\\]\\s(\\w+)\\s\\[(\\w+)\\]\\s\\"
                                         + "(\\$((\\d+).\\d+)\\)\\s\\(([(\\d+]{4}-[\\w+]{3}-[\\d+]{2})";

                    final Pattern pattern = Pattern.compile(regex);
                    final Matcher matcher = pattern.matcher(descCatAmountAndDate);

                    while (matcher.find()) {
                        String itemParser = matcher.group(1);
                        String descriptionParser = matcher.group(2);
                        String categoryParser = matcher.group(3);
                        Double amountParseDouble = Double.parseDouble(matcher.group(4));
                        Date dateParser = dateFormatter.parse(matcher.group(6));

                        switch (itemParser) {
                        case ("E"):
                            saveItemList.addExpense(descriptionParser, categoryParser, amountParseDouble, dateParser);
                            break;
                        case ("I"):
                            saveItemList.addIncome(descriptionParser, categoryParser, amountParseDouble, dateParser);
                            break;
                        default:
                            System.out.println("Please key in valid command!");
                        }
                    }
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Expenses file read successfully from file/expenses.txt.\n");
        UI.listMessage(saveItemList.items);
    }
}