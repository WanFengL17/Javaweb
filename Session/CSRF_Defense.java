
//防御CSRF
import javax.servlet.http.HttpServletRequest;

public String generateCSRFToken(HttpServletRequest request) {
        // 生成或获取与用户会话关联的CSRF令牌
        String csrfToken = ...; // 实现细节根据框架和应用需求而定
        request.getSession().setAttribute("CSRF_TOKEN", csrfToken);
        return csrfToken;
        }