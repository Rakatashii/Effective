package com.revature.effective.webflux;

import com.revature.effective.routes.ResponseHandler;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@RestController
public class HotFluxCtrl {

		private final FluxProcessor<Object, Object> processor;
		private final FluxSink<Object> sink;
	    private final AtomicLong counter;
	    @Autowired
	    private ResponseHandler handler;
	    
	    public HotFluxCtrl() {
	        this.processor = DirectProcessor.create().serialize();
	        this.sink = processor.sink();
	        this.counter = new AtomicLong();
	        this.handler = new ResponseHandler();
	    }
	    
		@RequestMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	    public Flux<ServerSentEvent<?>> sse() {
	        return processor.map(e -> ServerSentEvent.builder(e).build());
	    }

		@GetMapping("/writeToSink")
	    public void writeToSink() {
	        sink.next(generateMsg());
	        readFromSink();
	    }
		
		public void readFromSink() {
	        processor.cast(String.class)//.take(1)
	        .checkpoint().log()
	        .subscribe(next -> System.out.println("Next - " + next), 
	        	err -> ((Throwable) err).printStackTrace(), 
	        	() -> System.out.println("NEVER?"));
		}

		private String generateMsg() {
			return "Message#" + counter.getAndIncrement();
		}
		
		@GetMapping("/dumpSink")
	    public RouterFunction<ServerResponse> routeDumpSink() {
			return RouterFunctions.route(RequestPredicates.GET("/dumpSink"), this::dumpSink);
	    }
		
		private Mono<ServerResponse> dumpSink(ServerRequest req) {
			Mono<ServerResponse> response = Mono.just(processor.cast(String.class).single().log().block())
				.flatMap(handler::getServerResponse);
			return response;
		}
}

