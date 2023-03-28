package com.mericaltikardes.suffixoperations.service;

import com.mericaltikardes.suffixoperations.model.SuffixDatas;
import com.mericaltikardes.suffixoperations.repository.MongoJpa;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompareToStringsService {
    ArrayList<String> results = new ArrayList<>();
    private MongoJpa mongoRepository;
    boolean anyFound = false;

    public CompareToStringsService(MongoJpa mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public Optional<String> getResult(String... strings) {
        return getResult(Arrays.stream(strings).toList());
    }

    public Optional<String> getResult(List<String> strings) {
        List<FlexString> list = new ArrayList<>();
        for (String text : strings) {
            list.add(new FlexString(text, false, 0));
        }
        for (int i = 0; i < list.size(); i++) {
            startCompare(copyList(list), i, Collections.emptyList());
        }
        Optional<String> result = results.stream().max(Comparator.comparing(String::length));
        if (!anyFound) {
            result = Optional.empty();
        }
        results.clear();
        anyFound = false;
        return result;
    }

    public void startCompare(List<FlexString> texts, int parentIndex, List<String> addingWord) {
        FlexString parentText = texts.get(parentIndex);
        parentText.setCompared(true);
        boolean anyCompared = false;
        for (int i = 0; i < texts.size(); i++) {
            FlexString currentText = texts.get(i);
            if (currentText.equals(parentText)) continue;

            if (currentText.isCompared) continue;

            Pair<Integer, Integer> sameWordIndexes = getSameWordIndex(parentText.getText(), parentText.startIndex, currentText.getText(), 0);

            String[] currentList = currentText.getText().split(" ");
            if (sameWordIndexes == null) continue;

            ArrayList<String> wordList = new ArrayList<>();
            wordList.addAll(addingWord);

            wordList.addAll(sentenceWordSplitter(parentText.getText(), parentText.startIndex, sameWordIndexes.getFirst() - 1));
            wordList.add(currentList[sameWordIndexes.getSecond()]);
            anyCompared = true;
            anyFound = true;
            if (sameWordIndexes.getSecond() == currentList.length - 1) {
                results.add(String.join(" ", wordList));
                continue;
            }
            List<FlexString> copiedList = copyList(texts);
            copiedList.get(i).setStartIndex(sameWordIndexes.getSecond() + 1);
            startCompare(copiedList, i, wordList);
            //Swıc parentin ortak index;
        }
        if (!anyCompared) {
            ArrayList<String> wordList = new ArrayList<>();
            wordList.addAll(addingWord);
            wordList.addAll(sentenceWordSplitter(parentText.getText(), parentText.startIndex, parentText.getText().split(" ").length - 1));
            results.add(String.join(" ", wordList));
        }
    }

    private static List<String> sentenceWordSplitter(String sentence, int startIndex, int endIndex) {
        ArrayList<String> result = new ArrayList<>();
        String[] sentenceWords = sentence.split(" ");
        for (int i = startIndex; i <= endIndex; i++) {
            result.add(sentenceWords[i]);
        }
        return result;
    }

    private static boolean isSameWithoutSuffix(String parent, String current) {
        int minLength = Math.min(parent.length(), current.length());
        for (int i = 0; i < minLength; i++) {
            if (parent.charAt(i) != current.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static Pair<Integer, Integer> getSameWordIndex(String parentText, int parentStartIndex, String currentText, int currentStartIndex) {
        List<String> parentList = new ArrayList<>();
        List<String> currentList = new ArrayList<>();
        String[] parentSplit = parentText.split(" ");
        String[] currentSplit = currentText.split(" ");
        for (int i = parentStartIndex; i < parentSplit.length; i++) {

            parentList.add(parentSplit[i].replaceAll("\\p{Punct}", "").toLowerCase(Locale.ROOT));
        }
        for (int i = currentStartIndex; i < currentSplit.length; i++) {
            currentList.add(currentSplit[i].replaceAll("\\p{Punct}", "").toLowerCase(Locale.ROOT));

        }
        for (int parentIndex = 0; parentIndex < parentList.size(); parentIndex++) {
            for (int currentIndex = 0; currentIndex < currentList.size(); currentIndex++) {
                if (isSameWithoutSuffix(parentList.get(parentIndex), currentList.get(currentIndex))) {
                    return Pair.of(parentIndex + parentStartIndex, currentIndex + currentStartIndex);
                }
            }
        }
        return null;
    }

    public void save(SuffixDatas suffixDatas) {
        mongoRepository.save(suffixDatas);
    }

    static class FlexString {
        private String text;
        private boolean isCompared = false;
        private int startIndex;

        public FlexString() {
        }

        public FlexString(String text, boolean isCompared, int startIndex) {
            this.text = text;
            this.isCompared = isCompared;
            this.startIndex = startIndex;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isCompared() {
            return isCompared;
        }

        public void setCompared(boolean compared) {
            isCompared = compared;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public FlexString copy() {
            return new FlexString(this.text, this.isCompared, this.startIndex);
        }

    }
    public static List<FlexString> copyList(List<FlexString> list) {
        List<FlexString> result = new ArrayList<>();
        for (FlexString flex : list) {
            result.add(flex.copy());
        }
        return result;
    }
}

