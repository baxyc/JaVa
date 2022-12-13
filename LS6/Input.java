package LS6;

import java.util.Scanner;

public class Input {
    Scanner iScanner;

    public String inputString(String text)
    {
        while (true) {
            System.out.println(text);
            iScanner = new Scanner(System.in, "Cp866");
            try
            {
                String result = iScanner.next();
                return result;
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void scanerClose(){
        iScanner.close();
    }
}

