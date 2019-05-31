import java.util.Scanner;

public class AccountBook {
    private RecordNode head;
    private RecordNode tail;
    private double balance;

    public AccountBook() {
        head = null;
        tail = null;
        balance = 0;
    }

    /**
     * Insert a record node into the account book. The money amount can be either negative, meaning
     * the user spent money, or positive, meaning the user received money. If in the account book
     * there are records on the same day, you need to insert the record after the last of them;
     * Otherwise, you need to insert the record between records on earlier days and those on later
     * days.
     * 
     * @param day The day of the record to be inserted.
     * @param amount The money amount of the record.
     */
    public void insertRecord(int day, double amount) {
        RecordNode data = head;
        RecordNode record = new RecordNode(day, amount);
        if (data == null) {// if no record
            head = record;
            tail = record;
            return;
        } else if (data == head && data == tail && head.getDay() > day) {// prepend with one node
            record.setNext(head);
            head = record;
            return;
        } else if (data == head && data == tail && head.getDay() <= day) {
            head.setNext(record);
            tail = record;
            return;
        }

        while (data.getNext() != null) {// if have record
            if ((data.getDay() < day && data.getNext().getDay() > day)// between 2 nodes
                            || (data.getDay() == day && data.getNext().getDay() != day)) {// append
                                                                                          // at same
                                                                                          // day
                record.setNext(data.getNext());
                if (data == head) {
                    head.setNext(record);
                } else {
                    data.setNext(record);
                }
                return;
            }

            data = data.getNext();
            if (data.getDay() <= day && data == tail) { // if found at tail
                tail.setNext(record);
                tail = record;
                return;
            }
        }
    }

    /**
     * Prepend a record into the account book. The day of the record should be the same as the
     * EARLIEST record in the book. If there haven't been any records in the book yet, you should
     * show user the warning message "WARNING: Unable to prepend a record, for no records in the
     * account book yet." by printing it to the console.
     * 
     * @param amount The money amount of the record to be prepended.
     */
    public void prependRecord(double amount) {
        try {
            int day = head.getDay();
            RecordNode record = new RecordNode(day, amount);
            record.setNext(head);
            head = record;
        } catch (NullPointerException e) {
            System.out.println(
                            "WARNING: Unable to prepend a record, for no records in the account book yet.");
        }

    }

    /**
     * Append a record into the account book. Similar as above, the day of the record should be the
     * same as the LATEST record. If there haven't no records in the book yet, you should show user
     * the warning message "WARNING: Unable to append a record, for no records in the account book
     * yet.".
     * 
     * @param amount The money amount of the record to be appended.
     */
    public void appendRecord(double amount) {
        try {
            int day = tail.getDay();
            RecordNode record = new RecordNode(day, amount);
            tail.setNext(record);
            tail = record;
        } catch (NullPointerException e) {
            System.out.println(
                            "WARNING: Unable to append a record, for no records in the account book yet");
        }
    }

    /**
     * Remove a record from the account book. The two arguments identify which record to remove.
     * E.g., with day being 4 and seq_num being 2, the user would like to delete the second record
     * on the 4th day. If the number of records on day is smaller than seq_num, you show user the
     * warning message "WARNING: Unable to remove a record, for not enough records on the day
     * specified.".
     * 
     * @param day The day of the record to be removed.
     * @param seq_num The sequence number of the record within the day of it.
     */
    public void removeRecord(int day, int seq_num) {
        try {
            RecordNode data = head;
            int counter = 1;
            if (data == tail) {// if there is only 1 node
                if (data.getDay() == day) {
                    if (seq_num == counter) {
                        head = null;
                        tail = null;
                        return;
                    }
                } else {
                    throw new NullPointerException();
                }
            } else if (data == head && data.getDay() == day) {// remove head
                if (seq_num == counter) {
                    head = data.getNext();
                    return;
                }
                counter++;
            }
            while (data.getNext() != null) {
                if (data.getNext().getDay() == day) {
                    if (seq_num == counter) {
                        if (data.getNext() == tail) {// remove tail
                            data.setNext(null);
                            tail = data;
                        }
                        data.setNext(data.getNext().getNext());
                    }
                    counter++;
                }
                data = data.getNext();
            }
            if (counter-1 < seq_num) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println(
                            "WARNING: Unable to remove a record, for not enough records on the day specified.");
        }
    }

    /**
     * Modify a record in the account book. Similar as above, day and seq_num identify which record
     * to modify, while amount indicates the excepted money amount of the record after modification
     * E.g., with the three arguments being 4 2 100 respectively, the user would like to modify the
     * second record on the 4th day, and change the amount to 100. If the number of records on day
     * is smaller than seq_num , you should show user the warning message "WARNING: Unable to modify
     * a record, for not enough records on the day specified.".
     * 
     * @param day The day of the record to be modified.
     * @param seq_num The sequence number of the record within the day of it.
     * @param amount The amount of the record after modified.
     */
    public void modifyRecord(int day, int seq_num, double amount) {
        RecordNode data = head;
        int counter = 1;
        while (data != null) {
            if (data.getDay() == day) {
                if (seq_num == counter) {
                    data.setAmount(amount);
                }
                counter++;
            }
            data = data.getNext();
        }
        if (counter-1 < seq_num) {
            System.out.println(
                            "WARNING: Unable to remove a record, for not enough records on the day specified.");
        }
    }

