package com.example.subjects;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class PublishSubjectEx {
	public static void main(String[] args) {
		
		Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
		
		Subject<String> subject = PublishSubject.create();
		subject.map(String::length).subscribe(System.out::println);
		
		subject.onNext("Alpha");
		subject.onNext("Beta");
		subject.onNext("Gamma");
		
		// So the first subscription was successful, then dispose() was called. After that, the subject merged with source1, 
		// and streamed it's emissions alone
		subject.mergeWith(source1).subscribe(System.out::println);
		
		subject.onComplete();
	}
}
