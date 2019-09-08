package com.lhb.community.controller;

import com.lhb.community.dto.AccessTokenDTO;
import com.lhb.community.dto.GithubUser;
import com.lhb.community.mapper.UserMapper;
import com.lhb.community.model.User;
import com.lhb.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getuser(accessToken);
        User user1 = userMapper.findById(githubUser.getId());
        if (githubUser != null) {
            if (user1 != null) {
                response.addCookie(new Cookie("token", user1.getToken()));
                return "redirect:/";
            } else {
                User user = new User();
                user.setName(githubUser.getName());
                user.setAccountId(githubUser.getId());
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setBio(githubUser.getBio());
                user.setAvatarUrl(githubUser.getAvatar_url());
                userMapper.insert(user);
                response.addCookie(new Cookie("token", token));
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }
    }
}
