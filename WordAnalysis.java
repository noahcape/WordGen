// I am the sole author of the work in this repository.

import java.util.Scanner;

/**
* WordAnalysis class implements Table and FrequencyList classes
* it takes text input to analyze and determine the relative frequency
* of proceeding words in the text. Data will be stored in Table and FrequencyList
* <p>
* After text is processed using data text will be generated to mimic the style of
* the seed text provided. Generated text will be about 500 words (may be a bit more
* in order to end of a finished sentence)
*
* @author Noah Cape
*/
public class WordAnalysis {

  // desired word count of generated text
  protected static final int WORDS_IN_OUTPUT = 500;

  /**
  * method to parse input text, from file or in directly thru command line
  *
  * @return text from file or command line as String
  */
  private static String parseText() {
    Scanner in = new Scanner(System.in);
    StringBuffer textBuffer = new StringBuffer();
    while (in.hasNextLine()) {
        String line = in.nextLine();
        textBuffer.append(line);
        textBuffer.append("\n");
    }
    return textBuffer.toString();
  }

  /**
  * main method in the entry point of program
  * will parse text then generate a Table
  * <p>
  * After Table is generated text is generated to
  * mimic the style of seed text.
  */
  public static void main(String[] args) {
    // parse text and split into string array to loop thru
    String[] seed = parseText().split(" ");

    // create empty table to populate
    Table<String, String> t = new Table<String, String>();

    // loop thru seed text and populate table
    for (int i = 0; i <= seed.length - 1; i++) {
      // shows progress
      if (i % (seed.length / 20) == 0) {
        System.out.println(i + "/" + seed.length + " Current number of rows in Table: " + t.size());
      }

      // if we are at the end of the text
      if (i == (seed.length - 1)) {
        // add word and because there is no next word add the starting word
        t.add(seed[seed.length - 1], seed[0]);
        break;
      }
      // add word and next word in text
      t.add(seed[i], seed[i + 1]);
    }

    System.out.println();
    System.out.println("Generated Text: ");
    System.out.println();

    // initialize value
    String curWord = seed[0];

    // start by printing the initialized word
    System.out.print(curWord + " ");

    // intanciated the next string value
    String nextString;

    // loop to generate about 500 words of text
    for (int j = 0; j < WORDS_IN_OUTPUT; j++) {
      // choose a next string basis the curWord
      nextString = t.choose(curWord);
      // print generated word
      System.out.print(nextString + " ");
      // set the generated word as curWord
      curWord = nextString;
    }

    // while the word which was just generated is not the end of a sentence keep going until
    // end of a sentence has been reached
    while (curWord.lastIndexOf('.') == curWord.length() - 1
              ? false
              : !(curWord.lastIndexOf('!') == curWord.length() - 1 || curWord.lastIndexOf('?') == curWord.length() - 1)) {
      // choose a next string basis curWord
      nextString = t.choose(curWord);
      // print out generated word
      System.out.print(nextString + " ");
      // set the generated word as curWord
      curWord = nextString;
    }

    System.out.println();
  }
}
