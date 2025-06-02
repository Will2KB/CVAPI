package com.WB.API.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

	private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

	private static final Logger logger = LoggerFactory.getLogger(RateLimitingFilter.class);

	private Bucket resolveBucket(String ip) {
		Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1)));
		return cache.computeIfAbsent(ip, key -> Bucket.builder().addLimit(limit).build());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String clientIp = request.getRemoteAddr();
		Bucket bucket = resolveBucket(clientIp);

		if (bucket.tryConsume(1)) {
			filterChain.doFilter(request, response);
		} else {
			String remoteAddr = request.getRemoteAddr();
			logger.warn("Rate limit exceeded for IP: {}", remoteAddr);
			response.setStatus(429);// Code HTTP Too Many Requests
			response.getWriter().write("Too many requests – try again later.");
		}
	}
}