    /**
     * Show user the overall balance by printing some leading textual prompt followed by the balance
     * to the console, e.g., "Balance: -90.95". The balance should be initialized as 0 at first, and
     * accumulates as the user insert/prepend/append/remove/modify records.
     */
    public void showBalance() {
        balance = 0;
        RecordNode data = head;
        while (data != null) {
            balance = balance + data.getAmount();
            data = data.getNext();
        }
        System.out.printf("%nBalance: $%.2f%n", balance);
    }

    /**
     * Display all the records so far as well as the overall balance. If there haven't been no
     * records in the book yet, you should display "No records in the book yet." before displaying
     * the account balance.
     */
    public void display() {
        if (head == null) {
            System.out.println("No records in the book yet.");
        } else {
            System.out.println("Day\tAmount");
            System.out.println("================");
            RecordNode data = head;
            while (data != null) {
                System.out.printf("%d\t$%.2f%n", data.getDay(), data.getAmount());
                data = data.getNext();
            }
        }
        showBalance();
    }

    /**
     * Show the records and accumulated balance on the day specified. If in the account book there
     * haven't been any records on the day specified yet, you should display "No records on the day
     * yet." before displaying the accumulated balance.
     * 
     * @param day The day of the summary to be shown.
     */
    public void showDaySummary(int day) {
        int check = 0;
        RecordNode data = head;
        while (data != null) {
            if (data.getDay() == day) {
                check = 1;
            }
            data = data.getNext();
        }
        data = head;
        if (data == null || check == 0) {
            System.out.println("No records on the day yet");
            System.out.printf("%nAccumulated Balance: $%.2f%n", balance);
            return;
        }
        System.out.println("Day\tAmount");
        System.out.println("================");
        balance = 0;
        while (data != null) {
            if (data.getDay() == day) {
                System.out.printf("%d\t$%.2f%n", data.getDay(), data.getAmount());
                balance = balance + data.getAmount();
            }
            data = data.getNext();
        }
        System.out.printf("%nAccumulated Balance: $%.2f%n", balance);
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        AccountBook acc = new AccountBook();
        boolean loop = true;

        while (loop) {
            System.out.println("=====Account Book Program=====");
            System.out.println("1. Enter 'i' to insert a record into the account book");
            System.out.println("2. Enter 'p' to prepend a record into the account book");
            System.out.println("3. Enter 'a' to append a record into the account book");
            System.out.println("4. Enter 'r' to remove a record from the account book");
            System.out.println("5. Enter 'm' to modify a record in the account book");
            System.out.println("6. Enter 'b' to show the overall balance");
            System.out.println("7. Enter 'd' to display all the records and the overall balance");
            System.out.println("8. Enter 's' to show the records and accumulated balance on a day");
            System.out.println("0. Enter 'q' to quit the program");
            System.out.println("==============================");
            System.out.print("Please enter your command:");
            String input = scnr.nextLine();

            input = input.trim();
            String[] temp = input.split(" ");
            temp[0] = temp[0].trim();
            char in = temp[0].toLowerCase().charAt(0);
            try {
                switch (in) {
                    case 'i':
                        if (Integer.parseInt(temp[1]) < 1 || Integer.parseInt(temp[1]) > 31) {
                            throw new NumberFormatException("WARNING: Invalid day number.");
                        }
                        acc.insertRecord(Integer.parseInt(temp[1]), Double.parseDouble(temp[2]));
                        break;
                    case 'p':
                        acc.prependRecord(Integer.parseInt(temp[1]));
                        break;
                    case 'a':
                        acc.appendRecord(Integer.parseInt(temp[1]));
                        break;
                    case 'r':
                        if (Integer.parseInt(temp[1]) < 1 || Integer.parseInt(temp[1]) > 31) {
                            throw new NumberFormatException("WARNING: Invalid day number.");
                        }
                        if (Integer.parseInt(temp[2]) < 1) {
                            throw new NumberFormatException("WARNING: Sequence number.");
                        }
                        acc.removeRecord(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                        break;
                    case 'm':
                        if (Integer.parseInt(temp[1]) < 1 || Integer.parseInt(temp[1]) > 31) {
                            throw new NumberFormatException("WARNING: Invalid day number.");
                        }
                        if (Integer.parseInt(temp[2]) < 1) {
                            throw new NumberFormatException("WARNING: Sequence number.");
                        }
                        acc.modifyRecord(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
                                        Double.parseDouble(temp[3]));
                        break;
                    case 'b':
                        acc.showBalance();
                        break;
                    case 'd':
                        acc.display();
                        break;
                    case 's':
                        if (Integer.parseInt(temp[1]) < 1 || Integer.parseInt(temp[1]) > 31) {
                            throw new NumberFormatException("WARNING: Invalid day number.");
                        }
                        acc.showDaySummary(Integer.parseInt(temp[1]));
                        break;
                    case 'q':
                        loop = false;
                        break;
                    default:
                        System.out.println("WARNING: Unrecognized command.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } // catch (Exception e) {
              // System.out.println("WARNING: Unrecognized command.");
              // }
        }
    }

}
