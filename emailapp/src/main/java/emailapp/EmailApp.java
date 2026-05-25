package emailapp;

public class EmailApp {

    public static void main(String[] args) {
        // test object 1
        Email em1 = new Email("Jon", "Smith");
        em1.setMailboxCapacity(400);

        System.out.println(em1.showInfo());
    }
}