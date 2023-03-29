package fast.boardadmin.service;

import fast.boardadmin.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleManagementService {

    public List<ArticleDto> getArticles(){
        return List.of();
    }

    public ArticleDto getArticle(Long articleId){
        return null;
    }

    public void deleteArticle(Long articleId){

    }
}
