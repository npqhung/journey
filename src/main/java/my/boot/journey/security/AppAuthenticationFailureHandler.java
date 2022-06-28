package my.boot.journey.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AppAuthenticationFailureHandler extends
        SimpleUrlAuthenticationFailureHandler {
    @Autowired
    LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");

        loginAttemptService.loginFailed(username, request.getRemoteAddr());

//        super.setDefaultFailureUrl("/login?error");
        if (loginAttemptService.isBlocked(username)) {
            super.onAuthenticationFailure(request, response, new LockedException("Your account is temporarily locked. Please wait 5 minutes before retrying."));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}
