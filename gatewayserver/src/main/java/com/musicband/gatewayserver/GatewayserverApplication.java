package com.musicband.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator musicBandRouteConfig(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p.path("/musicband/tour/**")
						.filters(f->f.rewritePath("/musicband/tour/?(?<segment>.*)","/${segment}"))
						.uri("lb://TOUR"))
				.route(p->p.path("/musicband/ticket/**")
						.filters(f->f.rewritePath("/musicband/ticket/?(?<segment>.*)","/${segment}"))
						.uri("lb://TICKET"))
				.route(p->p.path("/musicband/merch/**")
						.filters(f->f.rewritePath("/musicband/merch/?(?<segment>.*)","/${segment}"))
						.uri("lb://MERCH"))
				.route(p->p.path("/musicband/payment/**")
						.filters(f->f.rewritePath("/musicband/payment/?(?<segment>.*)","/${segment}"))
						.uri("lb://PAYMENT"))
				.route(p->p.path("/musicband/notification/**")
						.filters(f->f.rewritePath("/musicband/notification/?(?<segment>.*)","/${segment}"))
						.uri("lb://NOTIFICATION"))
				.build();
	}
}
