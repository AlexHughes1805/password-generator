import java.util.Scanner;
import java.io.File;  
import java.io.FileWriter;   
import java.security.SecureRandom;


public class passGen
{
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%&_-";

    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS;

    private static SecureRandom random = new SecureRandom();

    public static String pass(int n)
    {
        StringBuilder password = new StringBuilder(n);
        // At least one uppercase letter
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        // At least one lowercase letter
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        // At least one digit
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        // At least one special character
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Remaining characters randomly selected from all characters
        for (int i = 4; i < n; i++)
        {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Shuffle the characters in the password
        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword;
    }

    private static String shuffleString(String input)
    {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            int randomIndex = random.nextInt(charArray.length);
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }
        return new String(charArray);
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Please name the file");
        Scanner fileInput = new Scanner(System.in); // inistialise scanner
        String fileName = fileInput.nextLine(); // user input for txt file name
        System.out.println("How long do you want your password to be? (Between 8 and 12 characters)");
        int passLen = fileInput.nextInt(); // input for password length
        if (passLen < 8 || passLen > 12) // if the password is too long or too short, the user will be prompted to try again
        {
            System.out.println("Please input a number between 8 and 12");
            passLen = fileInput.nextInt();
        }
        fileInput.close(); // close scanner

        File f = new File("C:\\Users\\Alex\\Documents\\Passwords\\" + fileName + ".txt");
        if(!f.exists() && !f.isDirectory()) // if the file doesn't exist, create the file
        {
            // change directory to suit
            FileWriter passGen = new FileWriter("C:\\Users\\Alex\\Documents\\Passwords\\" + fileName + ".txt"); // create text file with user inputed name
            passGen.write(pass(passLen)); // write the generated password into the file
            System.out.println("File created: " + "C:\\Users\\Alex\\Documents\\Passwords\\" + fileName + ".txt"); // display file path to user
            passGen.close(); // close file writer
        }
        else // if the file already exists, state as such
        {
            System.out.println("File already exists: C:\\Users\\Alex\\Documents\\Passwords\\" + fileName + ".txt");
        }
        
    }
}
