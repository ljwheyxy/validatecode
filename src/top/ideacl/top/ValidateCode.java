package top.ideacl.top;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author Chiawei
 * date 2019-3-7 20:11
 */
public class ValidateCode {

    private static ValidateCode validateCode;
    public ValidateCode() {
    }

    public ValidateCode(HttpServletRequest reqeust, HttpServletResponse response, int width, int height, int codeCount, int lineCount) throws IOException {
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = reqeust.getSession();

        ValidateCodeUtil vCode = new ValidateCodeUtil(width, height, codeCount, lineCount);
        session.setAttribute(Helper.SESSION_CHECKCODE, vCode.getCode());
        vCode.write(response.getOutputStream());
    }

    public static boolean isCodeRight(HttpServletRequest reqeust, HttpServletResponse response, String validateCode) {
        if (validateCode == null || "".equals(validateCode) ) {
            return false;
        } else{
            HttpSession session = reqeust.getSession();
            if ( session.getAttribute(Helper.SESSION_CHECKCODE) != null) {
                if (((String) session.getAttribute(Helper.SESSION_CHECKCODE)).equals(validateCode)) {
                    return true;
                }else {
                    return false;
                }
            }

            return false;
        }

    }

    public static void writeValidateCode(HttpServletRequest reqeust, HttpServletResponse response, int width, int height, int codeCount, int lineCount) throws IOException {
        validateCode = new ValidateCode(reqeust, response, width, height, codeCount, lineCount);
    }
}
