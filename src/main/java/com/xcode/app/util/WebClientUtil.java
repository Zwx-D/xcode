package com.xcode.app.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WebClientUtil {

    private final WebClient webClient;

    // 初始化 WebClient（可自定义配置）
    public WebClientUtil(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    /**
     * 通用 HTTP 请求方法
     *
     * @param method        HTTP 方法（GET, POST, PUT, DELETE 等）
     * @param url           请求 URL
     * @param headers       请求头（可选）
     * @param requestBody   请求体（可选）
     * @param responseType  响应类型
     * @param <T>           请求体类型
     * @param <R>           响应体类型
     * @return 响应结果
     */
    public <T, R> Mono<R> request(HttpMethod method, String url, Map<String, String> headers, T requestBody, Class<R> responseType) {
        return webClient.method(method)
            .uri(url)
            .headers(httpHeaders -> {
                if (headers != null) {
                    headers.forEach(httpHeaders::add);
                }
            })
            .bodyValue(requestBody != null ? requestBody : "")
            .retrieve()
            .bodyToMono(responseType)
            .onErrorResume(WebClientResponseException.class, ex -> {
                // 处理 HTTP 错误响应
                return Mono.error(new RuntimeException("HTTP Error: " + ex.getStatusCode()));
            })
            .onErrorResume(Exception.class, ex -> {
                // 处理其他异常
                return Mono.error(new RuntimeException("Request failed: " + ex.getMessage()));
            });
    }

    /**
     * GET 请求
     *
     * @param url          请求 URL
     * @param headers      请求头（可选）
     * @param responseType 响应类型
     * @param <R>          响应体类型
     * @return 响应结果
     */
    public <R> Mono<R> get(String url, Map<String, String> headers, Class<R> responseType) {
        return request(HttpMethod.GET, url, headers, null, responseType);
    }

    /**
     * POST 请求
     *
     * @param url          请求 URL
     * @param headers      请求头（可选）
     * @param requestBody  请求体
     * @param responseType 响应类型
     * @param <T>          请求体类型
     * @param <R>          响应体类型
     * @return 响应结果
     */
    public <T, R> Mono<R> post(String url, Map<String, String> headers, T requestBody, Class<R> responseType) {
        return request(HttpMethod.POST, url, headers, requestBody, responseType);
    }

    /**
     * PUT 请求
     *
     * @param url          请求 URL
     * @param headers      请求头（可选）
     * @param requestBody  请求体
     * @param responseType 响应类型
     * @param <T>          请求体类型
     * @param <R>          响应体类型
     * @return 响应结果
     */
    public <T, R> Mono<R> put(String url, Map<String, String> headers, T requestBody, Class<R> responseType) {
        return request(HttpMethod.PUT, url, headers, requestBody, responseType);
    }

    /**
     * DELETE 请求
     *
     * @param url          请求 URL
     * @param headers      请求头（可选）
     * @param responseType 响应类型
     * @param <R>          响应体类型
     * @return 响应结果
     */
    public <R> Mono<R> delete(String url, Map<String, String> headers, Class<R> responseType) {
        return request(HttpMethod.DELETE, url, headers, null, responseType);
    }

}
