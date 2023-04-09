/**
 * Authors: Edson Ricardo da Costa & Gabriel Fanto Stundner.
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;

public interface CipherController {
    public String readCipherText(String path) throws IOException;
    public void createSequences(String cipherText);
    public double calcIoc(ArrayList<String> sequences);
    public String findKeyBySize(String cipherText, int keyLimit);
    public char calcProbLetterByIndex(int keyLength, int cipherSize, String substring);
    public String decipherByKey(String cipherText, String key);
}
