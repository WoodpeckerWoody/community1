package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name ="code") String code,
                           @RequestParam(name ="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("2c28fc64c9407f52d7c4");
        accessTokenDTO.setClient_secret("5c72fb6d6e474c53d9ee77806d07f0d7393c24cb");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}

