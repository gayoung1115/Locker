package com.locker.blog.service.auth;

import com.google.gson.Gson;
import com.locker.blog.advice.exception.CCommunicationException;
import com.locker.blog.domain.repository.GithubRepository;
import com.locker.blog.domain.repository.MyRepository;
import com.locker.blog.repository.github.MyRepositoryJpaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.*;

@SpringBootTest
@Transactional
public class GithubServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private GithubService githubService;
    @Autowired private Gson gson;
    @Autowired private MyRepositoryJpaRepo myRepositoryJpaRepo;

    @Test
    public void getGithubRepoTest() throws Exception {
        // given
        final String repoUrl = "https://api.github.com/users/junhok82/subscriptions";

        // when
        List<GithubRepository> githubRepositoryList =  githubService.getGithubRepo(repoUrl);

        // then
        logger.info(githubRepositoryList.toString());
    }

    @Test
    public void getHiddenInfoTest() throws Exception {
        // given
        String loginId = "junhok82";

        // when
        String hiddenInfo = githubService.getHiddenInfo(loginId);

        // then
        logger.info(hiddenInfo);
     }

     @Test
     public void insertMyRepository() throws Exception {
         // given
         String name = "junhok82";
         String repoName = "BOJ";

         // when
         myRepositoryJpaRepo.save(MyRepository.builder()
                 .name(name)
                 .repoName(repoName)
                 .build());

         // then
         MyRepository myRepository = myRepositoryJpaRepo.findByNameAndRepoName(name,repoName).orElseThrow(ClassNotFoundException::new);
         Assertions.assertEquals(myRepository.getName(),name);
      }

      @Test
      public void delteMyRepository() throws Exception {
          // given
          String name = "junhok82";
          String repoName = "BOJ";

          // when
          myRepositoryJpaRepo.save(MyRepository.builder()
                  .name(name)
                  .repoName(repoName)
                  .build());

          // then
          myRepositoryJpaRepo.delete(myRepositoryJpaRepo.findByNameAndRepoName(name,repoName).orElseThrow(ClassNotFoundException::new));
       }

       @Test
       public void findAllMyRepository() throws Exception {
           // given
           String name = "junhok82";
           String repoName = "BOJ";

           // when
           myRepositoryJpaRepo.save(MyRepository.builder()
                   .name(name)
                   .repoName(repoName)
                   .build());

           // then
           List<MyRepository> myRepositories = myRepositoryJpaRepo.findAll();
           for (int i = 0; i < myRepositories.size(); i++) {
               if(myRepositories.get(i).getName().equals(name) && myRepositories.get(i).getRepoName().equals(repoName)) break;
               else if(myRepositories.size() == i - 1)  Assertions.fail("조회 불가");
           }
        }
}
