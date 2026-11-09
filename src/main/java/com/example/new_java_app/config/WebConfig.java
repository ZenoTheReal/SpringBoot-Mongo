package com.example.new_java_app.config;

import org.springframework.boot.webmvc.autoconfigure.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class WebConfig implements WebMvcRegistrations {

    /**
     * Bulletproof Trailing Slash Support (NestJS-style):
     * Automatically registers both trailing-slash and non-trailing-slash variations
     * for EVERY controller method in the entire application.
     *
     * Developers can write @GetMapping, @GetMapping(""), or @GetMapping("/") freely,
     * and clients can call /path or /path/ - both will always match flawlessly!
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {
            @Override
            protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
                RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
                if (info == null) {
                    return null;
                }

                Set<String> patterns = info.getPatternValues();
                Set<String> combined = new LinkedHashSet<>();

                for (String pattern : patterns) {
                    combined.add(pattern);
                    if (pattern.endsWith("/") && pattern.length() > 1) {
                        // If registered with trailing slash (e.g. /faqs/), also add /faqs
                        combined.add(pattern.substring(0, pattern.length() - 1));
                    } else if (!pattern.endsWith("/")) {
                        // If registered without trailing slash (e.g. /faqs), also add /faqs/
                        combined.add(pattern + "/");
                    }
                }

                return info.mutate().paths(combined.toArray(new String[0])).build();
            }
        };
    }
}
