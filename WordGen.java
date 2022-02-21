// I am the sole author of the work in this repository.

import java.util.Scanner;

/**
* WordGen class takes text input and analyzes the relative frequency of
* characters that proceed k-length substrings in the text. Basis these
* frequencies it will generated about 500 words of text to match style of
* seed text given to algorithm.
*
*
* @author Noah Cape
*/
public class WordGen {

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
  * main method is the entry point of the WordGen class
  * takes desired level of analysis and text to analyze.
  * <p>
  * WordGen will generate a Table from the input text and
  * analyze the frequency of proceeding characters from k-length
  * substrings.
  * <p>
  * Once Table of frequencies have been generated characters will be
  * generated with the initialized value being the starting k-length
  * substring from the training text. This processes will be done once
  * the length of generated text is >= 500 and the last character is an
  * end of a sentence
  */
  public static void main(String[] args) {
    // ensure that the user has given the desired level of analysis
    if (args.length == 0) {
      // if not tell them and quit program
      System.out.println("Usage: java WordGen <k length string> <seed text>");
      return;
    }

    // parse input text as seed String
    String seed = parseText();
    // initalized the level of analysis
    int k = Integer.parseInt(args[0]);

    // initalize empty Table to be populated
    Table<String, String> t = new Table<String, String>();

    // loop thru text  and populate Table
    for (int i = 0; i <= seed.length() - k; i++) {

      // print progress
      if (i % (seed.length() / 20) == 0) {
        System.out.println(i + "/" + seed.length() + " Current number of rows in Table: " + t.size());
      }

      // if we have reached a point where the next character will be undefined
      if (i == seed.length() - k) {
        // construct tempSeq which will be used to connect end of string to beginning
        String tempSeq = seed.substring(seed.length() - k, seed.length()) + " " + seed.substring(0, k);
        // use a tracker to only make k + 1 new seqs
        int newSeqs = 0;
        do {
          // add to Table from the tempSeq which will connect end to beginning as to never has
          // k-length sequences which are not accounted for
          t.add(tempSeq.substring(newSeqs, newSeqs + k), tempSeq.substring(newSeqs + k, newSeqs + k + 1));
          newSeqs++;
        } while (newSeqs != k + 1);
        break;
      }
      // add to Table substring of length k and the next character after substring
      t.add(seed.substring(i, i + k), seed.substring(i + k, i + k + 1));
    }

    System.out.println();
    System.out.println("Generated Text: ");
    System.out.println();

    // start generating text from generated Table
    int wordCount = 0;
    int index = 0;
    // start with k-length seq from beginning of seed text
    String randSeq = seed.substring(0, k);

    // while wordCount is less than desired length of output generate characters
    do {
      // generate a word based on the k-length substring from randSequ
      String nextString = t.choose(randSeq.substring(index, index + k));
      // add next character to the randSeq
      randSeq += nextString;
      // if we generated a new word add to word count
      if (Character.isWhitespace(nextString.charAt(0))) {
        wordCount++;
      }
      index++;

      // keep going until some sentence ending punctuation is selected
      if (wordCount == WORDS_IN_OUTPUT) {
        // if the next string we just generated is not an end to a sentence keep generating characters
        while (nextString.equals(".") ? false : !(nextString.equals("!") || nextString.equals("?"))) {
          nextString = t.choose(randSeq.substring(index, index + k));
          randSeq += nextString;
          index++;
        }
      }
    } while (wordCount < WORDS_IN_OUTPUT);

    // print generated words
    System.out.println(randSeq);
  }
}
