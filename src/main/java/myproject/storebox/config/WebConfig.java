package myproject.storebox.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(0, (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) -> {
            if (ex instanceof IOException && 
                (ex.getMessage() != null && ex.getMessage().contains("Connection reset by peer") || 
                 ex instanceof java.net.SocketException || 
                 ex instanceof org.apache.catalina.connector.ClientAbortException ||
                 ex instanceof java.io.EOFException)) {
                // Client disconnected, log at debug level to reduce noise
                if (log.isDebugEnabled()) {
                    log.debug("Client disconnected: " + ex.getMessage());
                }
                // Return null to indicate the exception has been handled
                return new ModelAndView();
            }
            // Let other exceptions be handled by default resolvers
            return null;
        });
    }
}
