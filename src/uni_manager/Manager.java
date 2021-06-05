package uni_manager;

import java.util.*;

public class Manager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while( !input.equals("quit") ) {

            CommandParser cp = new CommandParser(input);
            if( cp.getError() != null )
                System.out.println(cp.getError());
            else
                cp.Launch();

            input = sc.nextLine();
        }
    }

}