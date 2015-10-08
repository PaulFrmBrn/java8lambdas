package com.paulfrmbrn.lambda8.example;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * This code was borrowed from https://github.com/RichardWarburton/java-8-lambdas-exercises
 *
 * @author richard
 */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    // TODO: test
    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return concat(Stream.of(artist), artist.getMembers());
        });
    }

}
