package com.tp.appapi.common.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MdcFilter extends OncePerRequestFilter {

    private static final String HDR_X_REQUEST_ID = "X-Request-Id";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String traceId = extractTraceId(request);
        MDC.put("traceId", traceId);
        MDC.put("userId", resolveUserId());
        MDC.put("clientIp", resolveClientIp(request));
        MDC.put("reqUri", request.getRequestURI());
        MDC.put("reqMethod", request.getMethod());
        response.setHeader(HDR_X_REQUEST_ID, traceId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private String extractTraceId(HttpServletRequest req) {
        String h = headerFirstNonBlank(req, HDR_X_REQUEST_ID);
        return (h != null) ? h : UUID.randomUUID().toString();
    }

    private String headerFirstNonBlank(HttpServletRequest req, String... names) {
        for (String n : names) {
            String v = req.getHeader(n);
            if (v != null && !v.isBlank()) return v;
        }
        return null;
    }

    private String resolveUserId() {
        // TODO: SecurityContext에서 실제 사용자 ID 추출
        return "system";
    }

    private String resolveClientIp(HttpServletRequest request) {
        // 프록시/ALB 환경 고려
        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            // "client, proxy1, proxy2" 형태 → 첫번째가 클라이언트 IP
            return xff.split(",")[0].trim();
        }
        String realIp = request.getHeader("X-Real-IP");
        if (realIp != null && !realIp.isBlank()) return realIp;
        return request.getRemoteAddr();
    }
}
