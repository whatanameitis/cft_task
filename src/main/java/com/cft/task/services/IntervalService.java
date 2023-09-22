package com.cft.task.services;

import com.cft.task.entities.DigitInterval;
import com.cft.task.entities.LetterInterval;
import com.cft.task.repositories.DigitIntervalRepository;
import com.cft.task.repositories.LetterIntervalRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class IntervalService {
    private final DigitIntervalRepository digitIntervalsRepository;
    private final LetterIntervalRepository letterIntervalRepository;

    public void postInterval(List<List<Object>> intervals, String kind) {
        if (Objects.equals(kind, "digits")) {
            List<DigitInterval> digitIntervals = new ArrayList<>();
            intervals.forEach(interval -> digitIntervals.add(DigitInterval
                    .builder()
                    .start((Integer) interval.get(0))
                    .end((Integer) interval.get(1))
                    .build()));
            digitIntervalsRepository.saveAll(mergeIntegers(digitIntervals));
        }

        if (Objects.equals(kind, "letters")) {
            List<LetterInterval> letterIntervals = new ArrayList<>();
            intervals.forEach(interval -> letterIntervals.add(LetterInterval
                    .builder()
                    .start((String) interval.get(0))
                    .end((String) interval.get(1))
                    .build()));
            letterIntervalRepository.saveAll(mergeLetters(letterIntervals));
        }
    }

    public List<Object> getMinimalInterval(String kind) {
        if (kind.equals("digits")) {
            DigitInterval interval = digitIntervalsRepository.findMinimalInterval();
            List<Object> ret = new ArrayList<>(2);
            ret.add(interval.getStart());
            ret.add(interval.getEnd());
            return ret;
        }

        if (kind.equals("letters")) {
            LetterInterval interval = letterIntervalRepository.findMinimalInterval();
            List<Object> ret = new ArrayList<>(2);
            ret.add(interval.getStart());
            ret.add(interval.getEnd());
            return ret;
        }
        throw new IllegalArgumentException("Kind must be either digits or letters!");
    }

    private List<DigitInterval> mergeIntegers(List<DigitInterval> intervals) {
        intervals.sort(Comparator.comparingInt(DigitInterval::getStart));
        List<DigitInterval> mergedIntervals = new ArrayList<>();
        Integer start = intervals.get(0).getStart();
        Integer end = intervals.get(0).getEnd();
        for (DigitInterval interval : intervals) {
            if (interval.getStart() <= end) {
                end = Math.max(interval.getEnd(), end);
            } else {
                mergedIntervals.add(DigitInterval.builder().start(start).end(end).build());
                start = interval.getStart();
                end = interval.getEnd();
            }
        }
        mergedIntervals.add(DigitInterval.builder().start(start).end(end).build());
        return mergedIntervals;
    }

    private List<LetterInterval> mergeLetters(List<LetterInterval> intervals) {
        intervals.sort(Comparator.comparing(LetterInterval::getStart));
        List<LetterInterval> mergedIntervals = new ArrayList<>();
        char start = intervals.get(0).getStart().charAt(0);
        char end = intervals.get(0).getEnd().charAt(0);
        for (LetterInterval interval : intervals) {
            if (interval.getStart().charAt(0) <= end) {
                end = (char) Math.max(interval.getEnd().charAt(0), end);
            } else {
                mergedIntervals.add(LetterInterval.builder().start(Character.toString(start)).end(Character.toString(end)).build());
                start = interval.getStart().charAt(0);
                end = interval.getEnd().charAt(0);
            }
        }
        mergedIntervals.add(LetterInterval.builder().start(Character.toString(start)).end(Character.toString(end)).build());
        return mergedIntervals;
    }

}
