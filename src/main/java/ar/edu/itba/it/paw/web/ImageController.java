package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;


@Controller
public class ImageController extends BaseController {

    @RequestMapping
    @ResponseBody
    public byte[] showUserImage(HttpSession session)  {

        if (!isLoggedIn(session)) {
            return null;
        }

        Picture avatar = getLoggedInUser(session).getAvatar();

        if (avatar == null) {
            return null;
        }

        return avatar.getBytes();
    }

    @RequestMapping
    @ResponseBody
    public byte[] show(@RequestParam(value = "userId") User user)  {

        if (user == null || user.getAvatar() == null)
            return null;

        return user.getAvatar().getBytes();
    }
}

