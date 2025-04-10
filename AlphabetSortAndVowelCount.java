import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
* This program will count the number of vowels in a string
* and sort the string in alphabetical order using bubble sort method.
* While taking the input from the input file and writing the output to
* the output file.
*
* @author Remy Skelton
* @version 1.0
* @since 2025-04-08
*/

final class AlphabetSortAndVowelCount {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private AlphabetSortAndVowelCount() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {
        // Welcome message
        System.out.print("Welcome to the Alphabet "
        + "Sort and Vowel Count program! ");
        System.out.print("This program will count the "
        + "number of vowels in a string. ");
        System.out.print("It will sort the string in "
        + "alphabetical order using the ");
        System.out.println("bubble sort method.");

        System.out.print("The input is taken from "
        + "the input file and the ");
        System.out.println("output is written to the output file.");

        // Create file object for input
        File inputFile = new File("Assign-02-input.txt");

        // Create a file writer object to write to the output file
        FileWriter myWriter = new FileWriter("./Assign-02-output.txt");

        // Create a scanner object to read the file
        Scanner scanner = new Scanner(inputFile);

        // Initialize the list for number of y's on a line
        ArrayList<Integer> listYCount = new ArrayList<>();

        // Initialize the list for number of vowels on a line
        ArrayList<Integer> listVowelCounts = new ArrayList<>();

        // Initialize the list for words on a line in input file
        ArrayList<String> listWords = new ArrayList<>();

        // Loop through the file
        while (scanner.hasNextLine()) {
            // Set line equal to the next line
            String line = scanner.nextLine();

            String[] lineWords = line.split(" ");

            // For loop to add word to list of word and count vowels and y
            for (String word : lineWords) {
                // Check if the word is not empty
                if (!word.isEmpty()) {
                    // Add the word to the list
                    listWords.add(word);

                    // Set array of y count
                    // and vowel count to countVowels method
                    int[] vowelCount = countVowels(word);
                    // Add the vowel count to the list
                    listVowelCounts.add(vowelCount[0]);
                    // Add the y count to the list
                    listYCount.add(vowelCount[1]);
                }
            }
        }
        // Set the array of the sorted words y
        // and vowel count to the bubble sort method
        String[][] arrayWordsAndVowels
        = bubbleSort(listWords, listVowelCounts, listYCount);

        // Set the sorted 2D array of words
        // equal to first index of arrayWordsAndVowels
        String[] sortedWords = arrayWordsAndVowels[0];

        // Set the sorted array of vowel counts
        // equal to second index of arrayWordsAndVowels
        String[] sortedVowelCounts = arrayWordsAndVowels[1];
        // Set the sorted array of y counts
        // equal to third index of arrayWordsAndVowels
        String[] sortedYCounts = arrayWordsAndVowels[2];

        // Write sorted words to the output file
        myWriter.write("Sorted words: \n");

        // Write sorted words to the output file
        for (String word : sortedWords) {
            myWriter.write(word + "\n");
        }

        // Write vowel count to the output file
        myWriter.write("\nVowel count: \n");
        // Write vowel count to the output file
        for (int line = 0; line < sortedVowelCounts.length; line++) {
            myWriter.write(sortedWords[line] + ": "
                    + sortedVowelCounts[line] + " vowels and "
                    + sortedYCounts[line] + " number of y's\n");
        }
        // Print that the file has been written
        System.out.println("The file has been written to output.txt");

        // Close the scanner
        scanner.close();
        // Close the file writer
        myWriter.close();
    }
    /**
     * This method will count the number of vowels in a string.
     *
     * @param word The word to count the vowels in.
     * @return An array of integers with the vowel count and y count.
     */
    public static int[] countVowels(final String word) {
        // Initialize the vowel count and y count
        int vowelCount = 0;
        int yCount = 0;

        // For loop to count number of vowels in a word
        for (int letter = 0; letter < word.length(); letter++) {
            // Check if the letter is a vowel
            if (Character.toLowerCase(word.charAt(letter)) == 'a'
                    || Character.toLowerCase(word.charAt(letter)) == 'e'
                    || Character.toLowerCase(word.charAt(letter)) == 'i'
                    || Character.toLowerCase(word.charAt(letter)) == 'o'
                    || Character.toLowerCase(word.charAt(letter)) == 'u') {
                // Add to the vowel count
                vowelCount++;
            }
            // Check if the letter is y
            if (Character.toLowerCase(word.charAt(letter)) == 'y') {
                /// Add to the y count
                yCount++;
            }
        }

        // Return the vowel count and y count
        return new int[] {vowelCount, yCount };

    }

    /**
     * This method will sort the words in alphabetical order.
     *
     * @param listWords The list of words to sort.
     * @param listVowelCounts The list of vowel counts to sort.
     * @param listYCounts The list of y counts to sort.
     * @return An array of strings with the sorted words and their vowel counts.
     */
    public static String[][] bubbleSort(final ArrayList<String> listWords,
            final ArrayList<Integer> listVowelCounts,
            final ArrayList<Integer> listYCounts) {
        // For loop to sort the words in alphabetical order
        for (int pass = 0; pass < listWords.size(); pass++) {
            for (int index = 0; index < listWords.size() - pass - 1; index++) {
                // Check if the word is greater than the next word
                if
                (listWords.get(index).compareTo(listWords.get(index + 1)) > 0) {
                    // Swap the words
                    String tempWord = listWords.get(index);
                    listWords.set(index, listWords.get(index + 1));
                    listWords.set(index + 1, tempWord);

                    // Swap the vowel counts
                    int tempVowelCount = listVowelCounts.get(index);
                    listVowelCounts.set(index, listVowelCounts.get(index + 1));
                    listVowelCounts.set(index + 1, tempVowelCount);

                    // Swap the y counts
                    int tempYCount = listYCounts.get(index);
                    listYCounts.set(index, listYCounts.get(index + 1));
                    listYCounts.set(index + 1, tempYCount);
                }
            }
        }

        // Create arrays from the lists for return
        String[] sortedWords = listWords.toArray(new String[0]);

        // Convert listVowelCounts to String[]
        String[] sortedVowelCounts = new String[listVowelCounts.size()];
        for (int index = 0; index < listVowelCounts.size(); index++) {
            sortedVowelCounts[index]
            = String.valueOf(listVowelCounts.get(index));
        }

        // Convert listYCounts to String[]
        String[] sortedYCounts = new String[listYCounts.size()];
        for (int index = 0; index < listYCounts.size(); index++) {
            sortedYCounts[index] = String.valueOf(listYCounts.get(index));
        }

        // Return the sorted arrays as a 2D array
        return new String[][] {sortedWords, sortedVowelCounts, sortedYCounts };
    }


}
