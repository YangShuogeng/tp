package seedu.duke;

public class InputErrorException extends Exception {
    private String errorType;

    public InputErrorException(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }

    static void toPrintDateFormatError() {
        System.out.println("OOPS!!! The input date format wrong. Please try again.\n");
    }

    static void toPrintSortFormatError() {
        System.out.println("OOPS!!! The sort input format wrong. Please try again.\n");
        System.out.println("[Help] Command: \n sort /asc /amount \n"
                            + " sort /desc /amount \n");
    }

    static void toPrintEmptyFileError() {
        System.out.println("OOPS!!! The list is empty. Please read the file or add expenses.\n");
    }

    static void toPrintInvalidCommand() {

        System.out.println("OOPS!!! Invalid command input. Please try again.\n");
    }

    static void toPrintIndexNotInt() {
        System.out.println("OOPS!!! The index you typed is not an integer. Please try again.\n");
    }

    static void toPrintIndexOutOfRange() {

        System.out.println("OOPS!!! The index out of range. Please try again.\n");
    }

    static void toPrintAmountNotNumber() {
        System.out.println("OOPS!!! The amount is not a number. Please try again.\n");
    }

    static void toPrintInputInfoShortage() {
        System.out.println("OOPS!!! The input information not enough. Please try again.\n");
    }
}
