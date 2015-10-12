import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.SampleData;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        // 1.a
        System.out.println("addUpResult = " + addUp(Stream.of(3, 5)));
        System.out.println("addUpResult = " + addUp(Stream.of(11, 5)));

        // 1.b
        System.out.println("getArtistsInfo(Artist.getSampleArtistList()) = " + getArtistsInfo(SampleData.getThreeArtists()));

        // 1.c
        List<Album> albums = new ArrayList<>();
        albums.add(SampleData.aLoveSupreme);
        albums.add(SampleData.sampleShortAlbum);
        albums.add(SampleData.manyTrackAlbum);
        System.out.println("getSmallAlbums(albums) = " + getSmallAlbums(albums));

        // 2
        System.out.println("externalIteration(SampleData.getThreeArtists()); = " + externalIteration(SampleData.getThreeArtists()));
        System.out.println("internalIteration(SampleData.getThreeArtists()); = " + internalIteration(SampleData.getThreeArtists()));
        System.out.println("internalIterationFromBook(SampleData.getThreeArtists()); = " + internalIterationFromBook(SampleData.getThreeArtists()));

        // 3.a eager
        // 3.b lazy

        // 4.a yes - parameter is a function
        // 4.b no - no function neither among parameters, no return value is a function

        // 5 yes
        // 5.a no

        // 6
        System.out.println("countLowerCase(\"somEsTRinG\") = " + countLowerCase("somEsTRinG"));

        // 7
        Optional<String> maxLowerCaseCountResult = maxLowerCaseCount(Arrays.asList("asd","Qwe","ERty"));
        //Optional<String> maxLowerCaseCountResult = maxLowerCaseCount(Collections.emptyList());
        if (maxLowerCaseCountResult.isPresent()) {
            System.out.println("maxLowerCaseCount(Arrays.asList(\"asd\",\"Qwe\",\"ERty\")) = " + maxLowerCaseCountResult.get());
        } else {
            System.out.println("maxLowerCaseCount param list is empty");
        }

        System.out.println("Arrays.asList(\"asd\",\"def\").stream().map(s -> s.length()).collect(Collectors.toList()); = " +
                Arrays.asList("asd","def").stream().map(String::length).collect(Collectors.toList()));

        System.out.println("Main.<String,Integer>myMap(Arrays.asList(\"asd\",\"def\").stream(),s -> s.length()) = " +
                Main.myMap(Arrays.asList("asd", "def").stream(), String::length));

        System.out.println("Arrays.asList(\"asd\",\"def\").stream().map(s -> s + 2).collect(Collectors.toList()) = " +
                Arrays.asList("asd","def").stream().map(s -> s + 2).collect(Collectors.toList()));

        System.out.println("Main.<String,String>myMap(Arrays.asList(\"asd\", \"def\").stream(), s -> s + 2) = " +
                Main.<String,String>myMap(Arrays.asList("asd", "def").stream(), s -> s + 2));


    }

    public static int addUp (Stream<Integer> numbers) {
        return numbers.reduce(0,(accumulator,element) -> accumulator + element);
    }

    public static List<String> getArtistsInfo(List<Artist> artists) {
        //return artist.getMembers().map(artist1 -> artist.getName() + " is from " +  artist.getNationality()).collect(Collectors.toList()) ;
        //return artists.stream().map(Artist::getName).collect(Collectors.toList());
        //return artists.stream().map(artist -> artist.getName()).collect(Collectors.toList());
        //return artists.stream().map(artist -> artist.getName() + " is from " + artist.getNationality()).collect(Collectors.toList());
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(Collectors.toList());
    }

    public static List<String> getSmallAlbums(List<Album> albums) {
        return albums.stream().filter(album -> album.getTrackList().size() <= 3).map(Album::getName).collect(Collectors.toList());
    }

    public static int externalIteration(List<Artist> artists) {
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
        return totalMembers;
    }

    public static int internalIteration(List<Artist> artists) {
        return (int) artists.stream().mapToLong(artist -> artist.getMembers().count()).sum();
    }

    public static int internalIterationFromBook(List<Artist> artists) {
        return artists.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue();
    }

    public static long countLowerCase(String value) {
        return value.chars().filter(Character::isLowerCase).count();
    }

    public static Optional<String> maxLowerCaseCount(List<String> stringList) {
        return stringList.stream().max(Comparator.comparing(Main::countLowerCase));
    }

    public static <T,R> List<R> myMap(Stream<T> param, Function<? super T, ? extends R> mapper) {
        return param.reduce(

                // identity
                new ArrayList<>(),

                // accumulator
                (List<R> accumulator, T element) -> {
                    List<R> list = new ArrayList<>(accumulator);
                    list.add(mapper.apply(element));
                    return list;

                // combiner
                },(List<R> leftList, List<R> rightList) -> {
                    List<R> temp = new ArrayList<>(leftList);
                    temp.addAll(rightList);
                    return temp;
                }
                );

    }


}
