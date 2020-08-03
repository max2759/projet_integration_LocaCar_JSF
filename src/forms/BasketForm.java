package forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class BasketForm {

    public void BasketForm(HttpServletRequest request) {

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        if (session.getAttribute("basket") == null) {
            Map<Integer, Object> basket = new HashMap<>();
            session.setAttribute("basket", basket);
        }

    }
}
