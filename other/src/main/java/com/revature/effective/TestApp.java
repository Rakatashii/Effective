package com.revature.effective;

import java.net.URI;

import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.MultipartBodyBuilder.PublisherEntity;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import io.reactivex.Observable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import rx.RxReactiveStreams;

@SpringBootApplication
public class TestApp implements CommandLineRunner {

	public static void main(String... args) {
		SpringApplication.run(TestApp.class, args);
	}
	
    private Integer bodyIndex;
    private ResolvableType requestBodyType;
    
    WebClient webClient = Mockito.mock(WebClient.class);
    ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);
    RequestBodySpec requestBodySpec = Mockito.mock(WebClient.RequestBodySpec.class);

	@Override
	public void run(String... args) throws Exception {
		
		Mono<String> input = Mono.just("string");
		
		BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter = BodyInserters
            .fromPublisher((Publisher<String>) input, String.class);
		
		Observable<?> obs = Observable.just(Mono.just("string"));
		StepVerifier.create(RxReactiveStreams.toPublisher(Observable.just("1", 1)
				..Mono.just(1).flatMap(num -> Mono.just("string"))));

		ReactiveHttpInputMessage msg;
		WebClient.RequestHeadersSpec<?> requestSpec = WebClient.create()
            .method(HttpMethod.GET)
            .uri(new URI("http", "google.com", "/test", null)) 
            ;
		RequestHeadersSpec<?> rhs = requestBodySpec
			.body(BodyInserters.fromPublisher(Mono.just("data"), String.class));
		
		String returnVal = rhs.retrieve().bodyToMono(String.class).onErrorReturn("faker").block();
		System.out.println("returnVal: " + returnVal);
		
		ResponseSpec resp = requestSpec.retrieve();
		Mono<String> output = resp.bodyToMono(String.class);
		output.flatMap(string -> {
			System.out.println("OUTPUT: " + string);
			return Mono.just(string);
		}).subscribe();
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BodyInserter<? extends Object, ?> insertBody(Object body){
        if (bodyIndex == null) {
            return BodyInserters.empty();
        }

        if (requestBodyType instanceof Publisher) {
            return BodyInserters.fromPublisher((Publisher) body, requestBodyType.getGeneric(0).getRawClass());
        } else if (requestBodyType instanceof Resource) {
            return BodyInserters.fromResource((Resource) body);
        
        //... 
            
		} else {
            return BodyInserters.fromObject(body);
        }
	}
	
}
