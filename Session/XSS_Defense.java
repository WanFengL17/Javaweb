//防御XSS
import org.owasp.encoder.Encode;

public String sanitizeInput(String input) {
        // 使用OWASP Encoder类来转义HTML特殊字符
        return Encode.forHtml(input);
        }
