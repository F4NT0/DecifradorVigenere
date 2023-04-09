/**
 * Authors: Edson Ricardo da Costa & Gabriel Fanto Stundner.
 */

package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CipherControllerImpl implements CipherController {

    // Realiza a leitura do arquivo .txt
    @Override
    public String readCipherText(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String line = "";
        String input = "";
        while (true) {
            if (line != null) {
                // System.out.println(linha);
                input += line;
            } else
                break;
            line = buffRead.readLine();
        }
        buffRead.close();
        return input;
    }

    // Criação das Sequencias para análise
    @Override
    public void createSequences(String cipherText) {
        ArrayList<Double> iocKeySize = new ArrayList<Double>();
        ArrayList<String> sequence = new ArrayList<String>();
        int startAt = 0;
        for (int possKeySize = 1; possKeySize <= 30; possKeySize++) {
            String newSequence = "";
            while (startAt < possKeySize) {
                for (int i = startAt; i < cipherText.length(); i += possKeySize) {
                    newSequence += cipherText.charAt(i);
                }
                sequence.add(newSequence);
                newSequence = "";
                startAt++;
            }
            iocKeySize.add(calcIoc(sequence));
            sequence.clear();
            startAt = 0;
        }
    }

    // Calcula o índice de Coincidência
    @Override
    public double calcIoc(ArrayList<String> sequences) {
        ArrayList<Double> avgs = new ArrayList<Double>();
        for (int seqAvg = 0; seqAvg < sequences.size(); seqAvg++) {
            int n = 0;
            double sum = 0.0;
            int[] values = new int[26];
            for (int i = 0; i < 26; i++) {
                values[i] = 0;
            }

            int character;
            for (int i = 0; i < sequences.get(seqAvg).length(); i++) {
                character = sequences.get(seqAvg).charAt(i) - 65;
                if (character >= 0 && character < 26) {
                    values[character]++;
                    n++;
                }
            }

            for (int i = 0; i < 26; i++) {
                character = values[i];
                sum = sum + (character * (character - 1));
            }

            avgs.add(sum / (n * (n - 1)));
        }

        double sum = 0;
        for (Double average : avgs) {
            sum += average;
        }

        return sum / avgs.size();
    }

    @Override
    public String findKeyBySize(String cipherText, int keyLimit) {
        ArrayList<String> sequences = new ArrayList<String>();
        String newSequence = "";
        int startAt = 0;
        String key = "";

        while (startAt < keyLimit) {
            for (int i = startAt; i < cipherText.length(); i += keyLimit) {
                newSequence += cipherText.charAt(i);
            }
            sequences.add(newSequence);
            newSequence = "";
            startAt++;
        }

        for (int i = 0; i < keyLimit; i++) {
            key += calcProbLetterByIndex(keyLimit, sequences.get(i).length(), sequences.get(i));
        }

        return key;
    }

    @Override
    public char calcProbLetterByIndex(int keyLength, int keyCipher, String substring) {

        // Frequências do alfabeto na língua inglesa de A a Z
        double[] freqEnglish = { 0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966,
                0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056,
                0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074 };
        ArrayList<Double> chiSums = new ArrayList<Double>();

        for (int shift = 0; shift < 26; shift++) {
            double chiSum = 0;
            int ASCIILetterA = 65;
            int countLetterOcc = 0;
            String message = "";

            for (int i = 0; i < substring.length(); i++) {
                int c = substring.charAt(i) - (shift % 26);
                if (c < 'A') {
                    c = c + 26;
                }
                message = message + (char) c;
            }

            // Calcula o valor chi para cada letra do alfabeto
            for (int i = 0; i < 26; i++) {

                // Verificação do número de ocorrências da letra na String
                countLetterOcc = message.length() - message.replace(String.valueOf(Character.toChars(ASCIILetterA + i)), "").length();
                double x = countLetterOcc - ((keyCipher) * freqEnglish[i]);
                double numerator = Math.pow(x, 2);
                chiSum += numerator / (keyCipher * freqEnglish[i]);
            }
            chiSums.add(chiSum);
        }

        // Encontra o menor valor de chi indicando a letra mais provável
        int minLetterIndex = chiSums.indexOf(Collections.min(chiSums));
        char minLetter = (char) ((minLetterIndex % 26) + 'A');

        return minLetter;
    }

    // Decifra a cifra pela chave
    @Override
    public String decipherByKey(String cipherText, String key) {
        String decipher = "";
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            decipher += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        return decipher;
    }
}
