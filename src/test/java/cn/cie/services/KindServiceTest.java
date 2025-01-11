package cn.cie.services;

import cn.cie.utils.Result;
import cn.cie.entity.dto.GameDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value  = {"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class KindServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KindService kindService;

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getGamesByKind() throws Exception {
        int kind = 1;
        try {
            Result<List<GameDTO>> result = kindService.getGamesByKind(kind, 1);
            if (result.isSuccess()) {
                logger.info("Query results for:{}" + result.getData());
            } else {
                logger.info("failure");
            }
        } catch (Exception e) {
            logger.error("mistake：", e);
        }
    }

}