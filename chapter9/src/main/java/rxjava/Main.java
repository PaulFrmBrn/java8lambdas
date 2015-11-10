package rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

import java.util.Arrays;
import java.util.List;

/**
 *
 * http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
 *
 * @author paul
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        canonicalCode();

        simplerCode();

        mapExample();

        flatMapExample();

        operatorsExample();

        errorHandling();

        subscriptionExample();

    }

    private static void subscriptionExample() {

        System.out.println("subscriptionExample --> ");
        System.out.println("creating observable");
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("a");
                subscriber.onNext("b");
                subscriber.onNext("c");
                System.out.println("sleep...");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    System.out.println("Ooooops....");
                }
                System.out.println("...awake");
                subscriber.onNext("d");
                subscriber.onNext("e");
                subscriber.onCompleted();
            }
        });

        System.out.println("creating subscription");
        Subscription subscription = observable.subscribe(System.out::println);
        subscription.unsubscribe();
        System.out.println("done");
        System.out.println("creating subscription2 ");
        Subscription subscription2 = observable.subscribe(System.out::println);
        System.out.println("done 2");

    }

    private static void errorHandling() {

        System.out.println("errorHandling --> ");
        Observable.from("a","b","c","d","e","f")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("done!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Ooooops.... " + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        if (s.equals("d")) {
                            throw new IllegalArgumentException("Aaaaaaa!!!!");
                        }
                        System.out.println(s);
                    }
                });
    }

    private static void operatorsExample() {

        System.out.println("operatorsExample --> ");
        Observable<List<String>> observable = Observable.from(
                Arrays.asList(
                        Arrays.asList("a","b"),
                        Arrays.asList("c","d"),
                        Arrays.asList("e","f")
                )
        );
        Observable<String> newObservable = observable.flatMap(Observable::from)
                .filter(s -> !s.equals("c"))
                .take(4)
                .doOnNext(s -> System.out.println(s + " etc"));
        System.out.println("nothing happening...");
        newObservable.subscribe(System.out::println);
        System.out.println("done");

    }

    private static void flatMapExample() {

        System.out.println("flatMapExample --> ");
        Observable<List<String>> observable = Observable.from(
                Arrays.asList(Arrays.asList("a","b"),Arrays.asList("c","d"))
        );
        observable.flatMap(Observable::from)
                  .subscribe(System.out::println);

    }

    private static void mapExample() {

        System.out.println("mapExample --> ");
        Observable.just("Hello, World!")
                .map(s -> s + " -Dan")
                .map(String::hashCode)
                .map(s -> Integer.toString(s))
                .subscribe(System.out::println);
    }

    private static void simplerCode() {

        System.out.println("simplerCode --> ");
        Observable<String> observable = Observable.just("Hello, World!");
        Action1<String> action = System.out::println;
        observable.subscribe(action);

    }

    private static void canonicalCode() throws InterruptedException {

        System.out.println("canonicalCode --> ");

        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello, world!");
                subscriber.onCompleted();
            }
        });
        System.out.println("observer created");

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        System.out.println("subscriber created");

        System.out.println("sleep...");
        Thread.sleep(3000L);
        System.out.println("...awake");

        myObservable.subscribe(mySubscriber);
        myObservable.subscribe(mySubscriber);

    }

}
