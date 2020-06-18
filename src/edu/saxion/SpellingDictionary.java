package edu.saxion;

import java.util.*;

import static java.rmi.server.LogStream.log;

public class SpellingDictionary {

    private Map<String, Integer> nwords	= new HashMap<String, Integer>(new Integer(1));

    public String correct(String word) {
        if(this.nwords.containsKey(word))
            return word;
        else
            return max3(word);
    }

    private String max3(String word) {
        // known(wordSet), known(edits1(word)),known_edits2(word), word
        Set<String> wordSet = new HashSet<String>();
        wordSet.add(word);
        String bestMatch = word;
        // any word in a set is infinitely more probable than
        // any word from the next set
        Set<String> knownWordSet = known(wordSet);
        bestMatch = max(knownWordSet, word);
        if (knownWordSet.contains(bestMatch)) {
            log("max3: Word in known Word Set.");
            return bestMatch;
        }
//        Set<String> knownEdits1 = known(edits1(word));
//        bestMatch = max(knownEdits1, word);
//        if (knownEdits1.contains(bestMatch)) {
//            log("max3: Word in Known Edits 1 Set.");
//            return bestMatch;
//        }
//        Set<String> knownEdits2 = known_edits2(word);
//        bestMatch = max(knownEdits2, word);
//        if (knownEdits2.contains(bestMatch)) {
//            log("max3: Word in Known Edits 2 Set.");
//            return bestMatch;
//        }
        else {
            log("max3: Word not found. Using word itself.");
            wordSet = new HashSet<String>();
            wordSet.add(word);
            return max(wordSet, word);
        }
    }

    //get the best set from the match
    private String max(Set<String> set, String word) {
        int maxPc = 0;
        String bestMatch = word;
        log("max - num of candidates: " + set.size());
        for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            // log("Checking " + element);
            for (Iterator<String> iterator = this.nwords.keySet().iterator(); iterator.hasNext();) {
                String elem2 = (String) iterator.next();
                if (element.equalsIgnoreCase(elem2)) {
                    int newPc = this.nwords.get(elem2);
                    // log("newPc for " + elem2 + " is " + newPc);
                    if (newPc > maxPc) {
                        bestMatch = element;
                        maxPc = newPc;
                        log("New Best Match so far - Word: " + bestMatch + ", P(c): " + maxPc);
                    }
                }
            }
        }
        return bestMatch;
    }

    //check which words from the input were learned
    private Set<String> known(Set<String> words) {
        Set<String> result = new HashSet<String>();
        for (Iterator<String> iter = words.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            if (this.nwords.containsKey(element))
                result.add(element);
        }
        return result;
    }

    }
